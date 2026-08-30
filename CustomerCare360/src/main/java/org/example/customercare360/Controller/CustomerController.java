package org.example.customercare360.Controller;

import org.example.customercare360.Exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/customers/{id}")
    public String getCustomerById(@PathVariable Long id) {

        if (id != 1) {
            throw new ResourceNotFoundException(
                    "Customer not found with id: " + id
            );
        }

        return "Customer Found: " + id;
    }
}