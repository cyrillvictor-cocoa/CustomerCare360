package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;

@Getter
@Setter
@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name = "UserId")
public class Customer extends User {

    @Enumerated(EnumType.STRING)
    @Column(name = "CustomerType")
    private CustomerType customerType;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ModifiedBy")
    private User modifiedBy;
}
