package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;

import java.util.List;

public interface HelpDeskService {

    List<HelpDeskDashboard> getDashboard();

    List<FaqResponse> getFaqs();

    List<ComplaintResponse> getComplaints();

    ComplaintResponse createComplaint(
            ComplaintRequest request
    );

    List<BillAdjustmentResponse> getBillAdjustments();

    BillAdjustmentResponse createBillAdjustment(
            BillAdjustmentRequest request
    );
}