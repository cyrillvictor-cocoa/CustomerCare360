package org.example.customercare360.Controller;

import org.example.customercare360.DTO.BillResponse;
import org.example.customercare360.DTO.DownloadBillResponse;
import org.example.customercare360.Services.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BillingController {

    private final BillService billService;

    public BillingController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/customers/{customerId}/bills")
    public List<BillResponse> getCustomerBills(
            @PathVariable Integer customerId) {

        return billService.getCustomerBills(customerId);
    }

    @GetMapping("/bills/{billId}/download")
    public DownloadBillResponse downloadBill(
            @PathVariable Integer billId) {

        return billService.downloadBill(billId);
    }
}