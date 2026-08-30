package org.example.customercare360.Services;

import org.example.customercare360.dto.AssignedServiceOrderResponseDTO;
import org.example.customercare360.dto.ServiceOrderDTO;
import org.example.customercare360.dto.AcceptServiceOrderDTO;
import org.example.customercare360.dto.RejectServiceOrderDTO;
import org.example.customercare360.dto.ApiResponseDTO;

import java.util.List;

public interface ServiceOrderService {

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
}



