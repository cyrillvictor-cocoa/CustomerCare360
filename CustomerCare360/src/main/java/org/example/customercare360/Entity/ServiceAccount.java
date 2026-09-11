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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "CustomerId",
            referencedColumnName = "CustomerId",
            insertable = false,
            updatable = false
    )
    private Customer customer;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}