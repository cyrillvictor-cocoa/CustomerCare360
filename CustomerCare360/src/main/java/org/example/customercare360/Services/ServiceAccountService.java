package org.example.customercare360.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.example.customercare360.DTO.ServiceAccountRequest;
import org.example.customercare360.DTO.ServiceAccountResponse;
import org.example.customercare360.Entity.Customer;
import org.example.customercare360.Entity.Premise;
import org.example.customercare360.Entity.ServiceAccount;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Exception.ResourceNotFoundException;
import org.example.customercare360.Exception.UserNotFound;
import org.example.customercare360.Repository.CustomerRepository;
import org.example.customercare360.Repository.PremiseRepository;
import org.example.customercare360.Repository.ServiceAccountRepository;
import org.example.customercare360.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAccountService {

    @Autowired
    private ServiceAccountRepository serviceAccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PremiseRepository premiseRepository;

    @Autowired
    private UserRepository userRepository;

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

        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("The customer id is not found!!"));
        Premise premise = premiseRepository.findById(request.getPremiseId()).orElseThrow(()-> new ResourceNotFoundException("Premise Id not found!!"));
        User createdBy = userRepository.findById(request.getCreatedBy()).orElse(null);
        User modifiedBy = userRepository.findById(request.getModifiedBy()).orElse(null);
        ServiceAccount serviceAccount = new ServiceAccount();

        serviceAccount.setCustomer(customer);
        serviceAccount.setStartDate(request.getStartDate());
        serviceAccount.setServiceType(request.getServiceType());
        serviceAccount.setEndDate(request.getEndDate());
        serviceAccount.setStatus(request.getStatus());
        serviceAccount.setPremise(premise);
        if(createdBy!=null)serviceAccount.setCreatedBy(createdBy);
        if(modifiedBy!=null)serviceAccount.setModifiedBy(modifiedBy);

        ServiceAccount saved =
                serviceAccountRepository.save(serviceAccount);

        return mapToResponse(saved);
    }

    // UPDATE
    public ServiceAccountResponse updateServiceAccount(
            ServiceAccountRequest request) {

        ServiceAccount existing =
                serviceAccountRepository.findById(request.getAccountId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Service Account not found with id: "
                                                + request.getAccountId()));
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("The customer id is not found!!"));
        Premise premise = premiseRepository.findById(request.getPremiseId()).orElseThrow(()-> new ResourceNotFoundException("Premise Id not found!!"));
        User createdBy = userRepository.findById(request.getCreatedBy()).orElse(null);
        User modifiedBy = userRepository.findById(request.getModifiedBy()).orElse(null);

        existing.setCustomer(customer);
        existing.setStartDate(request.getStartDate());
        existing.setServiceType(request.getServiceType());
        existing.setEndDate(request.getEndDate());
        existing.setStatus(request.getStatus());
        existing.setPremise(premise);
        if(createdBy!=null)existing.setCreatedBy(createdBy);
        if(modifiedBy!=null)existing.setModifiedBy(modifiedBy);

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
        response.setCustomerId(serviceAccount.getCustomer().getUserId());
        response.setStartDate(serviceAccount.getStartDate());
        response.setServiceType(serviceAccount.getServiceType());
        response.setEndDate(serviceAccount.getEndDate());
        response.setStatus(serviceAccount.getStatus());
        response.setPremiseId(serviceAccount.getPremise().getPremiseId());
        if(serviceAccount.getCreatedBy()!=null)response.setCreatedBy(serviceAccount.getCreatedBy().getUserId());
        if(serviceAccount.getModifiedBy()!=null)response.setModifiedBy(serviceAccount.getModifiedBy().getUserId());

        return response;
    }
}