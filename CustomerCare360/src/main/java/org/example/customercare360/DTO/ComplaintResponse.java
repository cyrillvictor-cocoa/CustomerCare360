package org.example.customercare360.DTO;

public class ComplaintResponse {

    private Integer complaintId;
    private String status;

    public ComplaintResponse() {
    }

    public ComplaintResponse(Integer complaintId, String status) {
        this.complaintId = complaintId;
        this.status = status;
    }

    public Integer getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Integer complaintId) {
        this.complaintId = complaintId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}