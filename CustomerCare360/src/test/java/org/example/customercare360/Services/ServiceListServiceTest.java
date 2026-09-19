package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceResponse;
import org.example.customercare360.Entity.ServiceEntity;
import org.example.customercare360.Exception.ServiceNotFoundException;
import org.example.customercare360.Repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceListServiceTest {

    @Mock
    private ServiceRepository repository;

    @InjectMocks
    private ServiceListService service;

    @Test
    void shouldReturnAllServices() {

        ServiceEntity entity = new ServiceEntity();

        when(repository.findAll())
                .thenReturn(Arrays.asList(entity));

        assertEquals(1,
                service.getAllServices().size());
    }

    @Test
    void shouldReturnServiceById() {

        ServiceEntity entity =
                new ServiceEntity();

        entity.setServiceId(1);

        when(repository.findById(1))
                .thenReturn(Optional.of(entity));

        ServiceResponse result =
                service.getServiceById(1);

        assertEquals(1,
                result.getServiceId());
    }

    @Test
    void shouldThrowExceptionWhenServiceNotFound() {

        when(repository.findById(100))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceNotFoundException.class,
                () -> service.getServiceById(100));
    }

    @Test
    void shouldReturnServicesByName() {

        ServiceEntity entity =
                new ServiceEntity();

        when(repository.findByServiceName("Water"))
                .thenReturn(Arrays.asList(entity));

        assertFalse(
                service.getServicesByServiceName(
                        "Water").isEmpty());
    }

    @Test
    void shouldReturnServicesByProviderId() {

        ServiceEntity entity =
                new ServiceEntity();

        when(repository.findByProviderId(1))
                .thenReturn(Arrays.asList(entity));

        assertFalse(
                service.getServicesByProviderId(
                        1).isEmpty());
    }
}