package com.ajay.notificationservice.model;

import com.ajay.notificationservice.util.StringArrayConverterUtil;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "NOTIFICATION_STATUS")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "NOTIFICATION_ID", nullable = false, length = 36)
    private String notificationId;

    @Column(name = "CHANNEL_TYPE", nullable = false, length = 100)
    private String channelType;

    @Column(name = "SENT_TO", nullable = false, columnDefinition = "json")
    @Convert(converter = StringArrayConverterUtil.class)
    private List<String> to;

    @Column(name = "SENT_FROM")
    private String from;

    @Column(name = "TITLE")
    private String title;

    @Lob
    @Column(name = "MESSAGE", length = 512, nullable = false)
    private String message;

    @Column(name = "NOTIFICATION_STATUS", length = 100, nullable = false)
    private String notificationStatus;

    @Column(name = "SENT_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp sentAt;
}
