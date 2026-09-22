package org.example.customercare360.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonIgnoreProperties
public class LoginRequest {

    @JsonProperty(required = true)
    @NotNull
    private String username;
    @JsonProperty(required = true)
    @NotNull
    private String password;

    public LoginRequest() {
    }

}