
package org.example.customercare360.Services;
import org.apache.coyote.Response;
import org.example.customercare360.DTO.AuthResponse;
import org.example.customercare360.DTO.LoginRequest;
import org.example.customercare360.DTO.RegisterRequest;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;
import org.example.customercare360.Enums.Role;
import org.example.customercare360.Exception.*;
import org.example.customercare360.Repository.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, CustomerRepository customerRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
    }

    public AuthResponse register(RegisterRequest request)throws EmailExists,UserNameExists,NullCustomerType{
            if(userRepository.existsByEmail(request.getEmail())) throw new EmailExists("User already registered using this email");
            if(userRepository.existsByUserName(request.getUserName())) throw new UserNameExists("UserName is already registered");
            if(request.getRole() == Role.USER && request.getCustomerType()==null) throw new NullCustomerType("CustomerType cant be null!!");
            User user  = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setUserName(request.getUserName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRole(request.getRole());
            userRepository.save(user);
            if(request.getRole()==Role.USER){
                Customer customer = new Customer();
                customer.setCustomerType(request.getCustomerType());
                customer.setContactInfo(request.getEmail());
                customer.setStatus(CustomerStatus.ACTIVE);
                customer.setUser(user);
                customerRepository.save(customer);
            }
            return new AuthResponse("Registered Successfully",jwtService.generateToken(user),user.getRole().name());

    }

    public AuthResponse login (LoginRequest request) throws UserNameNotFound,PasswordInValid{
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
            User user =(User) authentication.getPrincipal();
            return new AuthResponse("Logged In Successfull",jwtService.generateToken(user),user.getRole().name());
        }catch (BadCredentialsException ex){
            throw new PasswordInValid(ex.getMessage());
        }catch (UserNameNotFound ex){
            throw new UserNameNotFound(ex.getMessage());
        }

    }
}
