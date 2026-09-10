package org.example.customercare360.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "serviceorder")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "ServiceAccountID")
    private Integer serviceAccountId;

    @Column(name = "PremiseId")
    private Integer premiseId;

    @Column(name = "OrderType")
    private String orderType;

    @Column(name = "ScheduledDate")
    private LocalDateTime scheduledDate;

    @Column(name = "CompletionDate")
    private LocalDateTime completionDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "AssignedTo")
    private Integer assignedTo;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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
}
