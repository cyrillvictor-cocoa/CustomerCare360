package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ProfileRequest;
import org.example.customercare360.DTO.ProfileResponse;
import org.example.customercare360.Services.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileControllerTest {

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @Test
    void testGetAllProfiles() {

        List<ProfileResponse> profiles = new ArrayList<>();

        ProfileResponse response = new ProfileResponse();
        response.setName("Virat");

        profiles.add(response);

        when(profileService.getAllProfiles())
                .thenReturn(profiles);

        List<ProfileResponse> result =
                profileController.getAllProfiles();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(profileService,
                times(1))
                .getAllProfiles();
    }

    @Test
    void testGetProfileById() {

        ProfileResponse response =
                new ProfileResponse();

        response.setName("Virat");

        when(profileService.getProfile(1))
                .thenReturn(response);

        ProfileResponse result =
                profileController.getProfile(1);

        assertNotNull(result);
        assertEquals(
                "Virat",
                result.getName());

        verify(profileService,
                times(1))
                .getProfile(1);
    }

    @Test
    void testUpdateProfile() {

        ProfileRequest request =
                new ProfileRequest();

        request.setName("Updated Virat");

        ProfileResponse response =
                new ProfileResponse();

        response.setName("Updated Virat");

        when(profileService.updateProfile(
                eq(1),
                any(ProfileRequest.class)))
                .thenReturn(response);

        ProfileResponse result =
                profileController.updateProfile(
                        1,
                        request);

        assertNotNull(result);
        assertEquals(
                "Updated Virat",
                result.getName());

        verify(profileService,
                times(1))
                .updateProfile(
                        eq(1),
                        any(ProfileRequest.class));
    }

    @Test
    void testDeleteProfile() {

        doNothing().when(profileService)
                .deleteProfile(1);

        String result =
                profileController.deleteProfile(1);

        assertEquals(
                "Profile deleted successfully",
                result);

        verify(profileService,
                times(1))
                .deleteProfile(1);
    }
}
