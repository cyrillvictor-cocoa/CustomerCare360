package org.example.customercare360.Services;

import static org.junit.jupiter.api.Assertions.*;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Exception.EmailExists;
import org.example.customercare360.Exception.NullCustomerType;
import org.example.customercare360.Exception.UserNameExists;
import org.example.customercare360.Exception.UserNotFound;
import org.example.customercare360.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Spy
    @InjectMocks
    private AuthService authService;

    @Test
    void register_Success() throws Exception {

        RegisterRequest request = new RegisterRequest();
        request.setName("Victor");
        request.setUsername("victor123");
        request.setEmail("victor@test.com");
        request.setPassword("password");
        request.setPhone("9876543210");
        request.setRole(Role.ADMIN);

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(userRepository.existsByUsername(request.getUsername()))
                .thenReturn(false);

        when(passwordEncoder.encode("password"))
                .thenReturn("encodedPassword");

        when(jwtService.generateToken(any(User.class)))
                .thenReturn("jwt-token");

        AuthResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals("Registered Successfully", response.getMessage());
        assertEquals("jwt-token", response.getToken());
        assertEquals("ADMIN", response.getRole());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_EmailAlreadyExists() {

        RegisterRequest request = new RegisterRequest();
        request.setEmail("victor@test.com");

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(true);

        assertThrows(
                EmailExists.class,
                () -> authService.register(request)
        );

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_UsernameAlreadyExists() {

        RegisterRequest request = new RegisterRequest();
        request.setEmail("victor@test.com");
        request.setUsername("victor123");

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(userRepository.existsByUsername(request.getUsername()))
                .thenReturn(true);

        assertThrows(
                UserNameExists.class,
                () -> authService.register(request)
        );

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_NullCustomerType() {

        RegisterRequest request = new RegisterRequest();
        request.setEmail("victor@test.com");
        request.setUsername("victor123");
        request.setRole(Role.USER);
        request.setCustomerType(null);

        when(userRepository.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(userRepository.existsByUsername(request.getUsername()))
                .thenReturn(false);

        assertThrows(
                NullCustomerType.class,
                () -> authService.register(request)
        );

        verify(userRepository, never()).save(any());
    }

    @Test
    void register_UserRoleWithCustomerType_Success() throws Exception {

        RegisterRequest request = new RegisterRequest();
        request.setName("Victor");
        request.setUsername("victor123");
        request.setEmail("victor@test.com");
        request.setPassword("password");
        request.setPhone("9876543210");
        request.setRole(Role.USER);
        request.setCustomerType(CustomerType.RESIDENTIAL);

        when(userRepository.existsByEmail(anyString()))
                .thenReturn(false);

        when(userRepository.existsByUsername(anyString()))
                .thenReturn(false);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(jwtService.generateToken(any(User.class)))
                .thenReturn("jwt-token");

        AuthResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals("Registered Successfully", response.getMessage());

        verify(userRepository).save(any(User.class));
    }
    @Test
    void createUser_WhenRoleUser_ReturnsCustomer() {

        RegisterRequest request = new RegisterRequest();
        request.setRole(Role.USER);
        request.setCustomerType(CustomerType.COMMERCIAL);
        request.setPhone("9876543210");

        User user = authService.createUser(request);

        assertNotNull(user);
        assertTrue(user instanceof Customer);

        Customer customer = (Customer) user;
        assertEquals(CustomerType.COMMERCIAL, customer.getCustomerType());
        assertEquals("9876543210", customer.getContactInfo());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    void createUser_WhenRoleAdmin_ReturnsUser() {

        RegisterRequest request = new RegisterRequest();
        request.setRole(Role.ADMIN);

        User user = authService.createUser(request);

        assertNotNull(user);
        assertFalse(user instanceof Customer);
    }

    @Test
    void login_Success() throws Exception {

        LoginRequest request = new LoginRequest();
        request.setUsername("victor");
        request.setPassword("password");

        User user = new User();
        user.setUsername("victor");
        user.setRole(Role.ADMIN);

        Authentication authentication = mock(Authentication.class);

        when(authentication.getPrincipal())
                .thenReturn(user);

        when(authenticationManager.authenticate(
                any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtService.generateToken(user))
                .thenReturn("jwt-token");

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("Logged In Successfull", response.getMessage());
        assertEquals("jwt-token", response.getToken());
        assertEquals("ADMIN", response.getRole());

        verify(authenticationManager, times(1))
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void login_UserNotFound() {

        LoginRequest request = new LoginRequest();
        request.setUsername("victor");
        request.setPassword("password");

        when(authenticationManager.authenticate(
                any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UserNotFound("User not found"));

        assertThrows(
                UserNotFound.class,
                () -> authService.login(request)
        );
    }

}