package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerId")
    private Integer customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "CustomerType")
    private CustomerType customerType;

    @Column(name = "ContactInfo")
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private CustomerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "UserId",
            referencedColumnName = "UserId"
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ModifiedBy")
    private User modifiedBy;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
