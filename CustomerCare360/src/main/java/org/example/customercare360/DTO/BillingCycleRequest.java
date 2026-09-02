package org.example.customercare360.DTO;

import org.example.customercare360.Enums.ServiceType;

import java.time.LocalDateTime;

public class BillingCycleRequest {


    private ServiceType serviceType;

    private LocalDateTime periodStart;

    private LocalDateTime periodEnd;

    public BillingCycleRequest() {
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public LocalDateTime getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(LocalDateTime periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDateTime getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(LocalDateTime periodEnd) {
        this.periodEnd = periodEnd;
    }
}