package org.example.customercare360.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignedServiceOrderDTO {

    private String orderId;
    private String customerName;
    private String status;
    //private String priority;

    public AssignedServiceOrderDTO() {
    }

    public AssignedServiceOrderDTO(String orderId,
                                   String customerName,
                                   String status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.status = status;
        //this.priority = priority;
    }

}