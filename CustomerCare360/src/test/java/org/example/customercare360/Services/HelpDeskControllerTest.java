package org.example.customercare360.Services;

import org.example.customercare360.Controller.HelpDeskController;
import org.example.customercare360.DTO.*;
import org.example.customercare360.Services.BillAdjustmentService;
import org.example.customercare360.Services.ComplaintServices;
import org.example.customercare360.Services.FAQService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HelpDeskControllerTest {

    @Mock
    private BillAdjustmentService helpDeskService;

    @Mock
    private FAQService faqService;

    @Mock
    private ComplaintServices complaintServices;

    @InjectMocks
    private HelpDeskController helpDeskController;


    // =========================================================
    // FAQ TEST
    // =========================================================

    @Test
    void getFaqs_shouldReturnFaqList() {

        // Arrange
        FaqResponse faq1 = new FaqResponse(
                "How can I raise a complaint?",
                "Navigate to the Complaints section and submit your issue."
        );

        FaqResponse faq2 = new FaqResponse(
                "How can I track my complaint status?",
                "Navigate to My Complaints and view the current status."
        );

        when(faqService.getFaqs())
                .thenReturn(List.of(faq1, faq2));

        // Act
        List<FaqResponse> result =
                helpDeskController.getFaqs();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(faqService, times(1))
                .getFaqs();
    }


    // =========================================================
    // GET COMPLAINTS TEST
    // =========================================================

    @Test
    void getComplaints_shouldReturnComplaints() {

        // Arrange
        ComplaintResponse response =
                mock(ComplaintResponse.class);

        when(complaintServices.getComplaints())
                .thenReturn(List.of(response));

        // Act
        List<ComplaintResponse> result =
                helpDeskController.getComplaints();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(complaintServices, times(1))
                .getComplaints();
    }


    // =========================================================
    // CREATE COMPLAINT TEST
    // =========================================================

    @Test
    void createComplaint_shouldCreateComplaint() {

        // Arrange
        ComplaintRequest request =
                mock(ComplaintRequest.class);

        ComplaintResponse response =
                mock(ComplaintResponse.class);

        when(complaintServices.createComplaint(request))
                .thenReturn(response);

        // Act
        ComplaintResponse result =
                helpDeskController.createComplaint(request);

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(complaintServices, times(1))
                .createComplaint(request);
    }


    // =========================================================
    // UPDATE COMPLAINT STATUS TEST
    // =========================================================

    @Test
    void updateComplaintStatus_shouldUpdateComplaint() {

        // Arrange
        Integer complaintId = 1;

        ComplaintStatusUpdateRequest request =
                mock(ComplaintStatusUpdateRequest.class);

        ComplaintResponse response =
                mock(ComplaintResponse.class);

        when(request.getStatus())
                .thenReturn("RESOLVED");

        when(complaintServices.updateComplaintStatus(
                complaintId,
                "RESOLVED"
        )).thenReturn(response);

        // Act
        ComplaintResponse result =
                helpDeskController.updateComplaintStatus(
                        complaintId,
                        request
                );

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(complaintServices, times(1))
                .updateComplaintStatus(
                        complaintId,
                        "RESOLVED"
                );
    }


    // =========================================================
    // GET BILL ADJUSTMENTS TEST
    // =========================================================

    @Test
    void getBillAdjustments_shouldReturnBillAdjustments() {

        // Arrange
        Integer billId = 10;

        BillAdjustmentResponse response =
                mock(BillAdjustmentResponse.class);

        when(helpDeskService.getBillAdjustments(billId))
                .thenReturn(List.of(response));

        // Act
        List<BillAdjustmentResponse> result =
                helpDeskController.getBillAdjustments(billId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(helpDeskService, times(1))
                .getBillAdjustments(billId);
    }


    // =========================================================
    // CREATE BILL ADJUSTMENT TEST
    // =========================================================

    @Test
    void createBillAdjustment_shouldCreateBillAdjustment() {

        // Arrange
        BillAdjustmentRequest request =
                mock(BillAdjustmentRequest.class);

        BillAdjustmentResponse response =
                mock(BillAdjustmentResponse.class);

        when(helpDeskService.createBillAdjustment(request))
                .thenReturn(response);

        // Act
        BillAdjustmentResponse result =
                helpDeskController.createBillAdjustment(request);

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(helpDeskService, times(1))
                .createBillAdjustment(request);
    }


    // =========================================================
    // ADMIN VIEW BILL ADJUSTMENT TEST
    // =========================================================

    @Test
    void getAllBillAdjustmentsForAdmin_shouldReturnAllAdjustments() {

        // Arrange
        BillAdjustmentAdminViewResponse response =
                mock(BillAdjustmentAdminViewResponse.class);

        when(helpDeskService.getAllBillAdjustmentsForAdmin())
                .thenReturn(List.of(response));

        // Act
        List<BillAdjustmentAdminViewResponse> result =
                helpDeskController.getAllBillAdjustmentsForAdmin();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(helpDeskService, times(1))
                .getAllBillAdjustmentsForAdmin();
    }


    // =========================================================
    // UPDATE BILL ADJUSTMENT TEST
    // =========================================================

    @Test
    void updateBillAdjustment_shouldUpdateBillAdjustment() {

        // Arrange
        Integer adjustmentId = 1;

        BillAdjustmentUpdateRequest request =
                mock(BillAdjustmentUpdateRequest.class);

        BillAdjustmentResponse response =
                mock(BillAdjustmentResponse.class);

        when(helpDeskService.updateBillAdjustment(
                adjustmentId,
                request
        )).thenReturn(response);

        // Act
        BillAdjustmentResponse result =
                helpDeskController.updateBillAdjustment(
                        adjustmentId,
                        request
                );

        // Assert
        assertNotNull(result);
        assertSame(response, result);

        verify(helpDeskService, times(1))
                .updateBillAdjustment(
                        adjustmentId,
                        request
                );
    }
}