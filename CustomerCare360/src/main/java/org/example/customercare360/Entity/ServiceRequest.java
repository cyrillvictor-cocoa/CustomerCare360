package org.example.customercare360.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "servicerequest")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RequestId")
    private Integer requestId;

    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "RequestType")
    private String requestType;

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate;

    @Column(name = "Priority")
    private String priority;

    @Column(name = "Status")
    private String status;

    @Column(name = "ServiceType")
    private String serviceType;

    @Column(name = "PremiseId")
    private Integer premiseId;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

}
