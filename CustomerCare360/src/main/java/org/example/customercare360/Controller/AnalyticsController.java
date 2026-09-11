package org.example.customercare360.Controller;

import org.example.customercare360.DTO.BillingAnalyticsDTO;
import org.example.customercare360.DTO.ChurnAnalyticsDTO;
import org.example.customercare360.DTO.SlaAnalyticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.customercare360.DTO.ChurnAnalyticsDTO;

import org.example.customercare360.DTO.DashboardDTO;
import org.example.customercare360.Services.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> getDashboardMetrics() {

        DashboardDTO response =
                analyticsService.getDashboardMetrics();

        return ResponseEntity.ok(response);
    }
    @GetMapping("/billing")
    public ResponseEntity<BillingAnalyticsDTO> getBillingAnalytics() {

        return ResponseEntity.ok(
                analyticsService.getBillingAnalytics()
        );
    }

    @GetMapping("/sla")
    public ResponseEntity<SlaAnalyticsDTO> getSlaAnalytics() {

        return ResponseEntity.ok(
                analyticsService.getSlaAnalytics()
        );
    }

    @GetMapping("/churn")
    public ResponseEntity<ChurnAnalyticsDTO> getChurnAnalytics() {

        return ResponseEntity.ok(analyticsService.getChurnAnalytics());
    }


}