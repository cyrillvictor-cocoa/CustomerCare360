package org.example.customercare360.dto;

public class AssignedServiceOrderDTO {

    private String orderId;
    private String customerName;
    private String status;
    private String priority;

    public AssignedServiceOrderDTO() {
    }

    public AssignedServiceOrderDTO(String orderId,
                                   String customerName,
                                   String status,
                                   String priority) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        this.priority = priority;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}