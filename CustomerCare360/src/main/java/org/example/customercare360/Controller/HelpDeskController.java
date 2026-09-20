package org.example.customercare360.Controller;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Services.ComplaintServices;
import org.example.customercare360.Services.FAQService;
import org.example.customercare360.Services.BillAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/helpdesk")
public class HelpDeskController {

    @Autowired
    private BillAdjustmentService helpDeskService;






        @Autowired
        private FAQService faqService;

        @GetMapping("/faqs")
        public List<FaqResponse> getFaqs() {
            return faqService.getFaqs();
        }


    @Autowired
    private ComplaintServices complaintServices;
    @GetMapping("/complaints")
    public List<ComplaintResponse> getComplaints() {

        return complaintServices.getComplaints();
    }

    @PostMapping("/complaints")
    public ComplaintResponse createComplaint(
            @RequestBody ComplaintRequest request) {

        return complaintServices.createComplaint(request);
    }

    @PutMapping("/complaints/{complaintId}")
    public ComplaintResponse updateComplaintStatus(
            @PathVariable Integer complaintId,
            @RequestBody ComplaintStatusUpdateRequest request) {

        return complaintServices.updateComplaintStatus(
                complaintId,
                request.getStatus());
    }

    @GetMapping("/billadjustments/{billId}")
    public List<BillAdjustmentResponse>
    getBillAdjustments(
            @PathVariable Integer billId) {

        return helpDeskService
                .getBillAdjustments(billId);
    }

    @PostMapping("/billadjustments")
    public BillAdjustmentResponse
    createBillAdjustment(
            @RequestBody
            BillAdjustmentRequest request) {

        return helpDeskService
                .createBillAdjustment(request);
    }

    @GetMapping("/billadjustments/adminview")
    public List<BillAdjustmentAdminViewResponse>
    getAllBillAdjustmentsForAdmin() {

        return helpDeskService
                .getAllBillAdjustmentsForAdmin();
    }

    @PutMapping("/billadjustments/{adjustmentId}")
    public BillAdjustmentResponse updateBillAdjustment(
            @PathVariable Integer adjustmentId,
            @RequestBody BillAdjustmentUpdateRequest request) {

        return helpDeskService.updateBillAdjustment(
                adjustmentId,
                request);
    }


}