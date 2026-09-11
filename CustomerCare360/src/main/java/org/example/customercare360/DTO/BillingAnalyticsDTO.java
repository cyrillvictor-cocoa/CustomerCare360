package org.example.customercare360.DTO;

public class BillingAnalyticsDTO {

    private long totalBills;
    private long totalAdjustments;
    private double billAdjustmentRate;
    private double billingAccuracy;

    public long getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(long totalBills) {
        this.totalBills = totalBills;
    }

    public long getTotalAdjustments() {
        return totalAdjustments;
    }

    public void setTotalAdjustments(long totalAdjustments) {
        this.totalAdjustments = totalAdjustments;
    }

    public double getBillAdjustmentRate() {
        return billAdjustmentRate;
    }

    public void setBillAdjustmentRate(double billAdjustmentRate) {
        this.billAdjustmentRate = billAdjustmentRate;
    }

    public double getBillingAccuracy() {
        return billingAccuracy;
    }

    public void setBillingAccuracy(double billingAccuracy) {
        this.billingAccuracy = billingAccuracy;
    }

}