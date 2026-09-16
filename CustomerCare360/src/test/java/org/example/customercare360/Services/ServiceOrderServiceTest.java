//package org.example.customercare360.Services;
//
//import org.example.customercare360.DTO.ServiceOrderDTO;
//import org.example.customercare360.Entity.ServiceOrder;
//import org.example.customercare360.Exception.ServiceOrderNotFoundException;
//import org.example.customercare360.Repository.ServiceOrderRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ServiceOrderServiceTest {
//
//    @Mock
//    private ServiceOrderRepository repository;
//
//    @InjectMocks
//    private ServiceOrderService service;
//
//    @Test
//    void shouldCreateServiceOrder() {
//
//        ServiceOrderDTO dto = new ServiceOrderDTO();
//
//        dto.setServiceAccountId(1);
//        dto.setPremiseId(1);
//        dto.setOrderType("CONNECT");
//        dto.setScheduledDate(LocalDateTime.now());
//        dto.setStatus("SCHEDULED");
//        dto.setAssignedTo(1);
//        dto.setCreatedBy(1);
//        dto.setModifiedBy(1);
//
//        ServiceOrder order = new ServiceOrder();
//        order.setOrderId(1);
//
//        when(repository.save(any(ServiceOrder.class)))
//                .thenReturn(order);
//
//        ServiceOrder result = service.create(dto);
//
//        assertNotNull(result);
//        assertEquals(1, result.getOrderId());
//
//        verify(repository, times(1))
//                .save(any(ServiceOrder.class));
//    }
//
//    @Test
//    void shouldReturnAllServiceOrders() {
//
//        ServiceOrder order1 = new ServiceOrder();
//        ServiceOrder order2 = new ServiceOrder();
//
//        when(repository.findAll())
//                .thenReturn(Arrays.asList(order1, order2));
//
//        List<ServiceOrder> result = service.getAll();
//
//        assertEquals(2, result.size());
//
//        verify(repository, times(1))
//                .findAll();
//    }
//
//    @Test
//    void shouldReturnServiceOrderById() {
//
//        ServiceOrder order = new ServiceOrder();
//        order.setOrderId(1);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(order));
//
//        ServiceOrder result = service.getById(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getOrderId());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenOrderNotFound() {
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceOrderNotFoundException.class,
//                () -> service.getById(100)
//        );
//    }
//
//    @Test
//    void shouldUpdateServiceOrder() {
//
//        ServiceOrder existing = new ServiceOrder();
//        existing.setOrderId(1);
//
//        ServiceOrderDTO dto = new ServiceOrderDTO();
//
//        dto.setStatus("COMPLETED");
//        dto.setAssignedTo(2);
//        dto.setCompletionDate(LocalDateTime.now());
//        dto.setScheduledDate(LocalDateTime.now());
//        dto.setModifiedBy(2);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(existing));
//
//        when(repository.save(any(ServiceOrder.class)))
//                .thenReturn(existing);
//
//        ServiceOrder result =
//                service.update(1, dto);
//
//        assertNotNull(result);
//
//        verify(repository, times(1))
//                .save(existing);
//    }
//
//    @Test
//    void shouldThrowExceptionWhileUpdatingInvalidId() {
//
//        ServiceOrderDTO dto =
//                new ServiceOrderDTO();
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceOrderNotFoundException.class,
//                () -> service.update(100, dto)
//        );
//    }
//
//    @Test
//    void shouldDeleteServiceOrder() {
//
//        ServiceOrder order =
//                new ServiceOrder();
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(order));
//
//        service.delete(1);
//
//        verify(repository, times(1))
//                .delete(order);
//    }
//
//    @Test
//    void shouldThrowExceptionWhileDeletingInvalidId() {
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceOrderNotFoundException.class,
//                () -> service.delete(100)
//        );
//    }
//}