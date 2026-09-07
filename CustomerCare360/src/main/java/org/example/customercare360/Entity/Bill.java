package org.example.customercare360.Entity;

import jakarta.persistence.*;
import org.example.customercare360.Enums.BillStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    private Integer billId;

    @Column(name = "AccountId")
    private Integer accountId;

    @Column(name = "CycleId")
    private Integer cycleId;

    @Column(name = "Usage")
    private String usage;

    @Column(name = "Amount")
    private Double amount;

    @Column(name = "DueDate")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private BillStatus status;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public BillStatus getStatus() {
        return status;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }
}
