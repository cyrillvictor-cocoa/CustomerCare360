package org.example.customercare360.Controller;

import org.example.customercare360.DTO.BillResponse;
import org.example.customercare360.DTO.UpdateBillRequest;
import org.example.customercare360.Services.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/bills")
public class AdminBillController {

    private final BillService billService;

    public AdminBillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public List<BillResponse> getAllBills() {
        return billService.getAllBills();
    }

    @PutMapping("/{billId}")
    public String updateBill(
            @PathVariable Integer billId,
            @RequestBody UpdateBillRequest request) {

        return billService.updateBill(
                billId,
                request
        );
    }
}