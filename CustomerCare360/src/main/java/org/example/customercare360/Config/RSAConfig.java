package org.example.customercare360.Config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class RSAConfig {

    private final RSAPublicKey publicKey;
    private final RSAPrivateKey privateKey;

    public RSAConfig() throws Exception {

        privateKey = loadPrivateKey();
        publicKey = loadPublicKey();
    }

    @Bean
    public JwtEncoder jwtEncoder() {

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .build();

        JWKSource<SecurityContext> jwkSource =
                new ImmutableJWKSet<>(
                        new JWKSet(rsaKey)
                );

        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JwtDecoder jwtDecoder() {

        return NimbusJwtDecoder
                .withPublicKey(publicKey)
                .build();
    }

    private RSAPrivateKey loadPrivateKey() throws Exception {

        ClassPathResource resource =
                new ClassPathResource("keys/private_key.pem");

        String key = new String(
                resource.getInputStream().readAllBytes()
        );

        key = key
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory factory =
                KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) factory
                .generatePrivate(spec);
    }

    private RSAPublicKey loadPublicKey() throws Exception {

        ClassPathResource resource =
                new ClassPathResource("keys/public_key.pem");

        String key = new String(
                resource.getInputStream().readAllBytes()
        );

        key = key
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);

        X509EncodedKeySpec spec =
                new X509EncodedKeySpec(keyBytes);

        KeyFactory factory =
                KeyFactory.getInstance("RSA");

        return (RSAPublicKey) factory
                .generatePublic(spec);
    }
}
