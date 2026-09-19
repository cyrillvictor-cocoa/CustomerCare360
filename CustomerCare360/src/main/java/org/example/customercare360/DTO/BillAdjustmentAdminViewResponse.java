package org.example.customercare360.DTO;

public class BillAdjustmentAdminViewResponse {

    private Integer adjustmentId;
    private Integer billId;
    private String reason;
    private Double amountDelta;
    private Integer approvedBy;
    private String status;
    private Integer createdBy;
    private Integer modifiedBy;

    public BillAdjustmentAdminViewResponse(
            Integer adjustmentId,
            Integer billId,
            String reason,
            Double amountDelta,
            Integer approvedBy,
            String status,
            Integer createdBy,
            Integer modifiedBy) {

        this.adjustmentId = adjustmentId;
        this.billId = billId;
        this.reason = reason;
        this.amountDelta = amountDelta;
        this.approvedBy = approvedBy;
        this.status = status;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Integer getAdjustmentId() {
        return adjustmentId;
    }

    public Integer getBillId() {
        return billId;
    }

    public String getReason() {
        return reason;
    }

    public Double getAmountDelta() {
        return amountDelta;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }
}