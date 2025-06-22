package linhlt.project.backend_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import linhlt.project.backend_service.dto.request.RoleRequest;
import linhlt.project.backend_service.dto.response.RoleResponse;
import linhlt.project.backend_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/role")
@Tag(name = "ROLE-USER")
public class RoleController {
    RoleService roleService;

    @PostMapping
    RoleResponse createRole(@RequestBody RoleRequest roleRequest) {
        return roleService.createRole(roleRequest);
    }

    @GetMapping
    List<RoleResponse> getAllRoles() {
        return roleService.getAllRoles();
    }
}
