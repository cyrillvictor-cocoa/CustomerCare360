package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.DTO.UpdateStatusAvailabilityDTO;
import java.util.List;

public interface FSAServiceOrderService {

    AssignedServiceOrderResponseDTO getAssignedOrders(
            Long agentId,
            String assignmentDate,
            String status,
            String priority,
            String utilityType);

    List<ServiceOrderDTO> getAllServiceOrders();

    ApiResponseDTO acceptServiceOrder(
            AcceptServiceOrderDTO request);

    ApiResponseDTO rejectServiceOrder(
            RejectServiceOrderDTO request);

    ApiResponseDTO updateStatusAvailability(
            UpdateStatusAvailabilityDTO request);
}