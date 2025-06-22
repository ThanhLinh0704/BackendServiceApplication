package linhlt.project.backend_service.mapper;

import linhlt.project.backend_service.dto.request.RoleRequest;
import linhlt.project.backend_service.dto.response.RoleResponse;
import linhlt.project.backend_service.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleResponse toRoleResponse(Role role);
}
