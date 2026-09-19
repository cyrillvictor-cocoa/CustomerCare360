package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;

import java.util.List;

public interface BillAdjustmentService {






    List<BillAdjustmentResponse> getBillAdjustments(Integer billId);

    BillAdjustmentResponse createBillAdjustment(
            BillAdjustmentRequest request
    );

    List<BillAdjustmentAdminViewResponse>
    getAllBillAdjustmentsForAdmin();

    BillAdjustmentResponse updateBillAdjustment(
            Integer adjustmentId,
            BillAdjustmentUpdateRequest request
    );



}