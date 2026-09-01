package org.example.customercare360.Services;

import org.example.customercare360.dto.AssignedServiceOrderDTO;
import org.example.customercare360.dto.AssignedServiceOrderResponseDTO;
import org.example.customercare360.dto.ServiceOrderDTO;
import org.example.customercare360.dto.ApiResponseDTO;
import org.example.customercare360.dto.RejectServiceOrderDTO;
import org.example.customercare360.dto.AcceptServiceOrderDTO;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Override
    public AssignedServiceOrderResponseDTO getAssignedOrders(
            Long agentId,
            String assignmentDate,
            String status,
            String priority,
            String utilityType) {

        List<AssignedServiceOrderDTO> orders = new ArrayList<>();

        orders.add(new AssignedServiceOrderDTO(
                "WO-4813",
                "Customer A",
                "Assigned",
                "High"));

        AssignedServiceOrderResponseDTO response =
                new AssignedServiceOrderResponseDTO();

        response.setTotalRecords(orders.size());
        response.setServiceOrders(orders);

        return response;
    }

    @Override
    public List<ServiceOrderDTO> getAllServiceOrders() {

        List<ServiceOrderDTO> orders = new ArrayList<>();

        ServiceOrderDTO dto = new ServiceOrderDTO();

        dto.setServiceOrderId("sereg01");
        dto.setServiceOrderName("electricity");
        dto.setCustomerId("cus01");
        dto.setCustomerName("abcdefdsa");
        dto.setServiceOrderType("New Connection");
        dto.setServiceOrderStatus("Assigned");
        dto.setFieldServiceAgentAssigned(true);
        dto.setFieldServiceAgentId("fsa01");
        dto.setFieldServiceAgentName("fdafeir");

        orders.add(dto);

        return orders;
    }
    @Override
    public ApiResponseDTO acceptServiceOrder(
            AcceptServiceOrderDTO request) {

        return new ApiResponseDTO(
                200,
                "Accepted the Service Order"
        );
    }
    @Override
    public ApiResponseDTO rejectServiceOrder(
            RejectServiceOrderDTO request) {

        return new ApiResponseDTO(
                200,
                "Rejected the Service Order : "
                        + request.getServiceOrderId()
        );
    }
}