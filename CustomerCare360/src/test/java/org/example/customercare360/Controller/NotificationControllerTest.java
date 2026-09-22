package org.example.customercare360.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Not;
import org.example.customercare360.DTO.NotificationSendRequest;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Enums.NotificationCategory;
import org.example.customercare360.Services.DBNotificationService;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificationController.class)
@AutoConfigureMockMvc(addFilters = false)
class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private DBNotificationService dbNotificationService;
    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void getNotification_ShouldReturnNotifications() throws Exception {

        Notification notification = new Notification();
        notification.setMessage("Test Notification");
        notification.setCategory(NotificationCategory.COMPLAINT);
        notification.setNotificationId(1);
        Mockito.when(dbNotificationService.getNotification(1))
                .thenReturn(List.of(notification));

        mockMvc.perform(get("/notification/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value("COMPLAINT"))
                .andExpect(jsonPath("$[0].message")
                        .value("Test Notification"));
    }

    @Test
    void createNotification_ShouldReturnCreatedNotification() throws Exception {

        NotificationSendRequest request = new NotificationSendRequest();
        request.setMessage("New Notification");

        Notification notification = new Notification();
        notification.setMessage("New Notification");
        notification.setCategory(NotificationCategory.SERVICE);

        Mockito.when(
                        dbNotificationService.createNotification(
                                any(NotificationSendRequest.class),
                                Mockito.nullable(Authentication.class)))
                .thenReturn(notification);

        mockMvc.perform(post("/notification/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message")
                        .value("New Notification"));
    }
}