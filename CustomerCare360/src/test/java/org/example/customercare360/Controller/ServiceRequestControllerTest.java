package org.example.customercare360.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.customercare360.DTO.ServiceRequestRequest;
import org.example.customercare360.DTO.ServiceRequestResponse;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Services.ServiceRequestService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceRequestController.class)
@AutoConfigureMockMvc(addFilters = false)
class ServiceRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    @MockitoBean
    private ServiceRequestService service;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;

    @Test
    void shouldCreateRequest() throws Exception {

        ServiceRequestRequest request =
                new ServiceRequestRequest();

        request.setCustomerId(1);
        request.setRequestType("INSTALLATION");
        request.setPriority("HIGH");
        request.setStatus("OPEN");
        request.setServiceType("WATER");
        request.setPremiseId(1);
        request.setCreatedBy(1);
        request.setModifiedBy(1);

        ServiceRequestResponse response =
                new ServiceRequestResponse();

        response.setRequestId(1);

        when(service.create(any(ServiceRequestRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        post("/api/service-requests")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllRequests() throws Exception {

        ServiceRequestResponse response =
                new ServiceRequestResponse();

        when(service.getAll())
                .thenReturn(Arrays.asList(response));

        mockMvc.perform(
                        get("/api/service-requests")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnRequestById() throws Exception {

        ServiceRequestResponse response =
                new ServiceRequestResponse();

        response.setRequestId(1);

        when(service.getById(1))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/service-requests/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateRequest() throws Exception {

        ServiceRequestRequest request =
                new ServiceRequestRequest();

        request.setPriority("HIGH");
        request.setStatus("INPROGRESS");
        request.setModifiedBy(2);

        ServiceRequestResponse response =
                new ServiceRequestResponse();

        response.setRequestId(1);

        when(service.update(
                eq(1),
                any(ServiceRequestRequest.class)))
                .thenReturn(response);

        mockMvc.perform(
                        put("/api/service-requests/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteRequest() throws Exception {

        doNothing()
                .when(service)
                .delete(1);

        mockMvc.perform(
                        delete("/api/service-requests/1")
                )
                .andExpect(status().isOk());
    }
}