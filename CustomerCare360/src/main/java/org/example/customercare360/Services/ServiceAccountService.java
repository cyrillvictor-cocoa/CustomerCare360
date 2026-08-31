package org.example.customercare360.Services;

import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.ServiceAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    public ServiceAccount getServiceAccountDetails(Integer accountId) {

        return serviceAccountRepository.findById(accountId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service Account not found with id: " + accountId));
    }
}