package linhlt.project.backend_service.service;

import linhlt.project.backend_service.dto.request.UserRequest;
import linhlt.project.backend_service.dto.request.UserUpdateRequest;
import linhlt.project.backend_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest userRequest);

    UserResponse getUserById(String userId);

    UserResponse updateUser(String userId, UserUpdateRequest userUpdateRequest);

    void deleteUser(String userId);

    UserResponse getMyInformation();
    List<UserResponse> getAllUsers();
}
