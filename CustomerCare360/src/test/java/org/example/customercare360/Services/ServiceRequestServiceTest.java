//package org.example.customercare360.Services;
//
//import org.example.customercare360.DTO.ServiceRequestDTO;
//import org.example.customercare360.Entity.ServiceRequest;
//import org.example.customercare360.Exception.ServiceRequestNotFoundException;
//import org.example.customercare360.Repository.ServiceRequestRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ServiceRequestServiceTest {
//
//    @Mock
//    private ServiceRequestRepository repository;
//
//    @InjectMocks
//    private ServiceRequestService service;
//
//    @Test
//    void shouldCreateServiceRequest() {
//
//        ServiceRequestDTO dto = new ServiceRequestDTO();
//        dto.setCustomerId(1);
//        dto.setRequestType("NEWCONNECTION");
//        dto.setPriority("HIGH");
//        dto.setStatus("OPEN");
//        dto.setServiceType("WATER");
//        dto.setPremiseId(1);
//        dto.setCreatedBy(1);
//        dto.setModifiedBy(1);
//
//        ServiceRequest savedRequest = new ServiceRequest();
//        savedRequest.setRequestId(1);
//
//        when(repository.save(any(ServiceRequest.class)))
//                .thenReturn(savedRequest);
//
//        ServiceRequest result = service.create(dto);
//
//        assertNotNull(result);
//        assertEquals(1, result.getRequestId());
//
//        verify(repository, times(1))
//                .save(any(ServiceRequest.class));
//    }
//
//    @Test
//    void shouldReturnAllServiceRequests() {
//
//        ServiceRequest request1 = new ServiceRequest();
//        ServiceRequest request2 = new ServiceRequest();
//
//        when(repository.findAll())
//                .thenReturn(Arrays.asList(request1, request2));
//
//        List<ServiceRequest> result = service.getAll();
//
//        assertEquals(2, result.size());
//
//        verify(repository, times(1))
//                .findAll();
//    }
//
//    @Test
//    void shouldReturnServiceRequestById() {
//
//        ServiceRequest request = new ServiceRequest();
//        request.setRequestId(1);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(request));
//
//        ServiceRequest result = service.getById(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getRequestId());
//    }
//
//    @Test
//    void shouldThrowExceptionWhenRequestNotFound() {
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceRequestNotFoundException.class,
//                () -> service.getById(100)
//        );
//    }
//
//    @Test
//    void shouldUpdateServiceRequest() {
//
//        ServiceRequest existing = new ServiceRequest();
//        existing.setRequestId(1);
//
//        ServiceRequestDTO dto = new ServiceRequestDTO();
//        dto.setPriority("HIGH");
//        dto.setStatus("INPROGRESS");
//        dto.setModifiedBy(2);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(existing));
//
//        when(repository.save(any(ServiceRequest.class)))
//                .thenReturn(existing);
//
//        ServiceRequest result =
//                service.update(1, dto);
//
//        assertNotNull(result);
//
//        verify(repository, times(1))
//                .save(existing);
//    }
//
//    @Test
//    void shouldThrowExceptionWhileUpdatingInvalidId() {
//
//        ServiceRequestDTO dto =
//                new ServiceRequestDTO();
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceRequestNotFoundException.class,
//                () -> service.update(100, dto)
//        );
//    }
//
//    @Test
//    void shouldDeleteServiceRequest() {
//
//        ServiceRequest request =
//                new ServiceRequest();
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(request));
//
//        service.delete(1);
//
//        verify(repository, times(1))
//                .delete(request);
//    }
//
//    @Test
//    void shouldThrowExceptionWhileDeletingInvalidId() {
//
//        when(repository.findById(100))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceRequestNotFoundException.class,
//                () -> service.delete(100)
//        );
//    }
//}