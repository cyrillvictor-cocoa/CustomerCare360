package org.example.customercare360.Services;

import org.example.customercare360.DTO.AddUserDTO;
import org.example.customercare360.DTO.UpdateUserDTO;
import org.example.customercare360.Entity.User;

import java.util.List;

public interface AdminUserService {

    User addUser(AddUserDTO request);

    User getUserById(Integer userId);

    User updateUser(
            Integer userId,
            UpdateUserDTO request);

    String deleteUser(Integer userId);

    List<User> getAllUsers();
}
