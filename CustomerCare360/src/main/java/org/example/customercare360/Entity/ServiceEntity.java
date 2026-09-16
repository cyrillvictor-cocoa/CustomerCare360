package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
