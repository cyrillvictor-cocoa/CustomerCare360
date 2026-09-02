package org.example.customercare360.Entity;

import jakarta.persistence.*;
import org.example.customercare360.Enums.ServiceType;

import java.time.LocalDateTime;

@Entity
@Table(name = "billingcycle")
public class BillingCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CycleId")
    private Integer cycleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ServiceType")
    private ServiceType serviceType;

    @Column(name = "PeriodStart")
    private LocalDateTime periodStart;

    @Column(name = "PeriodEnd")
    private LocalDateTime periodEnd;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

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
