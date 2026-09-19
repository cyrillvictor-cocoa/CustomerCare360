package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResponse {

    private Integer serviceId;

    private String serviceName;

    private Integer providerId;

    private Double pricePerCycle;

    private Integer cyclePeriod;
}