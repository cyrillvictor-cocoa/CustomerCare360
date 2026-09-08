package org.example.customercare360.Controller;

import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Services.ServiceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/serviceaccounts")
public class ServiceAccountController {

    @Autowired
    private ServiceAccountService serviceAccountService;

    @GetMapping("/{accountId}")
    public ServiceAccount getServiceAccountDetails(
            @PathVariable Integer accountId) {

        return serviceAccountService.getServiceAccountDetails(accountId);
    }
}