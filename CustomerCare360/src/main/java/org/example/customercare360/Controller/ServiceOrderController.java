package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceOrderRequest;
import org.example.customercare360.DTO.ServiceOrderResponse;
import org.example.customercare360.Services.ServiceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService service;

    @PostMapping
    public ResponseEntity<ServiceOrderResponse>
    create(
            @RequestBody ServiceOrderRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<
            List<ServiceOrderResponse>>
    getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse>
    getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrderResponse>
    update(
            @PathVariable Integer id,
            @RequestBody ServiceOrderRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Service Order Deleted Successfully");
    }
}