package org.example.customercare360.Controller;

import org.example.customercare360.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }



    @GetMapping("/validate")
    public ResponseEntity<String> validate() {
        return ResponseEntity.ok("Valid token");
    }

}
