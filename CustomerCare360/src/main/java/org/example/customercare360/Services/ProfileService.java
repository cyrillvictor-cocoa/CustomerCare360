package org.example.customercare360.Services;

import org.example.customercare360.Entity.User;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    // CREATE PROFILE
    public User createProfile(User user) {
        return userRepository.save(user);
    }

    // VIEW PROFILE
    public User getProfile(Integer userId) {

        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId));
    }

    // UPDATE PROFILE
    public User updateProfile(
            Integer userId,
            User updatedUser) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + userId));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setModifiedBy(updatedUser.getModifiedBy());

        return userRepository.save(existingUser);
    }
}