package com.ajay.notificationservice.mapper;

import com.ajay.notificationservice.dto.request.NotificationRequestDto;
import com.ajay.notificationservice.dto.response.NotificationDto;
import com.ajay.notificationservice.model.Notification;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface NotificationMapper {
    Notification notificationRequestDtoToEntity(NotificationRequestDto notificationRequestDto);

    NotificationRequestDto toNotificationRequestDto(Notification notification);
    Notification toEntity(NotificationDto notificationDto);

    NotificationDto toDto(Notification notification);
}