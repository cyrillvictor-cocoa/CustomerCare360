package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ServiceAccountRequest {

    private Integer customerId;
    private Integer accountId;
    private LocalDateTime startDate;
    private String serviceType;
    private LocalDateTime endDate;
    private String status;
    private Integer premiseId;
    private Integer createdBy;
    private Integer modifiedBy;

}