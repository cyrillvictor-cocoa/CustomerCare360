package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "serviceorder")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "ServiceAccountID")
    private Integer serviceAccountId;

    @Column(name = "PremiseId")
    private Integer premiseId;

    @Column(name = "OrderType")
    private String orderType;

    @Column(name = "ScheduledDate")
    private LocalDateTime scheduledDate;

    @Column(name = "CompletionDate")
    private LocalDateTime completionDate;

    @Column(name = "Status")
    private String status;

    @Column(name = "AssignedTo")
    private Integer assignedTo;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

}
