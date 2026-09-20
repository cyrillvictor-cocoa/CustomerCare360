package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceRequestRequest;
import org.example.customercare360.DTO.ServiceRequestResponse;
import org.example.customercare360.Entity.ServiceRequest;
import org.example.customercare360.Exception.ServiceRequestNotFoundException;
import org.example.customercare360.Repository.ServiceRequestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository repository;

    @InjectMocks
    private ServiceRequestService service;

    @Test
    void shouldCreateRequest() {

        ServiceRequestRequest dto =
                new ServiceRequestRequest();

        ServiceRequest request =
                new ServiceRequest();

        request.setRequestId(1);

        when(repository.save(any(ServiceRequest.class)))
                .thenReturn(request);

        ServiceRequestResponse response =
                service.create(dto);

        assertNotNull(response);
    }

    @Test
    void shouldGetRequestById() {

        ServiceRequest request =
                new ServiceRequest();

        request.setRequestId(1);

        when(repository.findById(1))
                .thenReturn(Optional.of(request));

        ServiceRequestResponse response =
                service.getById(1);

        assertEquals(1,
                response.getRequestId());
    }

    @Test
    void shouldThrowExceptionWhenRequestNotFound() {

        when(repository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceRequestNotFoundException.class,
                () -> service.getById(99));
    }

    @Test
    void shouldDeleteRequest() {

        ServiceRequest request =
                new ServiceRequest();

        when(repository.findById(1))
                .thenReturn(Optional.of(request));

        service.delete(1);

        verify(repository)
                .delete(request);
    }
}