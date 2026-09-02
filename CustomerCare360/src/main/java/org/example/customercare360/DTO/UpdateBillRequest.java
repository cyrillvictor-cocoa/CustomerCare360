package org.example.customercare360.DTO;

import java.time.LocalDateTime;

public class UpdateBillRequest {

    private String usage;

    private Double amount;

    private LocalDateTime dueDate;

    public UpdateBillRequest() {
    }

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
}