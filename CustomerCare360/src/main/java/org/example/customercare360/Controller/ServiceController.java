package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceResponse;
import org.example.customercare360.Services.ServiceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceListService service;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>>
    getAllServices() {

        return ResponseEntity.ok(
                service.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse>
    getServiceById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                service.getServiceById(id));
    }

    @GetMapping("/name/{serviceName}")
    public ResponseEntity<List<ServiceResponse>>
    getServicesByServiceName(
            @PathVariable String serviceName) {

        return ResponseEntity.ok(
                service.getServicesByServiceName(
                        serviceName));
    }

    @GetMapping("/provider/{providerId}")
    public ResponseEntity<List<ServiceResponse>>
    getServicesByProviderId(
            @PathVariable Integer providerId) {

        return ResponseEntity.ok(
                service.getServicesByProviderId(
                        providerId));
    }
}