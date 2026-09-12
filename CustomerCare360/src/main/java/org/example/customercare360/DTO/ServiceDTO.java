package org.example.customercare360.DTO;

public class ServiceDTO {

    private Integer serviceId;

    private String serviceName;

    private Integer providerId;

    private Double pricePerCycle;

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