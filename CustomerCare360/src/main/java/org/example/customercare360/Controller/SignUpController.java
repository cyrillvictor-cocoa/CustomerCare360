package org.example.customercare360.Controller;

import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.Services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/signup")
public class SignUpController {

    private AuthService authService;

    public SignUpController(AuthService authService){this.authService = authService;}

    @PostMapping
    public ResponseEntity<AuthResponse> signup(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
