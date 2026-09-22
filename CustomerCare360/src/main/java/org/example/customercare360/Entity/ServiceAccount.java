package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "serviceaccount")
public class ServiceAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountId")
    private Integer accountId;

    @Column(name = "ServiceType")
    private String serviceType;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "CustomerId",
            referencedColumnName = "UserId"
    )
    private Customer customer;

    // getters setters
    @Column(name = "StartDate")
    private LocalDateTime startDate;


    @Column(name = "EndDate")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PremiseID")
    private Premise premise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ModifiedBy")
    private User modifiedBy;

    public ServiceAccount() {
    }

}