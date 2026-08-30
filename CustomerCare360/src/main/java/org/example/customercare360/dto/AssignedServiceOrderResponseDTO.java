package org.example.customercare360.dto;

import java.util.List;

public class AssignedServiceOrderResponseDTO {

    private int totalRecords;
    private List<AssignedServiceOrderDTO> serviceOrders;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<AssignedServiceOrderDTO> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<AssignedServiceOrderDTO> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}