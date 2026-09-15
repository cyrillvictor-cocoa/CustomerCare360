package org.example.customercare360.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Repository.ServiceAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    // GET ALL
    public List<ServiceAccountResponse> getAllServiceAccounts() {

        return serviceAccountRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ServiceAccountResponse getServiceAccountDetails(Integer accountId) {

        ServiceAccount serviceAccount =
                serviceAccountRepository.findById(accountId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Service Account not found with id: "
                                                + accountId));

        return mapToResponse(serviceAccount);
    }

    // CREATE
    public ServiceAccountResponse createServiceAccount(
            ServiceAccountRequest request) {

        ServiceAccount serviceAccount = new ServiceAccount();

        serviceAccount.setCustomerId(request.getCustomerId());
        serviceAccount.setStartDate(request.getStartDate());
        serviceAccount.setServiceType(request.getServiceType());
        serviceAccount.setEndDate(request.getEndDate());
        serviceAccount.setStatus(request.getStatus());
        serviceAccount.setPremiseId(request.getPremiseId());
        serviceAccount.setCreatedBy(request.getCreatedBy());
        serviceAccount.setModifiedBy(request.getModifiedBy());

        ServiceAccount saved =
                serviceAccountRepository.save(serviceAccount);

        return mapToResponse(saved);
    }

    // UPDATE
    public ServiceAccountResponse updateServiceAccount(
            Integer accountId,
            ServiceAccountRequest request) {

        ServiceAccount existing =
                serviceAccountRepository.findById(accountId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Service Account not found with id: "
                                                + accountId));

        existing.setCustomerId(request.getCustomerId());
        existing.setStartDate(request.getStartDate());
        existing.setServiceType(request.getServiceType());
        existing.setEndDate(request.getEndDate());
        existing.setStatus(request.getStatus());
        existing.setPremiseId(request.getPremiseId());
        existing.setCreatedBy(request.getCreatedBy());
        existing.setModifiedBy(request.getModifiedBy());

        ServiceAccount updated =
                serviceAccountRepository.save(existing);

        return mapToResponse(updated);
    }

    // DELETE
    public void deleteServiceAccount(Integer accountId) {

        ServiceAccount serviceAccount =
                serviceAccountRepository.findById(accountId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Service Account not found with id: "
                                                + accountId));

        serviceAccountRepository.delete(serviceAccount);
    }

    private ServiceAccountResponse mapToResponse(
            ServiceAccount serviceAccount) {

        ServiceAccountResponse response =
                new ServiceAccountResponse();

        response.setAccountId(serviceAccount.getAccountId());
        response.setCustomerId(serviceAccount.getCustomerId());
        response.setStartDate(serviceAccount.getStartDate());
        response.setServiceType(serviceAccount.getServiceType());
        response.setEndDate(serviceAccount.getEndDate());
        response.setStatus(serviceAccount.getStatus());
        response.setPremiseId(serviceAccount.getPremiseId());
        response.setCreatedBy(serviceAccount.getCreatedBy());
        response.setModifiedBy(serviceAccount.getModifiedBy());

        return response;
    }
}