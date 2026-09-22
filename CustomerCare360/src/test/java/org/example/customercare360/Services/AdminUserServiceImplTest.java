package org.example.customercare360.Services;

import org.example.customercare360.DTO.AddUserDTO;
import org.example.customercare360.DTO.UpdateUserDTO;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.customercare360.Enums.Role;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminUserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminUserServiceImpl adminUserService;

    @Test
    void testAddUser() {

        AddUserDTO dto = new AddUserDTO();
        dto.setName("John");
        dto.setEmail("john@test.com");
        dto.setPhone("9876543210");
        dto.setusername("john123");
        dto.setPassword("password");
        dto.setRole(Role.valueOf("ADMIN"));

        User savedUser = new User();
        savedUser.setName("John");

        when(passwordEncoder.encode("password"))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(savedUser);

        User result = adminUserService.addUser(dto);

        assertNotNull(result);
        assertEquals("John", result.getName());

        verify(passwordEncoder, times(1))
                .encode("password");
        verify(userRepository, times(1))
                .save(any(User.class));
    }

    @Test
    void testGetUserById_UserExists() {

        User user = new User();
        user.setUserId(1);
        user.setName("John");

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        User result = adminUserService.getUserById(1);

        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals("John", result.getName());

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void testGetUserById_UserNotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        User result = adminUserService.getUserById(1);

        assertNull(result);

        verify(userRepository, times(1))
                .findById(1);
    }

    @Test
    void testUpdateUser_Success() {

        User existingUser = new User();
        existingUser.setUserId(1);
        existingUser.setRole(Role.valueOf("USER"));

        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setRole(Role.valueOf("ADMIN"));

        when(userRepository.findById(1))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.save(any(User.class)))
                .thenReturn(existingUser);

        User result = adminUserService.updateUser(1, dto);

        assertNotNull(result);
        assertEquals(Role.ADMIN, result.getRole());

        verify(userRepository, times(1))
                .findById(1);
        verify(userRepository, times(1))
                .save(existingUser);
    }

    @Test
    void testUpdateUser_UserNotFound() {

        UpdateUserDTO dto = new UpdateUserDTO();
        dto.setRole(Role.valueOf("ADMIN"));

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        User result = adminUserService.updateUser(1, dto);

        assertNull(result);

        verify(userRepository, times(1))
                .findById(1);
        verify(userRepository, never())
                .save(any(User.class));
    }

    @Test
    void testDeleteUser_Success() {

        User user = new User();
        user.setUserId(1);

        when(userRepository.findById(1))
                .thenReturn(Optional.of(user));

        String result = adminUserService.deleteUser(1);

        assertEquals("User deleted successfully", result);

        verify(userRepository, times(1))
                .findById(1);
        verify(userRepository, times(1))
                .delete(user);
    }

    @Test
    void testDeleteUser_UserNotFound() {

        when(userRepository.findById(1))
                .thenReturn(Optional.empty());

        String result = adminUserService.deleteUser(1);

        assertEquals("User Not Found", result);

        verify(userRepository, times(1))
                .findById(1);
        verify(userRepository, never())
                .delete(any(User.class));
    }

    @Test
    void testGetAllUsers() {

        User user1 = new User();
        user1.setUserId(1);
        user1.setName("John");

        User user2 = new User();
        user2.setUserId(2);
        user2.setName("Mike");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll())
                .thenReturn(users);

        List<User> result = adminUserService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(userRepository, times(1))
                .findAll();
    }
}