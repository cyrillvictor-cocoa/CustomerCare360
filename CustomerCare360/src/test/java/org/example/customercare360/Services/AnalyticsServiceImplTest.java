package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.Complaint;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Repository.BillAdjustmentRepository;
import org.example.customercare360.Repository.BillRepository;
import org.example.customercare360.Repository.ComplaintRepository;
import org.example.customercare360.Repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceImplTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private BillAdjustmentRepository billAdjustmentRepository;

    @Mock
    private ComplaintRepository complaintRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AnalyticsServiceImpl analyticsService;

    @Test
    void testGetBillingAnalytics() {

        when(billRepository.count()).thenReturn(100L);
        when(billAdjustmentRepository.count()).thenReturn(10L);

        BillingAnalyticsDTO result =
                analyticsService.getBillingAnalytics();

        assertNotNull(result);
        assertEquals(100L, result.getTotalBills());
        assertEquals(10L, result.getTotalAdjustments());
        assertEquals(10.0, result.getBillAdjustmentRate());
        assertEquals(90.0, result.getBillingAccuracy());

        verify(billRepository).count();
        verify(billAdjustmentRepository).count();
    }

    @Test
    void testGetBillingAnalytics_NoBills() {

        when(billRepository.count()).thenReturn(0L);
        when(billAdjustmentRepository.count()).thenReturn(0L);

        BillingAnalyticsDTO result =
                analyticsService.getBillingAnalytics();

        assertEquals(0L, result.getTotalBills());
        assertEquals(0L, result.getTotalAdjustments());
        assertEquals(0.0, result.getBillAdjustmentRate());
        assertEquals(100.0, result.getBillingAccuracy());
    }

    @Test
    void testGetChurnAnalytics() {

        when(customerRepository.count()).thenReturn(100L);

        when(customerRepository.countByStatus(CustomerStatus.ACTIVE))
                .thenReturn(80L);

        when(customerRepository.countByStatus(CustomerStatus.INACTIVE))
                .thenReturn(20L);

        ChurnAnalyticsDTO result =
                analyticsService.getChurnAnalytics();

        assertNotNull(result);

        assertEquals(100L, result.getTotalCustomers());
        assertEquals(80L, result.getActiveCustomers());
        assertEquals(20L, result.getInactiveCustomers());
        assertEquals(20.0, result.getChurnRate());
    }

    @Test
    void testGetChurnAnalytics_NoCustomers() {

        when(customerRepository.count()).thenReturn(0L);

        when(customerRepository.countByStatus(CustomerStatus.ACTIVE))
                .thenReturn(0L);

        when(customerRepository.countByStatus(CustomerStatus.INACTIVE))
                .thenReturn(0L);

        ChurnAnalyticsDTO result =
                analyticsService.getChurnAnalytics();

        assertEquals(0L, result.getTotalCustomers());
        assertEquals(0.0, result.getChurnRate());
    }

    @Test
    void testGetSlaAnalytics() {

        Complaint complaint1 = new Complaint();

        complaint1.setCreatedAt(
                LocalDateTime.of(2025,1,1,10,0));

        complaint1.setResolvedAt(
                LocalDateTime.of(2025,1,1,15,0));

        complaint1.setSlaHours(8);

        Complaint complaint2 = new Complaint();

        complaint2.setCreatedAt(
                LocalDateTime.of(2025,1,1,10,0));

        complaint2.setResolvedAt(
                LocalDateTime.of(2025,1,2,10,0));

        complaint2.setSlaHours(12);

        when(complaintRepository.findAll())
                .thenReturn(List.of(
                        complaint1,
                        complaint2
                ));

        SlaAnalyticsDTO result =
                analyticsService.getSlaAnalytics();

        assertNotNull(result);

        assertEquals(2L, result.getTotalComplaints());
        assertEquals(2L, result.getResolvedComplaints());
        assertEquals(1L, result.getSlaBreachedComplaints());

        assertEquals(50.0, result.getSlaCompliance());
    }

    @Test
    void testGetSlaAnalytics_NoComplaints() {

        when(complaintRepository.findAll())
                .thenReturn(List.of());

        SlaAnalyticsDTO result =
                analyticsService.getSlaAnalytics();

        assertEquals(0L, result.getTotalComplaints());
        assertEquals(0L, result.getResolvedComplaints());
        assertEquals(0L, result.getSlaBreachedComplaints());
        assertEquals(0.0, result.getSlaCompliance());
    }

    @Test
    void testGetDashboardMetrics() {

        when(billRepository.count()).thenReturn(100L);
        when(billAdjustmentRepository.count()).thenReturn(10L);

        when(customerRepository.count()).thenReturn(100L);
        when(customerRepository.countByStatus(CustomerStatus.ACTIVE))
                .thenReturn(80L);
        when(customerRepository.countByStatus(CustomerStatus.INACTIVE))
                .thenReturn(20L);

        Complaint complaint = new Complaint();

        complaint.setCreatedAt(
                LocalDateTime.of(2025,1,1,10,0));

        complaint.setResolvedAt(
                LocalDateTime.of(2025,1,1,14,0));

        complaint.setSlaHours(8);

        when(complaintRepository.findAll())
                .thenReturn(List.of(complaint));

        when(complaintRepository.count())
                .thenReturn(1L);

        DashboardDTO dashboard =
                analyticsService.getDashboardMetrics();

        assertNotNull(dashboard);

        assertEquals(90.0,
                dashboard.getBillingAccuracy());

        assertEquals(100.0,
                dashboard.getSlaCompliance());

        assertEquals(1L,
                dashboard.getTotalComplaints());

        assertEquals(1L,
                dashboard.getResolvedComplaints());

        assertEquals(20L,
                dashboard.getHighRiskCustomers());
    }
}