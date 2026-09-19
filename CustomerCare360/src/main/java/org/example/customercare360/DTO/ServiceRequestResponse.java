package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceRequestResponse {

    private Integer requestId;

    private Integer customerId;

    private String requestType;

    private LocalDateTime createdDate;

    private String priority;

    private String status;

    private String serviceType;

    private Integer premiseId;

    private Integer createdBy;

    private Integer modifiedBy;
}