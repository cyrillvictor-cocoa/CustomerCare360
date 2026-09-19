package org.example.customercare360.Services;

import org.example.customercare360.DTO.ProfileRequest;
import org.example.customercare360.DTO.ProfileResponse;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Repository.CustomerRepository;
import org.example.customercare360.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ProfileService profileService;

    @Test
    void testGetAllProfiles() {

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setName("Virat");

        users.add(user);

        when(userRepository.findAll())
                .thenReturn(users);

        List<ProfileResponse> result =
                profileService.getAllProfiles();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(userRepository,
                times(1))
                .findAll();
    }

    @Test
    void testGetProfile() {

        User user = new User();
        user.setName("Virat");

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        ProfileResponse response =
                profileService.getProfile(1);

        assertNotNull(response);
        assertEquals(
                "Virat",
                response.getName());

        verify(userRepository,
                times(1))
                .findById(1);
    }

    @Test
    void testUpdateProfile() {

        Customer customer = new Customer();

        ProfileRequest request =
                new ProfileRequest();

        request.setName("Updated Virat");
        request.setPassword("password123");

        when(customerRepository.findById(1))
                .thenReturn(Optional.of(customer));

        when(passwordEncoder.encode(any(String.class)))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(Customer.class)))
                .thenReturn(customer);

        ProfileResponse response =
                profileService.updateProfile(
                        1,
                        request);

        assertNotNull(response);

        verify(customerRepository,
                times(1))
                .findById(1);

        verify(userRepository,
                times(1))
                .save(any(Customer.class));
    }

    @Test
    void testDeleteProfile() {

        Customer customer =
                new Customer();

        when(customerRepository.findById(1))
                .thenReturn(Optional.of(customer));

        profileService.deleteProfile(1);

        verify(customerRepository,
                times(1))
                .findById(1);

        verify(userRepository,
                times(1))
                .delete(customer);
    }
}