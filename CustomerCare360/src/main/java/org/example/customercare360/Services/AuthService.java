
package org.example.customercare360.Services;

import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Exception.*;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        //this.customerRepository = customerRepository;
    }

    public User createUser (RegisterRequest request){
        if(request.getRole()==Role.USER){
            Customer customer = new Customer();
            customer.setCustomerType(request.getCustomerType());
            customer.setContactInfo(request.getPhone());
            customer.setStatus(CustomerStatus.ACTIVE);
            return customer;
        }else{
            return new User();
        }
    }

    public AuthResponse register(RegisterRequest request)throws EmailExists,UserNameExists,NullCustomerType{
        if(userRepository.existsByEmail(request.getEmail())) throw new EmailExists("User already registered using this email");
        if(userRepository.existsByUsername(request.getUsername())) throw new UserNameExists("UserName is already registered");
        if(request.getRole() == Role.USER && request.getCustomerType()==null) throw new NullCustomerType("CustomerType cant be null!!");
        User user= createUser(request);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        return new AuthResponse("Registered Successfully",jwtService.generateToken(user),user.getRole().name());

    }

    public AuthResponse login (LoginRequest request) throws UserNotFound,PasswordInValid{
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            User user =(User) authentication.getPrincipal();
            return new AuthResponse("Logged In Successfull",jwtService.generateToken(user),user.getRole().name());
        }catch (BadCredentialsException ex){
            throw new PasswordInValid(ex.getMessage());
        }catch (UserNotFound ex){
            throw new UserNotFound(ex.getMessage());
        }

    }
}
