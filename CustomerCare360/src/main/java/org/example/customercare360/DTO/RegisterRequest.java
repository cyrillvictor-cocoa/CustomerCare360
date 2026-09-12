package org.example.customercare360.DTO;


import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;

@Getter
@Setter
public class RegisterRequest {

    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
    private Role role;
    private CustomerType customerType;

    public RegisterRequest() {
    }
}