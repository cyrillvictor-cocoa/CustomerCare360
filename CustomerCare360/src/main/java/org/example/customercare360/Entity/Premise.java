package org.example.customercare360.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Premise")
public class Premise {

    @Id
    @Column(name = "PremiseId")
    private Integer premiseId;

    @Column(name = "Address")
    private String address;

    @Column(name = "Region")
    private String region;

    @Column(name = "MeterId")
    private Integer meterId;

    @Column(name = "Status")
    private String status;

    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    public Premise() {
    }

    public Integer getPremiseId() {
        return premiseId;
    }

    public void setPremiseId(Integer premiseId) {
        this.premiseId = premiseId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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