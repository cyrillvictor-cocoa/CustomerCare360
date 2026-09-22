package org.example.customercare360.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Entity.Notification;
import org.example.customercare360.Entity.User;
import org.example.customercare360.Enums.NotificationCategory;

@Getter
@Setter
@JsonIgnoreProperties
public class NotificationSendRequest {

    @JsonProperty(required = true)
    @NotNull
    private NotificationCategory category;
    @JsonProperty(required = true)
    @NotNull
    private String message;
}
