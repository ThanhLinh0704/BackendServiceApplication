package linhlt.project.backend_service.service;

import com.nimbusds.jose.JOSEException;
import linhlt.project.backend_service.dto.request.AuthenticationRequest;
import linhlt.project.backend_service.dto.request.IntrospectRequest;
import linhlt.project.backend_service.dto.request.LogoutRequest;
import linhlt.project.backend_service.dto.response.AuthenticationResponse;
import linhlt.project.backend_service.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    IntrospectResponse introspectToken(IntrospectRequest authenticationRequest) throws JOSEException, ParseException;
    void logout(LogoutRequest logoutRequest) throws ParseException, JOSEException;
}
