package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceResponse;
import org.example.customercare360.Services.JwtService;
import org.example.customercare360.Services.ServiceListService;
import org.example.customercare360.Util.JwtAuthFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServiceController.class)
@AutoConfigureMockMvc(addFilters = false)
class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceListService service;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private JwtAuthFilter jwtAuthFilter;

    @MockitoBean
    private AuthenticationProvider authenticationProvider;

    @Test
    void shouldReturnAllServices() throws Exception {

        ServiceResponse response =
                new ServiceResponse();

        List<ServiceResponse> services =
                Arrays.asList(response);

        when(service.getAllServices())
                .thenReturn(services);

        mockMvc.perform(
                        get("/api/services"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnServiceById() throws Exception {

        ServiceResponse response =
                new ServiceResponse();

        response.setServiceId(1);

        when(service.getServiceById(1))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/services/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnServicesByName() throws Exception {

        ServiceResponse response =
                new ServiceResponse();

        when(service.getServicesByServiceName(
                "Water"))
                .thenReturn(Arrays.asList(response));

        mockMvc.perform(
                        get("/api/services/name/Water"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnServicesByProviderId()
            throws Exception {

        ServiceResponse response =
                new ServiceResponse();

        when(service.getServicesByProviderId(1))
                .thenReturn(Arrays.asList(response));

        mockMvc.perform(
                        get("/api/services/provider/1"))
                .andExpect(status().isOk());
    }
}