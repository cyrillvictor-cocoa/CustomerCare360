package org.example.customercare360.Entity;

import jakarta.persistence.*;

@Entity
@Table(name= "service")
public class ServiceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "provider_id")
    private Integer providerId;

    @Column(name = "pricepercycle")
    private Double pricePerCycle;

    @Column(name = "cycleperiod")
    private Integer cyclePeriod;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Double getPricePerCycle() {
        return pricePerCycle;
    }

    public void setPricePerCycle(Double pricePerCycle) {
        this.pricePerCycle = pricePerCycle;
    }

    public Integer getCyclePeriod() {
        return cyclePeriod;
    }

    public void setCyclePeriod(Integer cyclePeriod) {
        this.cyclePeriod = cyclePeriod;
    }
}
