package org.example.customercare360.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
@NoArgsConstructor
public class RegisterRequest {


    @JsonProperty(required = true)
    @NotBlank
    private String name;
    @JsonProperty(required = true)
    @NotBlank
    private String email;
    @JsonProperty(required = true)
    @NotBlank
    private String phone;
    @JsonProperty(required = true)
    @NotBlank
    private String username;
    @JsonProperty(required = true)
    @NotBlank
    private String password;
    @JsonProperty(required = true)
    @NotNull
    private Role role;

    private CustomerType customerType;

    @AssertTrue(message="CustomerType is required when role is User")
    public boolean isCustomerTypeValid(){
        if(role == Role.USER){
            return customerType!=null;
        }
        return true;
    }


}