package org.example.customercare360.Services;

import org.example.customercare360.DTO.BillingAnalyticsDTO;
import org.example.customercare360.DTO.ChurnAnalyticsDTO;
import org.example.customercare360.DTO.DashboardDTO;
import org.example.customercare360.DTO.SlaAnalyticsDTO;
import org.example.customercare360.Entity.Complaint;
import org.example.customercare360.Repository.BillAdjustmentRepository;
import org.example.customercare360.Repository.BillRepository;
import org.example.customercare360.Repository.ComplaintRepository;
import org.example.customercare360.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.customercare360.Enums.CustomerStatus;
import java.time.Duration;
import java.util.List;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillAdjustmentRepository billAdjustmentRepository;

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public DashboardDTO getDashboardMetrics() {

        DashboardDTO dashboardDTO = new DashboardDTO();

        BillingAnalyticsDTO billingAnalytics = getBillingAnalytics();

        SlaAnalyticsDTO slaAnalytics = getSlaAnalytics();

        ChurnAnalyticsDTO churnAnalytics = getChurnAnalytics();

        long totalComplaints =
                complaintRepository.count();

        dashboardDTO.setBillingAccuracy(billingAnalytics.getBillingAccuracy());

        dashboardDTO.setSlaCompliance(slaAnalytics.getSlaCompliance());

        dashboardDTO.setTotalComplaints(totalComplaints);

        dashboardDTO.setResolvedComplaints(slaAnalytics.getResolvedComplaints());

        dashboardDTO.setHighRiskCustomers(churnAnalytics.getInactiveCustomers());

        return dashboardDTO;
    }

    @Override
    public BillingAnalyticsDTO getBillingAnalytics() {

        BillingAnalyticsDTO dto = new BillingAnalyticsDTO();

        long totalBills = billRepository.count();

        long totalAdjustments = billAdjustmentRepository.count();

        double adjustmentRate = 0.0;
        double billingAccuracy = 100.0;

        if (totalBills > 0) {

            adjustmentRate = ((double) totalAdjustments / totalBills) * 100;

            billingAccuracy = 100 - adjustmentRate;
        }

        dto.setTotalBills(totalBills);

        dto.setTotalAdjustments(totalAdjustments);

        dto.setBillAdjustmentRate(Math.round(adjustmentRate * 100.0) / 100.0);

        dto.setBillingAccuracy(Math.round(billingAccuracy * 100.0) / 100.0);

        return dto;
    }

    @Override
    public SlaAnalyticsDTO getSlaAnalytics() {
        SlaAnalyticsDTO dto = new SlaAnalyticsDTO();

        List<Complaint> complaints = complaintRepository.findAll();

        long totalComplaints = complaints.size();
        long resolvedComplaints = 0;
        long slaBreachedComplaints = 0;
        double totalResolutionHours = 0;

        for (Complaint complaint : complaints) {

            if (complaint.getCreatedAt() != null && complaint.getResolvedAt() != null) {

                resolvedComplaints++;

                long resolutionHours = Duration.between(complaint.getCreatedAt(), complaint.getResolvedAt()).toHours();

                totalResolutionHours += resolutionHours;

                if (complaint.getSlaHours() != null && resolutionHours > complaint.getSlaHours()) {

                    slaBreachedComplaints++;
                }
            }
        }

        double averageResolutionHours = 0.0;

        if (resolvedComplaints > 0) {

            averageResolutionHours = totalResolutionHours / resolvedComplaints;
        }

        double slaCompliance = 0.0;

        if (resolvedComplaints > 0) {

            slaCompliance = ((double) (resolvedComplaints - slaBreachedComplaints) / resolvedComplaints) * 100;
        }

        dto.setTotalComplaints(totalComplaints);

        dto.setResolvedComplaints(resolvedComplaints);

        dto.setSlaBreachedComplaints(slaBreachedComplaints);

        dto.setAverageResolutionHours(Math.round(averageResolutionHours * 100.0) / 100.0);

        dto.setSlaCompliance(Math.round(slaCompliance * 100.0) / 100.0);

        return dto;
    }

    @Override
    public ChurnAnalyticsDTO getChurnAnalytics() {

        ChurnAnalyticsDTO dto = new ChurnAnalyticsDTO();

        long totalCustomers = customerRepository.count();

        long activeCustomers = customerRepository.countByStatus(CustomerStatus.ACTIVE);

        long inactiveCustomers = customerRepository.countByStatus(CustomerStatus.INACTIVE);

        double churnRate = 0.0;

        if (totalCustomers > 0) {

            churnRate = ((double) inactiveCustomers / totalCustomers) * 100;
        }

        dto.setTotalCustomers(totalCustomers);

        dto.setActiveCustomers(activeCustomers);

        dto.setInactiveCustomers(inactiveCustomers);


        return dto;
    }
}