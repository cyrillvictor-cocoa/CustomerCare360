package org.example.customercare360.DTO;

import java.time.LocalDateTime;

public class ServiceOrderDTO {
    private Integer serviceAccountId;

    private Integer premiseId;

    private String orderType;

    private LocalDateTime scheduledDate;

    private LocalDateTime completionDate;

    private String status;

    private Integer assignedTo;

    private Integer createdBy;

    private Integer modifiedBy;

    public Integer getServiceAccountId() {
        return serviceAccountId;
    }

    public void setServiceAccountId(Integer serviceAccountId) {
        this.serviceAccountId = serviceAccountId;
    }

    public Integer getPremiseId() {
        return premiseId;
    }

    public void setPremiseId(Integer premiseId) {
        this.premiseId = premiseId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public LocalDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
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