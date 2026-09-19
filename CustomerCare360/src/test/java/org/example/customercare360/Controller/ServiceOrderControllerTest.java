package org.example.customercare360.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.customercare360.DTO.ServiceOrderRequest;
import org.example.customercare360.DTO.ServiceOrderResponse;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Services.ServiceOrderService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class ServiceOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper =
            new ObjectMapper()
                    .registerModule(
                            new JavaTimeModule());

    @MockitoBean
    private ServiceOrderService service;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;

    @Test
    void shouldCreateOrder()
            throws Exception {

        ServiceOrderRequest request =
                new ServiceOrderRequest();

        when(service.create(
                any(ServiceOrderRequest.class)))
                .thenReturn(
                        new ServiceOrderResponse());

        mockMvc.perform(post(
                        "/api/service-orders")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllOrders()
            throws Exception {

        when(service.getAll())
                .thenReturn(
                        Arrays.asList(
                                new ServiceOrderResponse()));

        mockMvc.perform(
                        get("/api/service-orders"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnOrderById()
            throws Exception {

        when(service.getById(1))
                .thenReturn(
                        new ServiceOrderResponse());

        mockMvc.perform(
                        get("/api/service-orders/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateOrder()
            throws Exception {

        ServiceOrderRequest request =
                new ServiceOrderRequest();

        when(service.update(
                eq(1),
                any(ServiceOrderRequest.class)))
                .thenReturn(
                        new ServiceOrderResponse());

        mockMvc.perform(
                        put("/api/service-orders/1")
                                .contentType(
                                        MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteOrder()
            throws Exception {

        doNothing()
                .when(service)
                .delete(1);

        mockMvc.perform(
                        delete("/api/service-orders/1"))
                .andExpect(status().isOk());
    }
}