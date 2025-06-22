package linhlt.project.backend_service.service.implement;

import linhlt.project.backend_service.dto.request.PermissionRequest;
import linhlt.project.backend_service.dto.response.PermissionResponse;
import linhlt.project.backend_service.mapper.PermissionMapper;
import linhlt.project.backend_service.model.Permission;
import linhlt.project.backend_service.repository.PermissionRepository;
import linhlt.project.backend_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j(topic = "PERMISSION-SERVICE")
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    @Override
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.toPermission(permissionRequest);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        var listPermission = permissionRepository.findAll();
        return listPermission.stream().map(permission -> permissionMapper.toPermissionResponse(permission)).toList();
    }

    @Override
    public void deletePermission(String permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}
