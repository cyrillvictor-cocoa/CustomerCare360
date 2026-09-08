package org.example.customercare360.DTO;

public class BillAdjustmentResponse {

    private Integer adjustmentId;
    private String status;

    public BillAdjustmentResponse() {
    }

    public BillAdjustmentResponse(
            Integer adjustmentId,
            String status) {

        this.adjustmentId = adjustmentId;
        this.status = status;
    }

    public Integer getAdjustmentId() {
        return adjustmentId;
    }

    public void setAdjustmentId(Integer adjustmentId) {
        this.adjustmentId = adjustmentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}