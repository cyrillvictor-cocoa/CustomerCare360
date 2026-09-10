//package org.example.customercare360.Services;
//
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
//    void testCreate() {
//
//        ServiceRequest request = new ServiceRequest();
//
//        when(repository.save(any(ServiceRequest.class)))
//                .thenReturn(request);
//
//        ServiceRequest result = service.create(request);
//
//        assertNotNull(result);
//        verify(repository, times(1)).save(request);
//    }
//
//    @Test
//    void testGetById() {
//
//        ServiceRequest request = new ServiceRequest();
//        request.setRequestId(1);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(request));
//
//        ServiceRequest result = service.getById(1);
//
//        assertEquals(1, result.getRequestId());
//    }
//
//    @Test
//    void testGetByIdThrowsException() {
//
//        when(repository.findById(1))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceRequestNotFoundException.class,
//                () -> service.getById(1)
//        );
//    }
//
//    @Test
//    void testGetAll() {
//
//        List<ServiceRequest> requests = Arrays.asList(
//                new ServiceRequest(),
//                new ServiceRequest()
//        );
//
//        when(repository.findAll()).thenReturn(requests);
//
//        List<ServiceRequest> result = service.getAll();
//
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    void testUpdate() {
//
//        ServiceRequest existing = new ServiceRequest();
//        existing.setRequestId(1);
//
//        ServiceRequest updated = new ServiceRequest();
//        updated.setPriority("HIGH");
//        updated.setStatus("OPEN");
//        updated.setModifiedBy(1);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(existing));
//
//        when(repository.save(any(ServiceRequest.class)))
//                .thenReturn(existing);
//
//        ServiceRequest result =
//                service.update(1, updated);
//
//        assertNotNull(result);
//
//        verify(repository, times(1))
//                .save(existing);
//    }
//
//    @Test
//    void testDelete() {
//
//        ServiceRequest request = new ServiceRequest();
//        request.setRequestId(1);
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
//    void testDeleteThrowsException() {
//
//        when(repository.findById(1))
//                .thenReturn(Optional.empty());
//
//        assertThrows(
//                ServiceRequestNotFoundException.class,
//                () -> service.delete(1)
//        );
//    }
//}