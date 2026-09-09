package org.example.customercare360.DTO;

public class BillAdjustmentRequest {

    private Integer billId;
    private String reason;
    private Double amountDelta;
    private Integer approvedBy;
    private Integer createdBy;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getAmountDelta() {
        return amountDelta;
    }

    public void setAmountDelta(Double amountDelta) {
        this.amountDelta = amountDelta;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }
}