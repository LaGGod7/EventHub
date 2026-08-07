package org.gd.eventhub.Controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gd.eventhub.Services.AuthService;
import org.gd.eventhub.dto.Requests.LoginRequest;
import org.gd.eventhub.dto.Requests.RegisterRequest;
import org.gd.eventhub.dto.Response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static java.lang.System.out;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request)
    {     System.out.println("REGISTER CONTROLLER HIT");
        authService.register(request);
        return new ResponseEntity<>( "User Registered Successfully", HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody LoginRequest request)
    {
        return  ResponseEntity.ok(authService.Login(request));

    }

}
