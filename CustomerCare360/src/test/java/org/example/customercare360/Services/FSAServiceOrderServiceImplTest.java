package org.example.customercare360.Services;

import org.example.customercare360.DTO.AssignedServiceOrderResponseDTO;
import org.example.customercare360.DTO.FSAServiceOrderDTO;
import org.example.customercare360.Entity.FSAServiceOrder;
import org.example.customercare360.Exception.*;
import org.example.customercare360.Repository.FSAServiceOrderRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FSAServiceOrderServiceImplTest {

    @Mock
    private FSAServiceOrderRepo fsaServiceOrderRepo;

    @InjectMocks
    private FSAServiceOrderServiceImpl service;

    @Test
    void testGetAssignedOrders_Success() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setCustomerName("John");
        order.setAccountId(1001);
        order.setAgentId(1);
        order.setAgentName("Mike");
        order.setOrderType("INSTALLATION");
        order.setOrderStatus("ASSIGNED");

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(List.of(order)));

        AssignedServiceOrderResponseDTO response =
                service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                );

        assertNotNull(response);
        assertEquals(1, response.getTotalRecords());

        verify(fsaServiceOrderRepo).findByAgentId(1);
    }

    @Test
    void testGetAssignedOrders_AgentNotFound() {

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.empty());

        assertThrows(
                AgentNotFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAssignedOrders_NoOrdersFound() {

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(Collections.emptyList()));

        assertThrows(
                NoServiceOrdersFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAssignedOrders_CustomerNotFound() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setCustomerName("");
        order.setAccountId(1001);
        order.setAgentName("Mike");
        order.setOrderType("INSTALLATION");

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(List.of(order)));

        assertThrows(
                CustomerNotFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAssignedOrders_ServiceAccountNotFound() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setCustomerName("John");
        order.setAccountId(null);
        order.setAgentName("Mike");
        order.setOrderType("INSTALLATION");

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(List.of(order)));

        assertThrows(
                ServiceAccountNotFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAssignedOrders_AgentNameNotFound() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setCustomerName("John");
        order.setAccountId(1001);
        order.setAgentName("");
        order.setOrderType("INSTALLATION");

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(List.of(order)));

        assertThrows(
                AgentNameNotFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAssignedOrders_OrderTypeNotFound() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setCustomerName("John");
        order.setAccountId(1001);
        order.setAgentName("Mike");
        order.setOrderType("");

        when(fsaServiceOrderRepo.findByAgentId(1))
                .thenReturn(Optional.of(List.of(order)));

        assertThrows(
                OrderTypeNotFound.class,
                () -> service.getAssignedOrders(
                        1L,
                        null,
                        null,
                        null,
                        null
                )
        );
    }

    @Test
    void testGetAllServiceOrders_Success() {

        FSAServiceOrder order = new FSAServiceOrder();

        order.setOrderId(101);
        order.setOrderStatus("ASSIGNED");
        order.setOrderType("INSTALLATION");
        order.setAgentId(1);
        order.setAgentName("Mike");
        order.setAccountId(1001);
        order.setCustomerName("John");
        order.setCustomerPhone("9876543210");

        when(fsaServiceOrderRepo.findAll())
                .thenReturn(List.of(order));

        List<FSAServiceOrderDTO> response =
                service.getAllServiceOrders();

        assertNotNull(response);
        assertEquals(1, response.size());

        verify(fsaServiceOrderRepo).findAll();
    }

    @Test
    void testGetAllServiceOrders_EmptyList() {

        when(fsaServiceOrderRepo.findAll())
                .thenReturn(Collections.emptyList());

        assertThrows(
                NoServiceOrdersFound.class,
                () -> service.getAllServiceOrders()
        );

        verify(fsaServiceOrderRepo).findAll();
    }
}