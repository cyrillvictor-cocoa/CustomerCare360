package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceOrderDTO;
import org.example.customercare360.Entity.ServiceOrder;
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
    public ResponseEntity<ServiceOrder> create(
            @RequestBody ServiceOrderDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServiceOrder>> getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceOrder> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceOrder> update(
            @PathVariable Integer id,
            @RequestBody ServiceOrderDTO dto) {

        return ResponseEntity.ok(
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Service Order Deleted Successfully");
    }
}