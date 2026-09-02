package org.example.customercare360.Controller;

import org.example.customercare360.DTO.BillingCycleRequest;
import org.example.customercare360.Entity.BillingCycle;
import org.example.customercare360.Services.BillingCycleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/billing-cycles")
public class BillingCycleController {

    private final BillingCycleService billingCycleService;

    public BillingCycleController(BillingCycleService billingCycleService) {
        this.billingCycleService = billingCycleService;
    }

    @PostMapping
    public BillingCycle createCycle(
            @RequestBody BillingCycleRequest request) {

        return billingCycleService.createCycle(request);
    }

    @GetMapping
    public List<BillingCycle> getAllCycles() {

        return billingCycleService.getAllCycles();
    }

    @PutMapping("/{cycleId}")
    public String updateCycle(
            @PathVariable Integer cycleId,
            @RequestBody BillingCycleRequest request) {

        return billingCycleService.updateCycle(
                cycleId,
                request
        );
    }

    @DeleteMapping("/{cycleId}")
    public String deleteCycle(
            @PathVariable Integer cycleId) {

        return billingCycleService.deleteCycle(cycleId);
    }
}