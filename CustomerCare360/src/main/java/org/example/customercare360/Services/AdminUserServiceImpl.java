package org.example.customercare360.Services;

import org.example.customercare360.DTO.AddUserDTO;
import org.example.customercare360.DTO.UpdateUserDTO;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl
        implements AdminUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(AddUserDTO request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setUserName(request.getUserName());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {

        return userRepository
                .findById(userId)
                .orElse(null);
    }

    @Override
    public User updateUser(
            Integer userId,
            UpdateUserDTO request) {

        User user =
                userRepository.findById(userId)
                        .orElse(null);

        if (user == null) {
            return null;
        }

        user.setRole(request.getRole());

        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Integer userId) {

        User user =
                userRepository.findById(userId)
                        .orElse(null);

        if (user == null) {
            return "User Not Found";
        }

        userRepository.delete(user);

        return "User deleted successfully";
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}