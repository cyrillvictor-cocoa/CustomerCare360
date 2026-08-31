package org.example.customercare360.Controller;

import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.DTO.Response;
import org.example.customercare360.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(
            @RequestBody RegisterRequest request) {
        try{
            Optional<String> token =  authService.register(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new Response("Registered Successfully",token.get()));

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response(e.getMessage(),null));
        }


    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("Valid token");
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(
            @RequestBody LoginRequest request) {
        System.out.println("Login method called");
            String token = authService.login(request);

            return ResponseEntity.ok(
                    new Response(
                            "Login successful",
                            token
                    )
            );

    }
}
