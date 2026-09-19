package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceResponse;
import org.example.customercare360.Entity.ServiceEntity;
import org.example.customercare360.Exception.ServiceNotFoundException;
import org.example.customercare360.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceListService {

    @Autowired
    private ServiceRepository repository;

    public List<ServiceResponse> getAllServices() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ServiceResponse getServiceById(
            Integer id) {

        ServiceEntity service =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ServiceNotFoundException(
                                        "Service not found"));

        return mapToResponse(service);
    }

    public List<ServiceResponse>
    getServicesByServiceName(
            String serviceName) {

        List<ServiceEntity> services =
                repository.findByServiceName(
                        serviceName);

        if (services.isEmpty()) {

            throw new ServiceNotFoundException(
                    "Service not found with name : "
                            + serviceName);
        }

        return services.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<ServiceResponse>
    getServicesByProviderId(
            Integer providerId) {

        List<ServiceEntity> services =
                repository.findByProviderId(
                        providerId);

        if (services.isEmpty()) {

            throw new ServiceNotFoundException(
                    "Service not found with provider id : "
                            + providerId);
        }

        return services.stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ServiceResponse mapToResponse(
            ServiceEntity service) {

        ServiceResponse response =
                new ServiceResponse();

        response.setServiceId(
                service.getServiceId());

        response.setServiceName(
                service.getServiceName());

        response.setProviderId(
                service.getProviderId());

        response.setPricePerCycle(
                service.getPricePerCycle());

        response.setCyclePeriod(
                service.getCyclePeriod());

        return response;
    }
}