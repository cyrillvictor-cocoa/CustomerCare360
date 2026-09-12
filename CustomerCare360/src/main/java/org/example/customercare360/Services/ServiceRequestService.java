    package org.example.customercare360.Services;

    import org.example.customercare360.DTO.ServiceRequestDTO;
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

        public ServiceRequest create(ServiceRequestDTO dto) {

            ServiceRequest serviceRequest = new ServiceRequest();

            serviceRequest.setCustomerId(dto.getCustomerId());
            serviceRequest.setRequestType(dto.getRequestType());
            serviceRequest.setPriority(dto.getPriority());
            serviceRequest.setStatus(dto.getStatus());
            serviceRequest.setServiceType(dto.getServiceType());
            serviceRequest.setPremiseId(dto.getPremiseId());
            serviceRequest.setCreatedBy(dto.getCreatedBy());
            serviceRequest.setModifiedBy(dto.getModifiedBy());

            serviceRequest.setCreatedDate(LocalDateTime.now());

            return repository.save(serviceRequest);
        }

        public List<ServiceRequest> getAll() {

            return repository.findAll();
        }

        public ServiceRequest getById(Integer requestId) {

            return repository.findById(requestId)
                    .orElseThrow(() ->
                            new ServiceRequestNotFoundException(
                                    "Service Request not found"));
        }

        public ServiceRequest update(Integer requestId,
                                     ServiceRequestDTO dto) {

            ServiceRequest existing = repository.findById(requestId)
                    .orElseThrow(() ->
                            new ServiceRequestNotFoundException(
                                    "Service Request not found"));

            existing.setPriority(dto.getPriority());
            existing.setStatus(dto.getStatus());
            existing.setModifiedBy(dto.getModifiedBy());

            return repository.save(existing);
        }

        public void delete(Integer requestId) {

            ServiceRequest existing = repository.findById(requestId)
                    .orElseThrow(() ->
                            new ServiceRequestNotFoundException(
                                    "Service Request not found"));

            repository.delete(existing);
        }
    }