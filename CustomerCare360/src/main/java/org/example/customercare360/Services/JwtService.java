package org.example.customercare360.Services;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Exception.InvalidToken;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;
    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder,UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
    }

    public String generateToken(User user) {

        Instant now = Instant.now();
        String username = user.getUsername()!=null?user.getUsername(): user.getEmail();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(username)
                .claim("role", user.getRole())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(1800))
                .build();

        JwsHeader header = JwsHeader.with(SignatureAlgorithm.RS256)
                .build();

        JwtEncoderParameters parameters =
                JwtEncoderParameters.from(header, claims);

        return jwtEncoder
                .encode(parameters)
                .getTokenValue();
    }


    // Validate JWT
    public boolean validateToken(String token) {

        try {

            jwtDecoder.decode(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {

            return false;
        }
    }


    // Extract username
    public String extractUsername(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        return jwt.getSubject();
    }


    // Extract role
    public String extractRole(String token) {

        Jwt jwt = jwtDecoder.decode(token);

        return jwt.getClaimAsString("role");
    }
}