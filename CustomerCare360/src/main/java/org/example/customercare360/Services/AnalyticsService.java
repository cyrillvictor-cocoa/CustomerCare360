package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingAnalyticsDTO;
import org.example.customercare360.DTO.ChurnAnalyticsDTO;
import org.example.customercare360.DTO.DashboardDTO;
import org.example.customercare360.DTO.SlaAnalyticsDTO;

public interface AnalyticsService {

    DashboardDTO getDashboardMetrics();

    BillingAnalyticsDTO getBillingAnalytics();

    ChurnAnalyticsDTO getChurnAnalytics();

    SlaAnalyticsDTO getSlaAnalytics();
}