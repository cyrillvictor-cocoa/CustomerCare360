package org.example.customercare360.Controller;

import org.example.customercare360.Entity.User;
import org.example.customercare360.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping
    public User createProfile(@RequestBody User user) {
        return profileService.createProfile(user);
    }

    @GetMapping("/{userId}")
    public User getProfile(@PathVariable Integer userId) {
        return profileService.getProfile(userId);
    }

    @PutMapping("/{userId}")
    public User updateProfile(
            @PathVariable Integer userId,
            @RequestBody User user) {

        return profileService.updateProfile(userId, user);
    }
}