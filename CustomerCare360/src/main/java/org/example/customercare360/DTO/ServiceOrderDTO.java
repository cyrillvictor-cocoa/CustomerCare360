package org.example.customercare360.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServiceOrderDTO {
    private Integer serviceAccountId;

    private Integer premiseId;

    private String orderType;

    private LocalDateTime scheduledDate;

    private LocalDateTime completionDate;

    private String status;

    private Integer assignedTo;

    private Integer createdBy;

    private Integer modifiedBy;
}