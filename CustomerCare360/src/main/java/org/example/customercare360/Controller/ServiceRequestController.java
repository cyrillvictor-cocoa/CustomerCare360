package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceRequestRequest;
import org.example.customercare360.DTO.ServiceRequestResponse;
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
    public ResponseEntity<ServiceRequestResponse>
    create(
            @RequestBody ServiceRequestRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(request));
    }

    @GetMapping
    public ResponseEntity<
            List<ServiceRequestResponse>>
    getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequestResponse>
    getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRequestResponse>
    update(
            @PathVariable Integer id,
            @RequestBody ServiceRequestRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.ok(
                "Deleted Successfully");
    }
}