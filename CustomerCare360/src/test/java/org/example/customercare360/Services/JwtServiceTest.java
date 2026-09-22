package org.example.customercare360.Services;

import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.test.util.ReflectionTestUtils;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private JwtDecoder jwtDecoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JwtService jwtService;

//    @BeforeEach
//    void setUp() {
//
//        jwtService = new JwtService(jwtEncoder,jwtDecoder,userRepository);
//
//        ReflectionTestUtils.setField(
//                jwtService,
//                "secret",
//                "VGhpc0lzQVN1cGVyU2VjcmV0S2V5VGhhdElzQXRMZWFzdDMyQ2hhcnM="
//        );
//
//        ReflectionTestUtils.setField(
//                jwtService,
//                "expiration",
//                86400000L
//        );
//    }


    @Test
    void generateToken_ShouldGenerateValidToken() {

        User user = new User();
        user.setUsername("victor");
        user.setRole(Role.ADMIN);

        Jwt jwt = mock(Jwt.class);

        when(jwtEncoder.encode(any(JwtEncoderParameters.class)))
                .thenReturn(jwt);

        when(jwt.getTokenValue())
                .thenReturn("dummy-token");

        String token = jwtService.generateToken(user);

        assertNotNull(token);
        assertEquals("dummy-token", token);
    }

    @Test
    void extractUserName_ShouldReturnUsername() {

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "RS256")
                .subject("victor")
                .claim("role", "ADMIN")
                .build();

        when(jwtDecoder.decode("token")).thenReturn(jwt);

        String username = jwtService.extractUsername("token");

        assertEquals("victor", username);
    }

    @Test
    void extractRole_ShouldReturnRole() {

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "RS256")
                .subject("victor")
                .claim("role", "ADMIN")
                .build();

        when(jwtDecoder.decode("token")).thenReturn(jwt);

        assertEquals("ADMIN", jwtService.extractRole("token"));
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {

        Jwt jwt = Jwt.withTokenValue("token")
                .header("alg", "RS256")
                .subject("victor")
                .build();

        when(jwtDecoder.decode("token")).thenReturn(jwt);

        assertTrue(jwtService.validateToken("token"));
    }
}