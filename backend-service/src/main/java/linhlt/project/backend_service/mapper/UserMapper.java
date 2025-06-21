package linhlt.project.backend_service.mapper;

import linhlt.project.backend_service.dto.request.AddressRequest;
import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.request.UserUpdateRequest;
import linhlt.project.backend_service.dto.response.UserResponse;
import linhlt.project.backend_service.model.AddressEntity;
import linhlt.project.backend_service.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(UserRequest userRequest);
    void updateUserEntity(@MappingTarget UserEntity userEntity, UserUpdateRequest userUpdateRequest);
    UserResponse toUserResponse(UserEntity userEntity);
    void toAddressEntity(@MappingTarget AddressEntity addressEntity, AddressRequest addressRequest);
}
