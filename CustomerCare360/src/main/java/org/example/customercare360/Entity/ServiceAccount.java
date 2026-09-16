package org.example.customercare360.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "serviceaccount")
public class ServiceAccount {

    @Id
    @Column(name = "AccountId")
    private Integer accountId;

    @Column(name = "CustomerId")
    private Integer customerId;

    @Column(name = "ServiceType")
    private String serviceType;

    @Column(name = "Status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "CustomerId",
            referencedColumnName = "UserId",
            insertable = false,
            updatable = false
    )
    private Customer customer;

    // getters setters
}