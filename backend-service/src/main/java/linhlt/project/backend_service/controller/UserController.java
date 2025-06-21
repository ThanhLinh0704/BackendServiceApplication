package linhlt.project.backend_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.request.UserUpdateRequest;
import linhlt.project.backend_service.dto.response.ApiResponse;
import linhlt.project.backend_service.dto.response.UserResponse;
import linhlt.project.backend_service.service.implement.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "CRUD-USER")
public class UserController {

    UserServiceImpl userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userRequest));
        return apiResponse;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable String userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return "User had been deleted";
    }
}
