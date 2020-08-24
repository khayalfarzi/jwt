package az.company.jwt.security.service;

import az.company.jwt.payload.request.LoginRequest;
import az.company.jwt.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    ResponseEntity<?> save(SignupRequest signupRequest);

    ResponseEntity<?> login(LoginRequest loginRequest);
}