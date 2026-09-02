package org.example.customercare360.Controller;

import org.example.customercare360.DTO.*;
import org.example.customercare360.Services.HelpDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/helpdesk")
public class HelpDeskController {

    @Autowired
    private HelpDeskService helpDeskService;

    @GetMapping("/dashboard")
    public Object getDashboard() {
        return helpDeskService.getDashboard();
    }

    @GetMapping("/faqs")
    public List<FaqResponse> getFaqs() {

        return helpDeskService.getFaqs();
    }

    @GetMapping("/complaints")
    public List<ComplaintResponse> getComplaints() {

        return helpDeskService.getComplaints();
    }

    @PostMapping("/complaints")
    public ComplaintResponse createComplaint(
            @RequestBody ComplaintRequest request) {

        return helpDeskService.createComplaint(request);
    }
    @GetMapping("/billadjustments")
    public List<BillAdjustmentResponse>
    getBillAdjustments() {

        return helpDeskService
                .getBillAdjustments();
    }

    @PostMapping("/billadjustments")
    public BillAdjustmentResponse
    createBillAdjustment(
            @RequestBody
            BillAdjustmentRequest request) {

        return helpDeskService
                .createBillAdjustment(request);
    }


}