package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.FSAServiceOrder;
import org.example.customercare360.Exception.AgentNotFound;
import org.example.customercare360.Exception.NoServiceOrdersFound;
import org.example.customercare360.Repository.FSAServiceOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.customercare360.Services.FSAServiceOrderService;
import org.example.customercare360.Exception.CustomerNotFound;
import org.example.customercare360.Exception.OrderTypeNotFound;
import org.example.customercare360.Exception.ServiceAccountNotFound;
import org.example.customercare360.Exception.AgentNameNotFound;

import java.util.ArrayList;
import java.util.List;

@Service
public class FSAServiceOrderServiceImpl implements FSAServiceOrderService {

    @Autowired
    private FSAServiceOrderRepo fsaServiceOrderRepo;

    public AssignedServiceOrderResponseDTO getAssignedOrders(
            Long agentId,
            String assignmentDate,
            String status,
            String priority,
            String utilityType) {

        AssignedServiceOrderResponseDTO response =
                new AssignedServiceOrderResponseDTO();

        List<AssignedServiceOrderDTO> orders =
                new ArrayList<>();

        List<FSAServiceOrder> dbOrders =
                fsaServiceOrderRepo.findByAgentId(agentId.intValue()).orElseThrow(() ->
                                new AgentNotFound(
                                        "There is no Agent with id " + agentId
                                )
                        );

        if (dbOrders.isEmpty()) {
            throw new NoServiceOrdersFound(
                    "No Service Orders Assigned To Agent Id " + agentId
            );
        }

        for (FSAServiceOrder order : dbOrders) {

            if (order.getCustomerName() == null ||
                    order.getCustomerName().isBlank()) {

                throw new CustomerNotFound(
                        "Customer Not Found For Order Id "
                                + order.getOrderId()
                );
            }

            if (order.getAccountId() == null) {

                throw new ServiceAccountNotFound(
                        "Service Account Not Found For Order Id "
                                + order.getOrderId()
                );
            }

            if (order.getAgentName() == null ||
                    order.getAgentName().isBlank()) {

                throw new AgentNameNotFound(
                        "Agent Name Not Found For Agent Id "
                                + order.getAgentId()
                );
            }

            if (order.getOrderType() == null ||
                    order.getOrderType().isBlank()) {

                throw new OrderTypeNotFound(
                        "Order Type Missing For Order Id "
                                + order.getOrderId()
                );
            }

            AssignedServiceOrderDTO dto = new AssignedServiceOrderDTO();

            dto.setOrderId(String.valueOf(order.getOrderId()));

            dto.setStatus(order.getOrderStatus());

            dto.setCustomerName(order.getCustomerName());

            orders.add(dto);
        }

        response.setTotalRecords(orders.size());

        response.setServiceOrders(orders);

        return response;
    }

    public List<FSAServiceOrderDTO> getAllServiceOrders() {

        List<FSAServiceOrder> dbOrders =
                fsaServiceOrderRepo.findAll();

        if (dbOrders.isEmpty()) {throw new NoServiceOrdersFound("No Service Orders Found");
        }

        List<FSAServiceOrderDTO> response = new ArrayList<>();

        for (FSAServiceOrder order : dbOrders) {

            FSAServiceOrderDTO dto = new FSAServiceOrderDTO();

            dto.setOrderId(Long.valueOf(order.getOrderId()));

            dto.setOrderStatus(order.getOrderStatus());

            dto.setOrderType(order.getOrderType());

            dto.setAgentId(order.getAgentId() != null ? Long.valueOf(order.getAgentId()) : null);

            dto.setScheduledDate(order.getScheduledDate() != null ? order.getScheduledDate().toString() : null);

            dto.setCompletionDate(order.getCompletionDate() != null ? order.getCompletionDate().toString() : null);

            dto.setAgentName(order.getAgentName());

            dto.setAccountId(Long.valueOf(order.getAccountId()));

            dto.setServiceType(String.valueOf(order.getServiceType()));

            dto.setServiceType(String.valueOf(order.getServiceType()));

            dto.setAccountStatus(String.valueOf(order.getAccountStatus()));

            dto.setAccountStatus(String.valueOf(order.getAccountStatus()));

// dto.setCustomerId(Long.valueOf(order.getServiceAccount().getCustomerId()));

            dto.setCustomerName(order.getCustomerName());

            dto.setCustomerPhone(order.getCustomerPhone());
            response.add(dto);
        }

        return response;
    }

    @Override
    public ApiResponseDTO acceptServiceOrder(AcceptServiceOrderDTO request) {
        return null;
    }

    @Override
    public ApiResponseDTO rejectServiceOrder(RejectServiceOrderDTO request) {
        return null;
    }

    @Override
    public ApiResponseDTO updateStatusAvailability(UpdateStatusAvailabilityDTO request) {
        return null;
    }
}