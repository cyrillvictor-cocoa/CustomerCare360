package org.example.customercare360.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.Exception.PasswordInValid;
import org.example.customercare360.Exception.UserNotFound;
import org.example.customercare360.Services.AuthService;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;
    @Test
    void login_ShouldReturnAuthResponse_WhenRequestIsValid() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setUsername("john");
        request.setPassword("Password123");

        AuthResponse response = new AuthResponse("Login Succuesfull","jwt-token","Admin");


        Mockito.when(authService.login(any(LoginRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    void login_ShouldReturnBadRequest_WhenRequestIsInvalid() throws Exception {

        LoginRequest request = new LoginRequest();

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
    @Test
    void login_ShouldReturnUnauthorized_WhenUsernameIsInvalid() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setUsername("wronguser");
        request.setPassword("Password123");

        Mockito.when(authService.login(any(LoginRequest.class)))
                .thenThrow(new UserNotFound("Invalid username"));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }
    @Test
    void login_ShouldReturnUnauthorized_WhenPasswordIsInvalid() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setUsername("john");
        request.setPassword("wrongpassword");

        Mockito.when(authService.login(any(LoginRequest.class)))
                .thenThrow(new PasswordInValid("Invalid password"));

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}