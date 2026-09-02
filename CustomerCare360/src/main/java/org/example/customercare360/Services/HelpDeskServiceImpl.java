package org.example.customercare360.Services;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Entity.BillAdjustment;
import org.example.customercare360.Entity.Complaint;
import org.example.customercare360.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.customercare360.Repository.BillAdjustmentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HelpDeskServiceImpl implements HelpDeskService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private BillAdjustmentRepository billAdjustmentRepository;

    @Override
    public List<HelpDeskDashboard> getDashboard() {

        return List.of(
                new HelpDeskDashboard(
                        "FAQ",
                        "/api/v1/helpdesk/faqs"
                ),
                new HelpDeskDashboard(
                        "Complaints",
                        "/api/v1/helpdesk/complaints"
                )
        );
    }

    @Override
    public List<FaqResponse> getFaqs() {

        return List.of(
                new FaqResponse(
                        "How can I raise a complaint?",
                        "Navigate to the Complaints section and submit your issue."
                ),
                new FaqResponse(
                        "How can I track my complaint status?",
                        "Navigate to My Complaints and view the current status."
                )
        );
    }

    @Override
    public List<ComplaintResponse> getComplaints() {

        return complaintRepository.findAll()
                .stream()
                .map(complaint -> new ComplaintResponse(
                        complaint.getComplaintId(),
                        complaint.getStatus()
                ))
                .toList();
    }

    @Override
    public ComplaintResponse createComplaint(
            ComplaintRequest request) {

        Complaint complaint = new Complaint();

        complaint.setCustomerId(
                request.getCustomerId());

        complaint.setCategory(
                request.getCategory());

        complaint.setDescription(
                request.getDescription());

        complaint.setLoggedDate(
                LocalDateTime.now());

        complaint.setStatus(
                "OPEN");

        /*complaint.setCreatedBy(
                1);

        complaint.setModifiedBy(
                1);*/

        Complaint savedComplaint =
                complaintRepository.save(
                        complaint);

        return new ComplaintResponse(
                savedComplaint.getComplaintId(),
                savedComplaint.getStatus()
        );
    }

    @Override
    public List<BillAdjustmentResponse> getBillAdjustments() {

        return billAdjustmentRepository.findAll()
                .stream()
                .map(billAdjustment -> new BillAdjustmentResponse(
                        billAdjustment.getAdjustmentId(),
                        billAdjustment.getStatus()
                ))
                .toList();
    }

    @Override
    public BillAdjustmentResponse createBillAdjustment(
            BillAdjustmentRequest request) {

        BillAdjustment billAdjustment = new BillAdjustment();

        billAdjustment.setBillId(
                request.getBillId());

        billAdjustment.setReason(
                request.getReason());

        billAdjustment.setAmountDelta(
                request.getAmountDelta());

        billAdjustment.setApprovedBy(
                request.getApprovedBy());

        billAdjustment.setStatus(
                "REQUESTED");

        BillAdjustment savedBillAdjustment =
                billAdjustmentRepository.save(
                        billAdjustment);

        return new BillAdjustmentResponse(
                savedBillAdjustment.getAdjustmentId(),
                savedBillAdjustment.getStatus()
        );
    }

}