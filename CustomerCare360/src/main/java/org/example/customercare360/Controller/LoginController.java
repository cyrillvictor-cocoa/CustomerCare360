package org.example.customercare360.Controller;

import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {

    private AuthService authService;

    public LoginController(AuthService authService){this.authService = authService;}

    @PostMapping
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));

    }
}
