package org.example.customercare360.Controller;

import org.example.customercare360.DTO.ServiceDTO;
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
    public ResponseEntity<List<ServiceDTO>>
    getAllServices() {

        return ResponseEntity.ok(
                service.getAllServices());
    }

    @GetMapping("/{serviceName}")
    public ResponseEntity<List<ServiceDTO>>
    getServicesByServiceName(
            @PathVariable String serviceName) {

        return ResponseEntity.ok(
                service.getServicesByServiceName(
                        serviceName));
    }
}