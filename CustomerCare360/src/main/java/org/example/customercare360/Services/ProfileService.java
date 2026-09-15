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
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

        User existingUser =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id: "
                                                + userId));

        // USER TABLE UPDATE
        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setUserName(request.getUserName());
        existingUser.setRole(request.getRole());

        User updatedUser =
                userRepository.save(existingUser);

        // CUSTOMER TABLE UPDATE
        Customer customer =
                customerRepository.findByUser(updatedUser)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Customer not found for User Id: "
                                                + userId));

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

        customerRepository.save(customer);

        return mapToResponse(updatedUser);
    }

    // DELETE
    public void deleteProfile(Integer userId) {

        User existingUser =
                userRepository.findById(userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id: "
                                                + userId));

        Customer customer =
                customerRepository.findByUser(existingUser)
                        .orElse(null);

        if (customer != null) {
            customerRepository.delete(customer);
        }

        userRepository.delete(existingUser);
    }

    // DTO MAPPER
    private ProfileResponse mapToResponse(User user) {

        ProfileResponse response =
                new ProfileResponse();

        response.setUserId(user.getUserId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setUserName(user.getUserName());
        response.setRole(user.getRole());

        return response;
    }
}