package org.example.customercare360.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.customercare360.DTO.ProfileRequest;
import org.example.customercare360.DTO.ProfileResponse;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.CustomerRepository;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET ALL
    public List<ProfileResponse> getAllProfiles() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ProfileResponse getProfile(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId));

        return mapToResponse(user);
    }

    // UPDATE USER + CUSTOMER
    public ProfileResponse updateProfile(
            Integer userId,
            ProfileRequest request) {

        Customer customer =
                customerRepository.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id: "
                                                + userId));

        // USER TABLE UPDATE
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setUsername(request.getUserName());
        customer.setRole(request.getRole());

        customer.setContactInfo(
                request.getContactInfo());

        if (request.getCustomerType() != null) {
            customer.setCustomerType(
                    request.getCustomerType());
        }

        if (request.getStatus() != null) {
            customer.setStatus(
                    request.getStatus());
        }

        // PASSWORD UPDATE
        if (request.getPassword() != null
                && !request.getPassword().isBlank()) {

            customer.setPassword(
                    passwordEncoder.encode(
                            request.getPassword()));
        }

        User updatedUser =
                userRepository.save(customer);

        return mapToResponse(updatedUser);
    }

    // DELETE
    public void deleteProfile(Integer userId) {

        Customer customer =
                customerRepository.findById(userId)
                        .orElse(null);

        userRepository.delete(customer);
    }

    // DTO MAPPER
    private ProfileResponse mapToResponse(User user) {

        ProfileResponse response =
                new ProfileResponse();

        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setUserName(user.getUsername());
        response.setRole(user.getRole());

        return response;
    }
}