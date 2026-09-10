package org.example.customercare360.Services;

import org.example.customercare360.DTO.ServiceDTO;
import org.example.customercare360.Entity.ServiceEntity;
import org.example.customercare360.Exception.ServiceNotFoundException;
import org.example.customercare360.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceListService {

    @Autowired
    private ServiceRepository repository;

    public List<ServiceDTO> getAllServices() {

        List<ServiceEntity> services =
                repository.findAll();

        List<ServiceDTO> response =
                new ArrayList<>();

        for (ServiceEntity service : services) {

            ServiceDTO dto =
                    new ServiceDTO();

            dto.setServiceId(
                    service.getServiceId());

            dto.setServiceName(
                    service.getServiceName());

            dto.setProviderId(
                    service.getProviderId());

            dto.setPricePerCycle(
                    service.getPricePerCycle());

            dto.setCyclePeriod(
                    service.getCyclePeriod());

            response.add(dto);
        }

        return response;
    }

    public List<ServiceDTO>
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

        List<ServiceDTO> response =
                new ArrayList<>();

        for (ServiceEntity service : services) {

            ServiceDTO dto =
                    new ServiceDTO();

            dto.setServiceId(
                    service.getServiceId());

            dto.setServiceName(
                    service.getServiceName());

            dto.setProviderId(
                    service.getProviderId());

            dto.setPricePerCycle(
                    service.getPricePerCycle());

            dto.setCyclePeriod(
                    service.getCyclePeriod());

            response.add(dto);
        }

        return response;
    }
}