package linhlt.project.backend_service.service;

import linhlt.project.backend_service.dto.request.RoleRequest;
import linhlt.project.backend_service.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);
    List<RoleResponse> getAllRoles();
}
