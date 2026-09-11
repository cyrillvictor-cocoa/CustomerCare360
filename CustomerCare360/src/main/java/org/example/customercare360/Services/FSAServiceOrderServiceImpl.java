package org.example.customercare360.Services;

import org.example.customercare360.DTO.AcceptServiceOrderDTO;
import org.example.customercare360.DTO.ApiResponseDTO;
import org.example.customercare360.DTO.AssignedServiceOrderDTO;
import org.example.customercare360.DTO.AssignedServiceOrderResponseDTO;
import org.example.customercare360.DTO.RejectServiceOrderDTO;
import org.example.customercare360.DTO.FSAServiceOrderDTO;
import org.example.customercare360.Entity.FSAServiceOrder;
import org.example.customercare360.Repository.FSAServiceOrderRepo;
import org.example.customercare360.DTO.UpdateStatusAvailabilityDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FSAServiceOrderServiceImpl implements FSAServiceOrderService {

    @Autowired
    private FSAServiceOrderRepo fsaServiceOrderRepo;

    @Override
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

        if (agentId != null) {

            List<FSAServiceOrder> dbOrders =
                    fsaServiceOrderRepo.findByAssignedTo(
                            agentId.intValue()
                    );

            for (FSAServiceOrder order : dbOrders) {

                AssignedServiceOrderDTO dto =
                        new AssignedServiceOrderDTO();

                dto.setOrderId(
                        String.valueOf(order.getOrderId())
                );

                dto.setStatus(
                        order.getStatus()
                );

                dto.setCustomerName(
                        "Customer-" + order.getServiceAccountId()
                );

                dto.setPriority("");

                orders.add(dto);
            }
        }

        response.setTotalRecords(
                orders.size()
        );

        response.setServiceOrders(
                orders
        );

        return response;
    }

    @Override
    public List<FSAServiceOrderDTO> getAllServiceOrders() {

        List<FSAServiceOrder> dbOrders = fsaServiceOrderRepo.findAll();

        List<FSAServiceOrderDTO> response = new ArrayList<>();

        for (FSAServiceOrder order : dbOrders) {

            FSAServiceOrderDTO dto = new FSAServiceOrderDTO();

            dto.setServiceOrderId(
                    String.valueOf(order.getOrderId())
            );

            dto.setServiceOrderStatus(
                    order.getStatus()
            );

            dto.setServiceOrderType(
                    order.getOrderType()
            );

            dto.setCustomerId(
                    String.valueOf(order.getServiceAccount().getCustomerId())
            );

            // Customer Name
            try {

                if (order.getServiceAccount() != null
                        && order.getServiceAccount().getCustomer() != null
                        && order.getServiceAccount().getCustomer().getUser() != null) {

                    dto.setCustomerName(
                            order.getServiceAccount()
                                    .getCustomer()
                                    .getUser()
                                    .getName()
                    );

                } else {

                    dto.setCustomerName("N/A");
                }

            } catch (Exception e) {

                dto.setCustomerName("N/A");
            }

            dto.setServiceOrderName(
                    order.getOrderType()
            );

            dto.setFieldServiceAgentId(
                    String.valueOf(order.getAssignedTo())
            );

            // Agent Name
            try {

                if (order.getAssignedUser() != null) {

                    dto.setFieldServiceAgentName(
                            order.getAssignedUser().getName()
                    );

                } else {

                    dto.setFieldServiceAgentName("N/A");
                }

            } catch (Exception e) {

                dto.setFieldServiceAgentName("N/A");
            }

            dto.setFieldServiceAgentAssigned(
                    order.getAssignedTo() != null
            );

            response.add(dto);
        }

        return response;
    }

    @Override
    public ApiResponseDTO acceptServiceOrder(
            AcceptServiceOrderDTO request) {

        try {

            Integer orderId =
                    Integer.parseInt(
                            request.getServiceOrderId()
                    );

            FSAServiceOrder order =
                    fsaServiceOrderRepo
                            .findById(orderId)
                            .orElse(null);

            if (order == null) {

                return new ApiResponseDTO(
                        404,
                        "Service Order Not Found"
                );
            }

            order.setStatus("INPROGRESS");

            fsaServiceOrderRepo.save(order);

            return new ApiResponseDTO(
                    200,
                    "Accepted the Service Order"
            );

        } catch (Exception e) {

            return new ApiResponseDTO(
                    500,
                    e.getMessage()
            );
        }
    }

    @Override
    public ApiResponseDTO rejectServiceOrder(
            RejectServiceOrderDTO request) {

        try {

            Integer orderId =
                    Integer.parseInt(
                            request.getServiceOrderId()
                    );

            FSAServiceOrder order =
                    fsaServiceOrderRepo
                            .findById(orderId)
                            .orElse(null);

            if (order == null) {

                return new ApiResponseDTO(
                        404,
                        "Service Order Not Found"
                );
            }

            order.setStatus("FAILED");

            fsaServiceOrderRepo.save(order);

            return new ApiResponseDTO(
                    200,
                    "Rejected the Service Order : "
                            + request.getServiceOrderId()
            );

        } catch (Exception e) {

            return new ApiResponseDTO(
                    500,
                    e.getMessage()
            );
        }
    }
    @Override
    public ApiResponseDTO updateStatusAvailability(
            UpdateStatusAvailabilityDTO request) {

        try {

            Integer orderId =
                    Integer.parseInt(request.getWorkOrderId());

            Optional<FSAServiceOrder> optionalOrder =
                    fsaServiceOrderRepo.findById(orderId);

            if (optionalOrder.isEmpty()) {

                return new ApiResponseDTO(
                        404,
                        "Service Order Not Found"
                );
            }

            FSAServiceOrder serviceOrder =
                    optionalOrder.get();

            serviceOrder.setStatus(
                    request.getServiceOrderStatus()
            );

            fsaServiceOrderRepo.save(serviceOrder);

            return new ApiResponseDTO(
                    200,
                    "Status updated successfully"
            );

        } catch (Exception e) {

            return new ApiResponseDTO(
                    500,
                    e.getMessage()
            );
        }
    }
}