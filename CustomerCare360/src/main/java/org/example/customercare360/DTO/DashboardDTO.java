package org.example.customercare360.DTO;

public class DashboardDTO {

    private double billingAccuracy;
    private double slaCompliance;
    private long totalComplaints;
    private long resolvedComplaints;
    private long highRiskCustomers;

    public DashboardDTO() {
    }

    public DashboardDTO(double billingAccuracy, double slaCompliance,
                        long totalComplaints, long resolvedComplaints,
                        long highRiskCustomers) {
        this.billingAccuracy = billingAccuracy;
        this.slaCompliance = slaCompliance;
        this.totalComplaints = totalComplaints;
        this.resolvedComplaints = resolvedComplaints;
        this.highRiskCustomers = highRiskCustomers;
    }

    public double getBillingAccuracy() {
        return billingAccuracy;
    }

    public void setBillingAccuracy(double billingAccuracy) {
        this.billingAccuracy = billingAccuracy;
    }

    public double getSlaCompliance() {
        return slaCompliance;
    }

    public void setSlaCompliance(double slaCompliance) {
        this.slaCompliance = slaCompliance;
    }

    public long getTotalComplaints() {
        return totalComplaints;
    }

    public void setTotalComplaints(long totalComplaints) {
        this.totalComplaints = totalComplaints;
    }

    public long getResolvedComplaints() {
        return resolvedComplaints;
    }

    public void setResolvedComplaints(long resolvedComplaints) {
        this.resolvedComplaints = resolvedComplaints;
    }

    public long getHighRiskCustomers() {
        return highRiskCustomers;
    }

    public void setHighRiskCustomers(long highRiskCustomers) {
        this.highRiskCustomers = highRiskCustomers;
    }
}