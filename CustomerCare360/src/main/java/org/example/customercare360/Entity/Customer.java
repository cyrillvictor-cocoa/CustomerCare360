package org.example.customercare360.Entity;

import jakarta.persistence.*;
import org.example.customercare360.Enums.CustomerStatus;
import org.example.customercare360.Enums.CustomerType;

@Entity
@Table(name="customer")
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
    private CustomerStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId",referencedColumnName = "UserId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ModifiedBy")
    private User modifiedBy;

    public Integer getCustomerId(){return customerId;}

    public String getCustomerType(){return customerType.name();}

    public String getContactInfo(){return contactInfo;}

    public String getStatus(){return status.name();}

    public User getUser(){return user;}

    public User getCreatedBy(){return createdBy;}

    public User getModifiedBy(){return modifiedBy;}

    public void setCustomerId(Integer id){this.customerId = id;}

    public void setCustomerType(CustomerType type){this.customerType = type;}

    public void setContactInfo(String contact){this.contactInfo = contact;}

    public void setStatus(CustomerStatus status){this.status = status;}

    public void setUser(User user){this.user = user;}

    public void setCreatedBy(User createdBy){this.createdBy = createdBy;}

    public void setModifiedBy(User modifiedBy){this.modifiedBy = modifiedBy;}
}
