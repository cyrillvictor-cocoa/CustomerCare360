package org.example.customercare360.Services;

import org.example.customercare360.DTO.ComplaintRequest;
import org.example.customercare360.DTO.ComplaintResponse;
import org.example.customercare360.Entity.Complaint;
import org.example.customercare360.Repository.ComplaintRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComplaintServicesImplTest {

    @Mock
    private ComplaintRepository complaintRepository;

    @InjectMocks
    private ComplaintServicesImpl complaintServices;

    @Test
    void testGetComplaints() {

        Complaint complaint1 = new Complaint();
        complaint1.setComplaintId(1);
        complaint1.setStatus("OPEN");

        Complaint complaint2 = new Complaint();
        complaint2.setComplaintId(2);
        complaint2.setStatus("CLOSED");

        when(complaintRepository.findAll())
                .thenReturn(List.of(complaint1, complaint2));

        List<ComplaintResponse> responses =
                complaintServices.getComplaints();

        assertEquals(2, responses.size());

        assertEquals(1, responses.get(0).getComplaintId());
        assertEquals("OPEN", responses.get(0).getStatus());

        assertEquals(2, responses.get(1).getComplaintId());
        assertEquals("CLOSED", responses.get(1).getStatus());

        verify(complaintRepository, times(1)).findAll();
    }

    @Test
    void testCreateComplaint() {

        ComplaintRequest request = new ComplaintRequest();
        request.setCustomerId(101);
        request.setCategory("Billing");
        request.setDescription("Incorrect bill amount");

        Complaint savedComplaint = new Complaint();
        savedComplaint.setComplaintId(1);
        savedComplaint.setCustomerId(101);
        savedComplaint.setCategory("Billing");
        savedComplaint.setDescription("Incorrect bill amount");
        savedComplaint.setStatus("OPEN");
        savedComplaint.setLoggedDate(LocalDateTime.now());

        when(complaintRepository.save(any(Complaint.class)))
                .thenReturn(savedComplaint);

        ComplaintResponse response =
                complaintServices.createComplaint(request);

        assertNotNull(response);
        assertEquals(1, response.getComplaintId());
        assertEquals("OPEN", response.getStatus());

        verify(complaintRepository, times(1))
                .save(any(Complaint.class));
    }

    @Test
    void testUpdateComplaintStatus() {

        Complaint complaint = new Complaint();
        complaint.setComplaintId(1);
        complaint.setStatus("OPEN");

        when(complaintRepository.findById(1))
                .thenReturn(Optional.of(complaint));

        when(complaintRepository.save(any(Complaint.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ComplaintResponse response =
                complaintServices.updateComplaintStatus(
                        1,
                        "RESOLVED"
                );

        assertEquals(1, response.getComplaintId());
        assertEquals("RESOLVED", response.getStatus());

        verify(complaintRepository).findById(1);
        verify(complaintRepository).save(any(Complaint.class));
    }

    @Test
    void testUpdateComplaintStatus_ComplaintNotFound() {

        when(complaintRepository.findById(1))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> complaintServices.updateComplaintStatus(
                                1,
                                "RESOLVED"
                        )
                );

        assertEquals(
                "Complaint not found",
                exception.getMessage()
        );

        verify(complaintRepository).findById(1);
        verify(complaintRepository, never())
                .save(any(Complaint.class));
    }
}