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
import linhlt.project.backend_service.model.UserEntity;
import linhlt.project.backend_service.repository.UserRepository;
import linhlt.project.backend_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.secretkey}")
    protected String SIGNER_KEY;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername());
        boolean result = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!result) {
            throw new AppException(ErrorCode.UNAUTHENTICATION);
        }
        return AuthenticationResponse.builder()
                .token(genarationToken(user))
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

    private String genarationToken(UserEntity userEntity) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userEntity.getUsername())
                .issuer("linhlt.fpt")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(userEntity))
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

    private String buildScope(UserEntity userEntity) {
        StringJoiner scopeJoiner = new StringJoiner(" ");
        if (userEntity.getRoles() != null) {
            userEntity.getRoles().forEach(role -> {scopeJoiner.add("ROLE_" + role.getName());
            role.getPermissions().forEach(permission -> {scopeJoiner.add(permission.getName());});
            });

        }
        return scopeJoiner.toString();
    }
}
