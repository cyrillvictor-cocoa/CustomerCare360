package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceOrderRequest;
import org.example.customercare360.DTO.ServiceOrderResponse;
import org.example.customercare360.Entity.ServiceOrder;
import org.example.customercare360.Exception.ServiceOrderNotFoundException;
import org.example.customercare360.Repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository repository;

    public ServiceOrderResponse create(
            ServiceOrderRequest dto) {

        ServiceOrder serviceOrder =
                new ServiceOrder();

        serviceOrder.setServiceAccountId(
                dto.getServiceAccountId());

        serviceOrder.setPremiseId(
                dto.getPremiseId());

        serviceOrder.setOrderType(
                dto.getOrderType());

        serviceOrder.setScheduledDate(
                dto.getScheduledDate());

        serviceOrder.setCompletionDate(
                dto.getCompletionDate());

        serviceOrder.setStatus(
                dto.getStatus());

        serviceOrder.setAssignedTo(
                dto.getAssignedTo());

        serviceOrder.setCreatedBy(
                dto.getCreatedBy());

        serviceOrder.setModifiedBy(
                dto.getModifiedBy());

        ServiceOrder saved =
                repository.save(serviceOrder);

        return mapToResponse(saved);
    }

    public List<ServiceOrderResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ServiceOrderResponse getById(
            Integer id) {

        ServiceOrder serviceOrder =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ServiceOrderNotFoundException(
                                        "Service Order Not Found"));

        return mapToResponse(serviceOrder);
    }

    public ServiceOrderResponse update(
            Integer id,
            ServiceOrderRequest dto) {

        ServiceOrder existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ServiceOrderNotFoundException(
                                        "Service Order Not Found"));

        existing.setStatus(
                dto.getStatus());

        existing.setAssignedTo(
                dto.getAssignedTo());

        existing.setCompletionDate(
                dto.getCompletionDate());

        existing.setScheduledDate(
                dto.getScheduledDate());

        existing.setModifiedBy(
                dto.getModifiedBy());

        ServiceOrder updated =
                repository.save(existing);

        return mapToResponse(updated);
    }

    public void delete(Integer id) {

        ServiceOrder existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ServiceOrderNotFoundException(
                                        "Service Order Not Found"));

        repository.delete(existing);
    }

    private ServiceOrderResponse mapToResponse(
            ServiceOrder order) {

        ServiceOrderResponse response =
                new ServiceOrderResponse();

        response.setOrderId(
                order.getOrderId());

        response.setServiceAccountId(
                order.getServiceAccountId());

        response.setPremiseId(
                order.getPremiseId());

        response.setOrderType(
                order.getOrderType());

        response.setScheduledDate(
                order.getScheduledDate());

        response.setCompletionDate(
                order.getCompletionDate());

        response.setStatus(
                order.getStatus());

        response.setAssignedTo(
                order.getAssignedTo());

        response.setCreatedBy(
                order.getCreatedBy());

        response.setModifiedBy(
                order.getModifiedBy());

        return response;
    }
}