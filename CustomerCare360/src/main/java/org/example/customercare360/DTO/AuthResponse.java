package org.example.customercare360.DTO;

public class AuthResponse {

    private String message;
    private String token;
    private String role;

    public AuthResponse(String msg, String token, String role) {
        this.message = msg;
        this.token = token;
        this.role = role;
    }

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole(){return role;}

    public void setRole(String role){this.role = role;}
}