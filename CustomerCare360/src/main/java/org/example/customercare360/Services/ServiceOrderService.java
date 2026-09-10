package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceOrderDTO;
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

    public ServiceOrder create(ServiceOrderDTO dto) {

        ServiceOrder serviceOrder = new ServiceOrder();

        serviceOrder.setServiceAccountId(dto.getServiceAccountId());
        serviceOrder.setPremiseId(dto.getPremiseId());
        serviceOrder.setOrderType(dto.getOrderType());
        serviceOrder.setScheduledDate(dto.getScheduledDate());
        serviceOrder.setCompletionDate(dto.getCompletionDate());
        serviceOrder.setStatus(dto.getStatus());
        serviceOrder.setAssignedTo(dto.getAssignedTo());
        serviceOrder.setCreatedBy(dto.getCreatedBy());
        serviceOrder.setModifiedBy(dto.getModifiedBy());

        return repository.save(serviceOrder);
    }

    public List<ServiceOrder> getAll() {

        return repository.findAll();
    }

    public ServiceOrder getById(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ServiceOrderNotFoundException(
                                "Service Order Not Found"));
    }

    public ServiceOrder update(Integer id,
                               ServiceOrderDTO dto) {

        ServiceOrder existing = getById(id);

        existing.setStatus(dto.getStatus());
        existing.setAssignedTo(dto.getAssignedTo());
        existing.setCompletionDate(dto.getCompletionDate());

        return repository.save(existing);
    }

    public void delete(Integer id) {

        ServiceOrder existing = getById(id);

        repository.delete(existing);
    }
}