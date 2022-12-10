-- liquibase formatted sql

-- changeset ajay.ranwa:1670594736835-1
CREATE TABLE NOTIFICATION_STATUS
(
    NOTIFICATION_ID VARCHAR(36)             NOT NULL        DEFAULT 'uuid()',
    CHANNEL_TYPE    VARCHAR(100)            NOT NULL,
    SENT_TO            JSON                 NOT NULL,
    SENT_FROM          VARCHAR(255)         NULL,
    TITLE           VARCHAR(255)            NULL,
    MESSAGE         LONGTEXT                NOT NULL,
    SENT_AT         timestamp DEFAULT NOW() NOT NULL,
    CONSTRAINT pk_notification_status PRIMARY KEY (NOTIFICATION_ID)
);


-- changeset ajay:1670594736835-3
ALTER TABLE NOTIFICATION_STATUS
    ADD NOTIFICATION_STATUS VARCHAR(100) NOT NULL;