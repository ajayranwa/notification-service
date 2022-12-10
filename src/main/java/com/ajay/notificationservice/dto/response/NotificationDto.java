package com.ajay.notificationservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * A DTO for the {@link com.ajay.notificationservice.model.Notification} entity
 */
@Data
@Builder
public class NotificationDto implements Serializable {
    private String notificationId;
    private String channelType;
    private List<String> to;
    private String from;
    private String title;
    private String message;
    private String notificationStatus;
    private Timestamp sentAt;
}