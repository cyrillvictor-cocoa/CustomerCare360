package org.example.customercare360.Controller;

import java.util.List;

import org.example.customercare360.DTO.ProfileRequest;
import org.example.customercare360.DTO.ProfileResponse;
import org.example.customercare360.Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    // GET ALL
    @GetMapping
    public List<ProfileResponse> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    // GET BY ID
    @GetMapping("/{userId}")
    public ProfileResponse getProfile(
            @PathVariable Integer userId) {

        return profileService.getProfile(userId);
    }

    // UPDATE
    @PutMapping("/{userId}")
    public ProfileResponse updateProfile(
            @PathVariable Integer userId,
            @RequestBody ProfileRequest request) {

        return profileService.updateProfile(
                userId,
                request);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public String deleteProfile(
            @PathVariable Integer userId) {

        profileService.deleteProfile(userId);

        return "Profile deleted successfully";
    }
}