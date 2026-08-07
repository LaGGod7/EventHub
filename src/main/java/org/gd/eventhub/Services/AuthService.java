package org.gd.eventhub.Services;

import lombok.RequiredArgsConstructor;

import org.gd.eventhub.Entity.Role;
import org.gd.eventhub.Entity.User;
import org.gd.eventhub.Repository.UserRepository;
import org.gd.eventhub.dto.Requests.LoginRequest;
import org.gd.eventhub.dto.Requests.RegisterRequest;
import org.gd.eventhub.dto.Response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .phoneNo(request.getPhoneNumber())
                .role(Role.USER)
                .enabled(true).build();
        userRepository.save(user);


    }


    public AuthenticationResponse Login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("email not found"));
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);



    }


}
