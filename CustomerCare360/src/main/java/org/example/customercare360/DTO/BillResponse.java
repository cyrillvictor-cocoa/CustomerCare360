package org.example.customercare360.DTO;

import java.time.LocalDateTime;

public class BillResponse {

    private Integer billId;
    private Integer accountId;
    //private Integer cycleId;
    private String usage;
    private Double amount;
    private LocalDateTime dueDate;
    private String status;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

//    public Integer getCycleId() {
//        return cycleId;
//    }
//
//    public void setCycleId(Integer cycleId) {
//        this.cycleId = cycleId;
//    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
