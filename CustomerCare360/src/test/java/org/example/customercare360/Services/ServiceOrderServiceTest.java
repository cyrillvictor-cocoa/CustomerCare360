package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceOrderRequest;
import org.example.customercare360.DTO.ServiceOrderResponse;
import org.example.customercare360.Entity.ServiceOrder;
import org.example.customercare360.Exception.ServiceOrderNotFoundException;
import org.example.customercare360.Repository.ServiceOrderRepository;
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
class ServiceOrderServiceTest {

    @Mock
    private ServiceOrderRepository repository;

    @InjectMocks
    private ServiceOrderService service;

    @Test
    void shouldCreateOrder() {

        ServiceOrderRequest dto =
                new ServiceOrderRequest();

        ServiceOrder order =
                new ServiceOrder();

        order.setOrderId(1);

        when(repository.save(any(ServiceOrder.class)))
                .thenReturn(order);

        ServiceOrderResponse response =
                service.create(dto);

        assertNotNull(response);
    }

    @Test
    void shouldGetOrderById() {

        ServiceOrder order =
                new ServiceOrder();

        order.setOrderId(1);

        when(repository.findById(1))
                .thenReturn(Optional.of(order));

        ServiceOrderResponse response =
                service.getById(1);

        assertEquals(1,
                response.getOrderId());
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {

        when(repository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(
                ServiceOrderNotFoundException.class,
                () -> service.getById(99));
    }

    @Test
    void shouldDeleteOrder() {

        ServiceOrder order =
                new ServiceOrder();

        when(repository.findById(1))
                .thenReturn(Optional.of(order));

        service.delete(1);

        verify(repository)
                .delete(order);
    }
}