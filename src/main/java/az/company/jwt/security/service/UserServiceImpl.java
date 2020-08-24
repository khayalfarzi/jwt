package az.company.jwt.security.service;

import az.company.jwt.enums.Language;
import az.company.jwt.model.ERole;
import az.company.jwt.model.Role;
import az.company.jwt.model.User;
import az.company.jwt.payload.request.LoginRequest;
import az.company.jwt.payload.request.SignupRequest;
import az.company.jwt.payload.response.JwtResponse;
import az.company.jwt.payload.response.MessageResponse;
import az.company.jwt.repository.RoleRepository;
import az.company.jwt.repository.UserRepository;
import az.company.jwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> save(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (userRepository.existsByPassportId(signupRequest.getPassportId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Passport Id is already in use!"));
        }
        // Create new user's account
        User user = User.builder()
                .address(signupRequest.getAddress())
                .email(signupRequest.getEmail())
                .mobileNumber(signupRequest.getMobileNumber())
                .name(signupRequest.getName())
                .passportId(signupRequest.getPassportId())
                .password(encoder.encode(signupRequest.getPassword()))
                .paymentCards(signupRequest.getPaymentCards())
                .surname(signupRequest.getSurname())
                .username(signupRequest.getUsername())
                .build();
        if (signupRequest.getLanguage() == null) {
            user.setLanguage(Language.EN);
        }
        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        userRepository.setUserInfoById(LocalDateTime.now(), userDetails.getUsername());
        return ResponseEntity.ok(
                JwtResponse.builder()
                        .address(userDetails.getAddress())
                        .id(userDetails.getId())
                        .email(userDetails.getEmail())
                        .language(userDetails.getLanguage())
                        .mobileNumber(userDetails.getMobileNumber())
                        .name(userDetails.getName())
                        .surname(userDetails.getSurname())
                        .passportId(userDetails.getPassportId())
                        .paymentCards(userDetails.getPaymentCards())
                        .roles(roles)
                        .token(jwt)
                        .type("Bearer")
                        .username(userDetails.getUsername())
                        .build());
    }
}
