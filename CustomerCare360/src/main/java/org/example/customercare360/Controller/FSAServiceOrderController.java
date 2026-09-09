package org.example.customercare360.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.customercare360.DTO.*;
import org.example.customercare360.Services.FSAServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fsa/service-orders")

@Tag(
        name = "Service Order APIs",
        description = "FSA Service Order Operations"
)
public class FSAServiceOrderController {

    @Autowired
    private FSAServiceOrderService service;

    @Operation(
            summary = "Get Assigned Service Orders",
            description = "Fetch assigned service orders for an FSA using filters"
    )
    @GetMapping("/assigned")
    public AssignedServiceOrderResponseDTO getAssignedOrders(

            @RequestParam(required = false) Long agentId,
            @RequestParam(required = false) String assignmentDate,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String utilityType) {

        return service.getAssignedOrders(
                agentId,
                assignmentDate,
                status,
                priority,
                utilityType);
    }

    @PatchMapping("/update-status-availability")
    public ApiResponseDTO updateStatusAvailability(
            @RequestBody UpdateStatusAvailabilityDTO request) {

        return service.updateStatusAvailability(request);
    }

    @Operation(
            summary = "Get All Service Orders",
            description = "Retrieve all service requests from database"
    )
    @GetMapping("/all")
    public List<ServiceOrderDTO> getAllServiceOrders() {

        return service.getAllServiceOrders();
    }
    @Operation(
            summary = "Accept Service Order",
            description = "Service Agent accepts a service order"
    )
    @PostMapping("/accept")
    public ApiResponseDTO acceptServiceOrder(
            @RequestBody AcceptServiceOrderDTO request) {

        return service.acceptServiceOrder(request);
    }
    @Operation(
            summary = "Reject Service Order",
            description = "Service Agent rejects a service order"
    )
    @PostMapping("/reject")
    public ApiResponseDTO rejectServiceOrder(
            @RequestBody RejectServiceOrderDTO request) {

        return service.rejectServiceOrder(request);
    }
}