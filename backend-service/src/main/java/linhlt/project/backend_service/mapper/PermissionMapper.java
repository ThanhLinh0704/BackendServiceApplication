package linhlt.project.backend_service.mapper;

import linhlt.project.backend_service.dto.request.PermissionRequest;
import linhlt.project.backend_service.dto.response.PermissionResponse;
import linhlt.project.backend_service.model.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
