package org.example.customercare360.Controller;

import org.example.customercare360.DTO.AddUserDTO;
import org.example.customercare360.DTO.UpdateUserDTO;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Services.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping
    public User addUser(
            @RequestBody AddUserDTO request) {

        return adminUserService.addUser(request);
    }

    @GetMapping("/{userId}")
    public User getUserById(
            @PathVariable Integer userId) {

        return adminUserService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public User updateUser(
            @PathVariable Integer userId,
            @RequestBody UpdateUserDTO request) {

        return adminUserService.updateUser(
                userId,
                request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(
            @PathVariable Integer userId) {

        return adminUserService.deleteUser(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {

        return adminUserService.getAllUsers();
    }
}