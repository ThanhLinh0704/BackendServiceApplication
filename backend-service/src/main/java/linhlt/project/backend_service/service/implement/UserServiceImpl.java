package linhlt.project.backend_service.service.implement;

import linhlt.project.backend_service.common.Role;
import linhlt.project.backend_service.common.UserStatus;
import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.request.UserUpdateRequest;
import linhlt.project.backend_service.dto.response.UserResponse;
import linhlt.project.backend_service.exception.AppException;
import linhlt.project.backend_service.exception.ErrorCode;
import linhlt.project.backend_service.mapper.UserMapper;
import linhlt.project.backend_service.model.AddressEntity;
import linhlt.project.backend_service.model.UserEntity;
import linhlt.project.backend_service.repository.AddressRepository;
import linhlt.project.backend_service.repository.RoleRepository;
import linhlt.project.backend_service.repository.UserRepository;
import linhlt.project.backend_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {
    
    UserRepository userRepository;
    UserMapper userMapper;
    AddressRepository addressRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    public UserResponse createUser(UserRequest userRequest) {
        log.info("Saving user: {}", userRequest);

        if (userRepository. existsByUsername(userRequest.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        UserEntity userEntity = userMapper.toUserEntity(userRequest);
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        userEntity.setRoles(roles);

        userEntity.setStatus(UserStatus.NONE);
        userRepository.save(userEntity);
        if (userEntity.getId() != null) {
            log.info("user id: {}", userEntity.getId());
            List<AddressEntity> addresses = new ArrayList<>();
            userRequest.getAddressRequests().forEach(address -> {
                AddressEntity addressEntity = new AddressEntity();
                userMapper.toAddressEntity(addressEntity, address);
                addressEntity.setUserId(userEntity.getId());
                addresses.add(addressEntity);
            });
            addressRepository.saveAll(addresses);
            log.info("Saved addresses: {}", addresses);
        }
        return userMapper.toUserResponse(userEntity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        log.info("Getting all users");
        List<UserResponse> listUserResponse =  userRepository.findAll()
                .stream().map(userEntity -> userMapper.toUserResponse(userEntity)).toList();
        log.info("Found {} users", listUserResponse.size());
        return listUserResponse;
    }


    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {
        log.info("Getting user by id: {}", userId);
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(
                        ()->new RuntimeException("User not found")));
    }

    public UserResponse getMyInformation() {
        var context = SecurityContextHolder.getContext().getAuthentication();
        String userName = context.getName();
        UserEntity myInformation = userRepository.findByUsername(userName);
        return userMapper.toUserResponse(myInformation);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                ()->new RuntimeException("User not found"));
        userMapper.updateUserEntity(userEntity, userUpdateRequest);

        userEntity.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
        var listRoles = roleRepository.findAllById(userUpdateRequest.getRoles());
        userEntity.setRoles(new HashSet<>(listRoles));

        List<AddressEntity> addresses = new ArrayList<>();
        userUpdateRequest.getAddressRequests().forEach(address -> {
            AddressEntity addressEntity = addressRepository.findByUserIdAndAddressType(userId, address.getAddressType());
            if (addressEntity == null) {
                addressEntity = new AddressEntity();
            }
            userMapper.toAddressEntity(addressEntity, address);
            addresses.add(addressEntity);
        });
        addressRepository.saveAll(addresses);
        log.info("Updated addresses: {}", addresses);
        return userMapper.toUserResponse(userRepository.save(userEntity));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setStatus(UserStatus.INACTIVE);
        userRepository.save(userEntity);
        log.info("Deleted user: {}", userEntity);
    }
}
