package org.example.customercare360.dto;

public class ServiceOrderDTO {

    private String serviceOrderId;
    private String serviceOrderName;
    private String customerId;
    private String customerName;
    private String serviceOrderType;
    private String serviceOrderRequestDate;
    private String serviceOrderStatus;
    private Boolean fieldServiceAgentAssigned;
    private String fieldServiceAgentId;
    private String fieldServiceAgentName;

    public String getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(String serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    public String getServiceOrderName() {
        return serviceOrderName;
    }

    public void setServiceOrderName(String serviceOrderName) {
        this.serviceOrderName = serviceOrderName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceOrderType() {
        return serviceOrderType;
    }

    public void setServiceOrderType(String serviceOrderType) {
        this.serviceOrderType = serviceOrderType;
    }

    public String getServiceOrderRequestDate() {
        return serviceOrderRequestDate;
    }

    public void setServiceOrderRequestDate(String serviceOrderRequestDate) {
        this.serviceOrderRequestDate = serviceOrderRequestDate;
    }

    public String getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(String serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public Boolean getFieldServiceAgentAssigned() {
        return fieldServiceAgentAssigned;
    }

    public void setFieldServiceAgentAssigned(Boolean fieldServiceAgentAssigned) {
        this.fieldServiceAgentAssigned = fieldServiceAgentAssigned;
    }

    public String getFieldServiceAgentId() {
        return fieldServiceAgentId;
    }

    public void setFieldServiceAgentId(String fieldServiceAgentId) {
        this.fieldServiceAgentId = fieldServiceAgentId;
    }

    public String getFieldServiceAgentName() {
        return fieldServiceAgentName;
    }

    public void setFieldServiceAgentName(String fieldServiceAgentName) {
        this.fieldServiceAgentName = fieldServiceAgentName;
    }
}