package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private String message;
    private String token;
    private String role;

    public AuthResponse(String msg, String token, String role) {
        this.message = msg;
        this.token = token;
        this.role = role;
    }

}