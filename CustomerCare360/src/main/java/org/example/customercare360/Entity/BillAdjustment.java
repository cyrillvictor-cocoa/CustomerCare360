package org.example.customercare360.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adjustment")
public class BillAdjustment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AdjustmentId")
    private Integer adjustmentId;

    @Column(name = "BillId")
    private Integer billId;

    @Column(name = "Reason")
    private String reason;

    @Column(name = "AmountDelta")
    private Double amountDelta;

    @Column(name = "ApprovedBy")
    private Integer approvedBy;

    @Column(name = "Status")
    private String status;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    public Integer getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Integer adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}