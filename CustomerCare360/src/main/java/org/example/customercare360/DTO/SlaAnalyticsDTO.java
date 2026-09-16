package org.example.customercare360.DTO;

public class SlaAnalyticsDTO {

    private long totalComplaints;
    private long resolvedComplaints;
    private long slaBreachedComplaints;
    private double slaCompliance;
    private double averageResolutionHours;


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

    public long getSlaBreachedComplaints() {
        return slaBreachedComplaints;
    }

    public void setSlaBreachedComplaints(long slaBreachedComplaints) {
        this.slaBreachedComplaints = slaBreachedComplaints;
    }

    public double getSlaCompliance() {
        return slaCompliance;
    }

    public void setSlaCompliance(double slaCompliance) {
        this.slaCompliance = slaCompliance;
    }

    public double getAverageResolutionHours() {
        return averageResolutionHours;
    }

    public void setAverageResolutionHours(double averageResolutionHours) {
        this.averageResolutionHours = averageResolutionHours;
    }
}