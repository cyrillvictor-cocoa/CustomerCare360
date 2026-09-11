package org.example.customercare360.DTO;

public class ChurnAnalyticsDTO {

    private long totalCustomers;
    private long activeCustomers;
    private long inactiveCustomers;
    private double churnRate;

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public long getActiveCustomers() {
        return activeCustomers;
    }

    public void setActiveCustomers(long activeCustomers) {
        this.activeCustomers = activeCustomers;
    }

    public long getInactiveCustomers() {
        return inactiveCustomers;
    }

    public void setInactiveCustomers(long inactiveCustomers) {
        this.inactiveCustomers = inactiveCustomers;
    }

    public double getChurnRate() {
        return churnRate;
    }

    public void setChurnRate(double churnRate) {
        this.churnRate = churnRate;
    }
}