package com.ajay.notificationservice.service.impl;

import com.ajay.notificationservice.dto.request.NotificationRequestDto;
import com.ajay.notificationservice.mapper.NotificationMapper;
import com.ajay.notificationservice.model.Notification;
import com.ajay.notificationservice.repository.NotificationRepository;
import com.ajay.notificationservice.service.FcmPushNotificationService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class FcmPushNotificationServiceImpl implements FcmPushNotificationService {

    private final NotificationMapper notificationMapper = Mappers.getMapper(NotificationMapper.class);

    NotificationRepository notificationRepository;
    public FcmPushNotificationServiceImpl(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public Notification sendFcmPushNotificationToToken(NotificationRequestDto notificationRequestDto) {
        Notification notification = notificationMapper.notificationRequestDtoToEntity(notificationRequestDto);
        notification.setNotificationStatus("jj");

        System.out.println(notification);
        return notificationRepository.save(notification);
    }
}
