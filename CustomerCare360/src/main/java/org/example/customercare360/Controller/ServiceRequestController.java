package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceRequestDTO;
import org.example.customercare360.Entity.ServiceRequest;
import org.example.customercare360.Services.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService service;

    @PostMapping
    public ResponseEntity<ServiceRequest> create(
            @RequestBody ServiceRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequest> update(
            @PathVariable Integer id,
            @RequestBody ServiceRequestDTO dto) {

        return ResponseEntity.ok(
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Deleted Successfully");
    }
}