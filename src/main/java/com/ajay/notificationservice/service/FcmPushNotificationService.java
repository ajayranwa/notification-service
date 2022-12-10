package com.ajay.notificationservice.service;

import com.ajay.notificationservice.dto.request.NotificationRequestDto;
import com.ajay.notificationservice.model.Notification;

import java.io.Serializable;

public interface FcmPushNotificationService extends Serializable {
    Notification sendFcmPushNotificationToToken(NotificationRequestDto notificationRequestDto);
}
