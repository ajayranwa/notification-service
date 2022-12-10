package com.ajay.notificationservice.dto.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.ajay.notificationservice.model.Notification} entity
 */
@Data
public class NotificationRequestDto implements Serializable {
    private String channelType;
    private List<String> to;
    private String from;
    private String title;
    private String message;
}