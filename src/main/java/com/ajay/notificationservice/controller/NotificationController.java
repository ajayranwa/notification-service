package com.ajay.notificationservice.controller;

import com.ajay.notificationservice.dto.request.NotificationRequestDto;
import com.ajay.notificationservice.dto.response.NotificationDto;
import com.ajay.notificationservice.mapper.NotificationMapper;
import com.ajay.notificationservice.model.Notification;
import com.ajay.notificationservice.service.FcmPushNotificationService;
import io.micrometer.core.annotation.Timed;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class NotificationController {

    private final NotificationMapper notificationMapper = Mappers.getMapper(NotificationMapper.class);

    FcmPushNotificationService fcmPushNotificationService;

    public NotificationController(
            FcmPushNotificationService fcmPushNotificationService) {
        Assert.notNull(fcmPushNotificationService, "fcmPushNotificationService must not be null");
        this.fcmPushNotificationService = fcmPushNotificationService;
    }

    @PostMapping("/notify")
    public ResponseEntity<NotificationDto> sendNotification(
            @RequestBody NotificationRequestDto notificationRequestDto) {
        Notification notification = fcmPushNotificationService.sendFcmPushNotificationToToken(notificationRequestDto);
        NotificationDto notificationDto = notificationMapper.toDto(notification);
        return new ResponseEntity<>(notificationDto, HttpStatus.OK);
    }

    @GetMapping("/status")
    @Timed(value = "template.time", description = "Time taken to methods in application")
    public ResponseEntity<Object> statusCheck() {
        Map<String, Object> statusCheckObj = new HashMap<>();
        statusCheckObj.put("status", "ok");

        return new ResponseEntity<>(statusCheckObj, HttpStatus.OK);
    }
}
