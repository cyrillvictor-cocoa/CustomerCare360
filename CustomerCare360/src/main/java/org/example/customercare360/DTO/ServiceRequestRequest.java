package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestRequest {

    private Integer customerId;

    private String requestType;

    private String priority;

    private String status;

    private String serviceType;

    private Integer premiseId;

    private Integer createdBy;

    private Integer modifiedBy;
}