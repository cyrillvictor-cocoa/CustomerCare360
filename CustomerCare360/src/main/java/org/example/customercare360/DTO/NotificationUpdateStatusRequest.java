package org.example.customercare360.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.customercare360.Enums.NotificationStatus;

@Getter
@Setter
@JsonIgnoreProperties
public class NotificationUpdateStatusRequest {

    @JsonProperty(required = true)
    @NotNull
    private Integer notificationId;
    @JsonProperty(required = true)
    @NotNull
    private NotificationStatus status;
}
