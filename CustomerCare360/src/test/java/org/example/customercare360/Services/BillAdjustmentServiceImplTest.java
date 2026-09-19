package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillAdjustmentAdminViewResponse;
import org.example.customercare360.DTO.BillAdjustmentRequest;
import org.example.customercare360.DTO.BillAdjustmentResponse;
import org.example.customercare360.DTO.BillAdjustmentUpdateRequest;
import org.example.customercare360.Entity.Bill;
import org.example.customercare360.Entity.BillAdjustment;
import org.example.customercare360.Enums.BillStatus;
import org.example.customercare360.Exception.AdjustmentAlreadyProcessedException;
import org.example.customercare360.Repository.BillAdjustmentRepository;
import org.example.customercare360.Repository.BillRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillAdjustmentServiceImplTest {

    @Mock
    private BillAdjustmentRepository billAdjustmentRepository;

    @Mock
    private BillRepository billRepository;

    private BillAdjustmentServiceImpl billAdjustmentService;

    @BeforeEach
    void setUp() {
        billAdjustmentService =
                new BillAdjustmentServiceImpl(
                        billAdjustmentRepository,
                        billRepository
                );
    }

    // ---------------------------------------------------------
    // TEST 1 - GET BILL ADJUSTMENTS BY BILL ID
    // ---------------------------------------------------------

    @Test
    void getBillAdjustments_shouldReturnAdjustments() {

        // Arrange
        BillAdjustment adjustment = new BillAdjustment();
        adjustment.setAdjustmentId(1);
        adjustment.setBillId(10);
        adjustment.setStatus("REQUESTED");

        when(billAdjustmentRepository.findByBillId(10))
                .thenReturn(List.of(adjustment));

        // Act
        List<BillAdjustmentResponse> result =
                billAdjustmentService.getBillAdjustments(10);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(billAdjustmentRepository, times(1))
                .findByBillId(10);
    }


    // ---------------------------------------------------------
    // TEST 2 - CREATE BILL ADJUSTMENT
    // ---------------------------------------------------------

    @Test
    void createBillAdjustment_shouldCreateAdjustment() {

        // Arrange
        BillAdjustmentRequest request =
                new BillAdjustmentRequest();

        request.setBillId(10);
        request.setReason("Incorrect bill amount");
        request.setAmountDelta(500.0);
        request.setApprovedBy(6);

        BillAdjustment savedAdjustment =
                new BillAdjustment();

        savedAdjustment.setAdjustmentId(1);
        savedAdjustment.setBillId(10);
        savedAdjustment.setReason("Incorrect bill amount");
        savedAdjustment.setAmountDelta(500.0);
        savedAdjustment.setApprovedBy(6);
        savedAdjustment.setStatus("REQUESTED");

        when(billAdjustmentRepository.save(any(BillAdjustment.class)))
                .thenReturn(savedAdjustment);

        // Act
        BillAdjustmentResponse response =
                billAdjustmentService.createBillAdjustment(request);

        // Assert
        assertNotNull(response);

        assertEquals(
                1,
                response.getAdjustmentId()
        );

        assertEquals(
                "REQUESTED",
                response.getStatus()
        );

        verify(billAdjustmentRepository, times(1))
                .save(any(BillAdjustment.class));
    }


    // ---------------------------------------------------------
    // TEST 3 - GET ALL ADJUSTMENTS FOR ADMIN
    // ---------------------------------------------------------

    @Test
    void getAllBillAdjustmentsForAdmin_shouldReturnAllAdjustments() {

        // Arrange
        BillAdjustment adjustment1 =
                new BillAdjustment();

        adjustment1.setAdjustmentId(1);
        adjustment1.setBillId(10);
        adjustment1.setReason("Incorrect amount");
        adjustment1.setAmountDelta(500.0);
        adjustment1.setApprovedBy(6);
        adjustment1.setStatus("REQUESTED");

        BillAdjustment adjustment2 =
                new BillAdjustment();

        adjustment2.setAdjustmentId(2);
        adjustment2.setBillId(11);
        adjustment2.setReason("Wrong usage");
        adjustment2.setAmountDelta(200.0);
        adjustment2.setApprovedBy(7);
        adjustment2.setStatus("APPROVED");

        when(billAdjustmentRepository.findAll())
                .thenReturn(List.of(adjustment1, adjustment2));

        // Act
        List<BillAdjustmentAdminViewResponse> result =
                billAdjustmentService.getAllBillAdjustmentsForAdmin();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(billAdjustmentRepository, times(1))
                .findAll();
    }


    // ---------------------------------------------------------
    // TEST 4 - UPDATE ADJUSTMENT - APPROVED
    // ---------------------------------------------------------

    @Test
    void updateBillAdjustment_whenApproved_shouldUpdateBillAmount() {

        // Arrange

        BillAdjustment adjustment =
                new BillAdjustment();

        adjustment.setAdjustmentId(1);
        adjustment.setBillId(10);
        adjustment.setAmountDelta(500.0);
        adjustment.setStatus("REQUESTED");

        Bill bill = new Bill();

        bill.setBillId(10);
        bill.setAmount(2500.0);

        BillAdjustmentUpdateRequest request =
                new BillAdjustmentUpdateRequest();

        request.setStatus("APPROVED");
        request.setApprovedBy(6);

        when(billAdjustmentRepository.findById(1))
                .thenReturn(Optional.of(adjustment));

        when(billRepository.findById(10))
                .thenReturn(Optional.of(bill));

        when(billRepository.save(any(Bill.class)))
                .thenReturn(bill);

        when(billAdjustmentRepository.save(any(BillAdjustment.class)))
                .thenReturn(adjustment);

        // Act

        BillAdjustmentResponse response =
                billAdjustmentService.updateBillAdjustment(
                        1,
                        request
                );

        // Assert

        assertNotNull(response);

        assertEquals(
                "APPROVED",
                response.getStatus()
        );

        assertEquals(
                2000.0,
                bill.getAmount()
        );

        assertEquals(
                BillStatus.ADJUSTED,
                bill.getStatus()
        );

        assertEquals(
                6,
                adjustment.getApprovedBy()
        );

        verify(billAdjustmentRepository, times(1))
                .findById(1);

        verify(billRepository, times(1))
                .findById(10);

        verify(billRepository, times(1))
                .save(bill);

        verify(billAdjustmentRepository, times(1))
                .save(adjustment);
    }


    // ---------------------------------------------------------
    // TEST 5 - UPDATE ADJUSTMENT - REJECTED
    // ---------------------------------------------------------

    @Test
    void updateBillAdjustment_whenRejected_shouldNotUpdateBill() {

        // Arrange

        BillAdjustment adjustment =
                new BillAdjustment();

        adjustment.setAdjustmentId(1);
        adjustment.setBillId(10);
        adjustment.setAmountDelta(500.0);
        adjustment.setStatus("REQUESTED");

        BillAdjustmentUpdateRequest request =
                new BillAdjustmentUpdateRequest();

        request.setStatus("REJECTED");
        request.setApprovedBy(6);

        when(billAdjustmentRepository.findById(1))
                .thenReturn(Optional.of(adjustment));

        when(billAdjustmentRepository.save(any(BillAdjustment.class)))
                .thenReturn(adjustment);

        // Act

        BillAdjustmentResponse response =
                billAdjustmentService.updateBillAdjustment(
                        1,
                        request
                );

        // Assert

        assertNotNull(response);

        assertEquals(
                "REJECTED",
                response.getStatus()
        );

        verify(billRepository, never())
                .findById(anyInt());

        verify(billRepository, never())
                .save(any(Bill.class));

        verify(billAdjustmentRepository, times(1))
                .save(adjustment);
    }


    // ---------------------------------------------------------
    // TEST 6 - ALREADY APPROVED
    // ---------------------------------------------------------

    @Test
    void updateBillAdjustment_whenAlreadyApproved_shouldThrowException() {

        // Arrange

        BillAdjustment adjustment =
                new BillAdjustment();

        adjustment.setAdjustmentId(1);
        adjustment.setStatus("APPROVED");

        BillAdjustmentUpdateRequest request =
                new BillAdjustmentUpdateRequest();

        request.setStatus("APPROVED");
        request.setApprovedBy(6);

        when(billAdjustmentRepository.findById(1))
                .thenReturn(Optional.of(adjustment));

        // Act + Assert

        AdjustmentAlreadyProcessedException exception =
                assertThrows(
                        AdjustmentAlreadyProcessedException.class,
                        () -> billAdjustmentService
                                .updateBillAdjustment(
                                        1,
                                        request
                                )
                );

        assertEquals(
                "Adjustment request already approved",
                exception.getMessage()
        );

        verify(billAdjustmentRepository, never())
                .save(any(BillAdjustment.class));

        verify(billRepository, never())
                .save(any(Bill.class));
    }


    // ---------------------------------------------------------
    // TEST 7 - ALREADY REJECTED
    // ---------------------------------------------------------

    @Test
    void updateBillAdjustment_whenAlreadyRejected_shouldThrowException() {

        // Arrange

        BillAdjustment adjustment =
                new BillAdjustment();

        adjustment.setAdjustmentId(1);
        adjustment.setStatus("REJECTED");

        BillAdjustmentUpdateRequest request =
                new BillAdjustmentUpdateRequest();

        request.setStatus("APPROVED");
        request.setApprovedBy(6);

        when(billAdjustmentRepository.findById(1))
                .thenReturn(Optional.of(adjustment));

        // Act + Assert

        AdjustmentAlreadyProcessedException exception =
                assertThrows(
                        AdjustmentAlreadyProcessedException.class,
                        () -> billAdjustmentService
                                .updateBillAdjustment(
                                        1,
                                        request
                                )
                );

        assertEquals(
                "Adjustment request already rejected",
                exception.getMessage()
        );

        verify(billAdjustmentRepository, never())
                .save(any(BillAdjustment.class));

        verify(billRepository, never())
                .save(any(Bill.class));
    }


    // ---------------------------------------------------------
    // TEST 8 - ADJUSTMENT DOES NOT EXIST
    // ---------------------------------------------------------

    @Test
    void updateBillAdjustment_whenAdjustmentNotFound_shouldThrowException() {

        // Arrange

        BillAdjustmentUpdateRequest request =
                new BillAdjustmentUpdateRequest();

        request.setStatus("APPROVED");
        request.setApprovedBy(6);

        when(billAdjustmentRepository.findById(999))
                .thenReturn(Optional.empty());

        // Act + Assert

        assertThrows(
                RuntimeException.class,
                () -> billAdjustmentService
                        .updateBillAdjustment(
                                999,
                                request
                        )
        );

        verify(billAdjustmentRepository, times(1))
                .findById(999);

        verify(billAdjustmentRepository, never())
                .save(any(BillAdjustment.class));

        verify(billRepository, never())
                .save(any(Bill.class));
    }
}