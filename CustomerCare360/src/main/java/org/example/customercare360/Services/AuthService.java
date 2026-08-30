
package org.example.customercare360.Services;
import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Exception.EmailExists;
import org.example.customercare360.Exception.PasswordInValid;
import org.example.customercare360.Exception.UserNameExists;
import org.example.customercare360.Exception.UserNameNotFound;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public Optional<String> register(RegisterRequest request)throws EmailExists,UserNameExists{
            if(userRepository.existsByEmail(request.getEmail())) throw new EmailExists("User already registered using this email");
            if(userRepository.existsByUserName(request.getUserName())) throw new UserNameExists("UserName is already registered");
            User user  = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);

            return Optional.of(jwtService.generateToken(user.getUserName()));

    }

    public String login (LoginRequest request) throws UserNameNotFound,PasswordInValid{
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
            User user =(User) authentication.getPrincipal();
            return jwtService.generateToken(user.getUserName());
        }catch (BadCredentialsException ex){
            throw new PasswordInValid(ex.getMessage());
        }catch (UserNameNotFound ex){
            throw new UserNameNotFound(ex.getMessage());
        }

    }
}
