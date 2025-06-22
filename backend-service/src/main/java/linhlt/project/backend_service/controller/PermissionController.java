package linhlt.project.backend_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import linhlt.project.backend_service.dto.request.PermissionRequest;
import linhlt.project.backend_service.dto.response.ApiResponse;
import linhlt.project.backend_service.dto.response.PermissionResponse;
import linhlt.project.backend_service.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/permission")
@Tag(name = "PERMISSION-USER")
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    public PermissionResponse createPermission(@RequestBody PermissionRequest permissionRequest) {
        return permissionService.createPermission(permissionRequest);
    }

    @GetMapping
    public List<PermissionResponse> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @DeleteMapping("/{permission}")
    public void deletePermission(@PathVariable("permission") String permission) {
        permissionService.deletePermission(permission);
    }
}
