package linhlt.project.backend_service.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import linhlt.project.backend_service.dto.request.AddressRequest;
import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.request.UserUpdateRequest;
import linhlt.project.backend_service.dto.response.UserResponse;
import linhlt.project.backend_service.model.AddressEntity;
import linhlt.project.backend_service.model.Role;
import linhlt.project.backend_service.model.UserEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-23T23:08:47+0900",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.3 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.username( userRequest.getUsername() );
        userEntity.password( userRequest.getPassword() );
        userEntity.firstName( userRequest.getFirstName() );
        userEntity.lastName( userRequest.getLastName() );
        userEntity.email( userRequest.getEmail() );
        userEntity.gender( userRequest.getGender() );
        userEntity.birthDate( userRequest.getBirthDate() );
        userEntity.phoneNumber( userRequest.getPhoneNumber() );
        userEntity.userType( userRequest.getUserType() );

        return userEntity.build();
    }

    @Override
    public void updateUserEntity(UserEntity userEntity, UserUpdateRequest userUpdateRequest) {
        if ( userUpdateRequest == null ) {
            return;
        }

        userEntity.setUsername( userUpdateRequest.getUsername() );
        userEntity.setPassword( userUpdateRequest.getPassword() );
        userEntity.setFirstName( userUpdateRequest.getFirstName() );
        userEntity.setLastName( userUpdateRequest.getLastName() );
        userEntity.setEmail( userUpdateRequest.getEmail() );
        userEntity.setGender( userUpdateRequest.getGender() );
        userEntity.setBirthDate( userUpdateRequest.getBirthDate() );
        userEntity.setPhoneNumber( userUpdateRequest.getPhoneNumber() );
        userEntity.setUserType( userUpdateRequest.getUserType() );
    }

    @Override
    public UserResponse toUserResponse(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( userEntity.getId() );
        userResponse.setUsername( userEntity.getUsername() );
        userResponse.setPassword( userEntity.getPassword() );
        userResponse.setFirstName( userEntity.getFirstName() );
        userResponse.setLastName( userEntity.getLastName() );
        userResponse.setEmail( userEntity.getEmail() );
        userResponse.setGender( userEntity.getGender() );
        userResponse.setBirthDate( userEntity.getBirthDate() );
        userResponse.setPhoneNumber( userEntity.getPhoneNumber() );
        userResponse.setUserType( userEntity.getUserType() );
        Set<Role> set = userEntity.getRoles();
        if ( set != null ) {
            userResponse.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return userResponse;
    }

    @Override
    public void toAddressEntity(AddressEntity addressEntity, AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return;
        }

        addressEntity.setApartmentNumber( addressRequest.getApartmentNumber() );
        addressEntity.setFloor( addressRequest.getFloor() );
        addressEntity.setBuilding( addressRequest.getBuilding() );
        addressEntity.setStreetNumber( addressRequest.getStreetNumber() );
        addressEntity.setStreet( addressRequest.getStreet() );
        addressEntity.setCity( addressRequest.getCity() );
        addressEntity.setCountry( addressRequest.getCountry() );
        addressEntity.setAddressType( addressRequest.getAddressType() );
    }
}
