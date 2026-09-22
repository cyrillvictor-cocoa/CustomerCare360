package org.example.customercare360.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;

@Getter
@Setter
public class ProfileRequest {


    private String name;
    private String email;
    private String phone;
    private String userName;
    private String password; // ADD THIS
    private Role role;
    private String contactInfo;
    private CustomerType customerType;
    private CustomerStatus status;



}