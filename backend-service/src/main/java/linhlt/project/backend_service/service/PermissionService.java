package linhlt.project.backend_service.service;

import linhlt.project.backend_service.dto.request.PermissionRequest;
import linhlt.project.backend_service.dto.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest permissionRequest);
    List<PermissionResponse> getAllPermissions();
    void deletePermission(String permissionId);
}
