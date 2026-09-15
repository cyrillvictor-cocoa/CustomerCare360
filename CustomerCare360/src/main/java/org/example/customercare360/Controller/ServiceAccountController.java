package org.example.customercare360.Controller;

import java.util.List;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Services.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceaccounts")
public class ServiceAccountController {

    @Autowired
    private ServiceAccountService serviceAccountService;

    // GET ALL SERVICE ACCOUNTS
    @GetMapping
    public List<ServiceAccountResponse> getAllServiceAccounts() {
        return serviceAccountService.getAllServiceAccounts();
    }

    // GET SERVICE ACCOUNT BY ID
    @GetMapping("/{accountId}")
    public ServiceAccountResponse getServiceAccountDetails(
            @PathVariable Integer accountId) {

        return serviceAccountService.getServiceAccountDetails(accountId);
    }

    // CREATE SERVICE ACCOUNT
    @PostMapping
    public ServiceAccountResponse createServiceAccount(
            @RequestBody ServiceAccountRequest request) {

        return serviceAccountService.createServiceAccount(request);
    }

    // UPDATE SERVICE ACCOUNT
    @PutMapping("/{accountId}")
    public ServiceAccountResponse updateServiceAccount(
            @PathVariable Integer accountId,
            @RequestBody ServiceAccountRequest request) {

        return serviceAccountService.updateServiceAccount(
                accountId,
                request);
    }

    // DELETE SERVICE ACCOUNT
    @DeleteMapping("/{accountId}")
    public String deleteServiceAccount(
            @PathVariable Integer accountId) {

        serviceAccountService.deleteServiceAccount(accountId);

        return "Service Account deleted successfully";
    }
}