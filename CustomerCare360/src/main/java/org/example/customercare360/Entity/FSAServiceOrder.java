package org.example.customercare360.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.DTO.FSAServiceOrderDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "vw_field_agent_orders")
public class FSAServiceOrder {

    @Id
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "CustomerName")
    private String customerName;

    @Column(name = "CustomerPhone")
    private String customerPhone;

    @Column(name = "AccountId")
    private Integer accountId;

    @Column(name = "ServiceType")
    private String serviceType;

    @Column(name = "AccountStatus")
    private String accountStatus;

    @Column(name = "OrderType")
    private String orderType;

    @Column(name = "ScheduledDate")
    private LocalDateTime scheduledDate;

    private LocalDateTime completionDate;

    @Column(name = "OrderStatus")
    private String orderStatus;

    @Column(name = "AgentId")
    private Integer agentId;

    @Column(name = "AgentName")
    private String agentName;

}
