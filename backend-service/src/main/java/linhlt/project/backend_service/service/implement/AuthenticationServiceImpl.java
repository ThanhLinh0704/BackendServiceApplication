package linhlt.project.backend_service.service.implement;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import linhlt.project.backend_service.dto.request.AuthenticationRequest;
import linhlt.project.backend_service.dto.request.IntrospectRequest;
import linhlt.project.backend_service.dto.response.AuthenticationResponse;
import linhlt.project.backend_service.dto.response.IntrospectResponse;
import linhlt.project.backend_service.exception.AppException;
import linhlt.project.backend_service.exception.ErrorCode;
import linhlt.project.backend_service.repository.UserRepository;
import linhlt.project.backend_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    private static final String SIGNER_KEY = "W77hqLWGSWhmCmLXVR3UdhOTnPKeZr+D6huzU08+ieB2xPRx2LBU8KlAkIaunlqc";

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername());
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!result) {
            throw new AppException(ErrorCode.UNAUTHENTICATION);
        }
        return AuthenticationResponse.builder()
                .token(genarationToken(authenticationRequest.getUsername()))
                .authenticated(true)
                .build();
    }

    @Override
    public IntrospectResponse introspectToken(IntrospectRequest authenticationRequest)
            throws JOSEException, ParseException {
        var token = authenticationRequest.getToken();
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        var verified = signedJWT.verify(verifier);
        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
        return IntrospectResponse.builder()
                .valid( verified && expiration.after(new Date()))
                .build();
    }

    private String genarationToken(String username) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("linhlt.fpt")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("CustomClaim", "CustomClaimValue")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot sign JWT object", e);
            throw new RuntimeException(e);
        }
    }
}
