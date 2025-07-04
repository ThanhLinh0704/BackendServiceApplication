package linhlt.project.backend_service.controller;

import com.nimbusds.jose.JOSEException;
import io.swagger.v3.oas.annotations.tags.Tag;
import linhlt.project.backend_service.dto.request.AuthenticationRequest;
import linhlt.project.backend_service.dto.request.IntrospectRequest;
import linhlt.project.backend_service.dto.request.LogoutRequest;
import linhlt.project.backend_service.dto.response.ApiResponse;
import linhlt.project.backend_service.dto.response.AuthenticationResponse;
import linhlt.project.backend_service.dto.response.IntrospectResponse;
import linhlt.project.backend_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name ="AUTHENTICATION")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest introspectRequest)
            throws ParseException, JOSEException {
        var result = authenticationService.introspectToken(introspectRequest);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest logoutRequest)
            throws ParseException, JOSEException {
        authenticationService.logout(logoutRequest);
        return ApiResponse.<Void>builder().build();
    }
}
