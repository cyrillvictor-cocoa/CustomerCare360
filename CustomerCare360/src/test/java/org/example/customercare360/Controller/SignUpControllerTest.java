package org.example.customercare360.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Services.AuthService;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SignUpController.class)
@AutoConfigureMockMvc(addFilters = false)
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter authFilter;
    @Test
    void signup_ShouldReturnAuthResponse_WhenRequestIsValid_WithCustomerTypeNotNeeded() throws Exception {

        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setName("john");
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("Password123");
        request.setPhone("34823741293");
        request.setRole(Role.ADMIN);


        AuthResponse authResponse = new AuthResponse("Registered Successfully","jwt-token","USER");

        Mockito.when(authService.register(any(RegisterRequest.class)))
                .thenReturn(authResponse);

        // Act & Assert
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    void signup_ShouldReturnAuthResponse_WhenRequestIsValid_WithCustomerTypeNeeded() throws Exception {

        // Arrange
        RegisterRequest request = new RegisterRequest();
        request.setName("john");
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("Password123");
        request.setPhone("34823741293");
        request.setRole(Role.USER);
        request.setCustomerType(CustomerType.COMMERCIAL);


        AuthResponse authResponse = new AuthResponse("Registered Successfully","jwt-token","USER");

        Mockito.when(authService.register(any(RegisterRequest.class)))
                .thenReturn(authResponse);

        // Act & Assert
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    void signup_ShouldReturnBadRequest_WhenRequestIsInvalid() throws Exception {

        // Empty request to trigger validation failure
        RegisterRequest request = new RegisterRequest();

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signup_ShouldReturnBadRequest_WhenCustomerTypeIsNullForUser() throws Exception {

        // Empty request to trigger validation failure
        RegisterRequest request = new RegisterRequest();
        request.setName("john");
        request.setUsername("john");
        request.setEmail("john@example.com");
        request.setPassword("Password123");
        request.setPhone("34823741293");
        request.setRole(Role.USER);
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signup_ShouldReturnBadRequest_WhenRoleIsInvalid() throws Exception {

        // Empty request to trigger validation failure
        String requestJson = """
        {
        "name":"john",
        "username":"john",
        "email":"john@example.com",
        "password":"Password123",
        "phone":"34823741293",
        "role":"GG"
        }
    """;
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
    @Test
    void signup_ShouldReturnBadRequest_WhenCustomerTypeIsInvalid() throws Exception {

        // Empty request to trigger validation failure
        String requestJson = """
        {
        "name":"john",
        "username":"john",
        "email":"john@example.com",
        "password":"Password123",
        "phone":"34823741293",
        "role":"User".
        "customerType": "GG"
        }
    """;
        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}

