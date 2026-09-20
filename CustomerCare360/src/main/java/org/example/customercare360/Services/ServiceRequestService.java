package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceRequestRequest;
import org.example.customercare360.DTO.ServiceRequestResponse;
import org.example.customercare360.Entity.ServiceRequest;
import org.example.customercare360.Exception.ServiceRequestNotFoundException;
import org.example.customercare360.Repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository repository;

    public ServiceRequestResponse create(
            ServiceRequestRequest dto) {

        ServiceRequest serviceRequest =
                new ServiceRequest();

        serviceRequest.setCustomerId(
                dto.getCustomerId());

        serviceRequest.setRequestType(
                dto.getRequestType());

        serviceRequest.setPriority(
                dto.getPriority());

        serviceRequest.setStatus(
                dto.getStatus());

        serviceRequest.setServiceType(
                dto.getServiceType());

        serviceRequest.setPremiseId(
                dto.getPremiseId());

        serviceRequest.setCreatedBy(
                dto.getCreatedBy());

        serviceRequest.setModifiedBy(
                dto.getModifiedBy());

        serviceRequest.setCreatedDate(
                LocalDateTime.now());

        ServiceRequest saved =
                repository.save(serviceRequest);

        return mapToResponse(saved);
    }

    public List<ServiceRequestResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ServiceRequestResponse getById(
            Integer requestId) {

        ServiceRequest request =
                repository.findById(requestId)
                        .orElseThrow(() ->
                                new ServiceRequestNotFoundException(
                                        "Service Request not found"));

        return mapToResponse(request);
    }

    public ServiceRequestResponse update(
            Integer requestId,
            ServiceRequestRequest dto) {

        ServiceRequest existing =
                repository.findById(requestId)
                        .orElseThrow(() ->
                                new ServiceRequestNotFoundException(
                                        "Service Request not found"));

        existing.setPriority(
                dto.getPriority());

        existing.setStatus(
                dto.getStatus());

        existing.setModifiedBy(
                dto.getModifiedBy());

        ServiceRequest updated =
                repository.save(existing);

        return mapToResponse(updated);
    }

    public void delete(Integer requestId) {

        ServiceRequest existing =
                repository.findById(requestId)
                        .orElseThrow(() ->
                                new ServiceRequestNotFoundException(
                                        "Service Request not found"));

        repository.delete(existing);
    }

    private ServiceRequestResponse mapToResponse(
            ServiceRequest request) {

        ServiceRequestResponse response =
                new ServiceRequestResponse();

        response.setRequestId(
                request.getRequestId());

        response.setCustomerId(
                request.getCustomerId());

        response.setRequestType(
                request.getRequestType());

        response.setCreatedDate(
                request.getCreatedDate());

        response.setPriority(
                request.getPriority());

        response.setStatus(
                request.getStatus());

        response.setServiceType(
                request.getServiceType());

        response.setPremiseId(
                request.getPremiseId());

        response.setCreatedBy(
                request.getCreatedBy());

        response.setModifiedBy(
                request.getModifiedBy());

        return response;
    }
}