# Notification Service
Java (Spring Boot) based Notification Service with multiple channel support


### Functional Requirements
1. Should support multiple channels (SMS, Email, FCM-Push notification etc.)
   1. Current Scope: FCM push notification
2. Should support multiple types for same channel.
3. Should support categorization based on priority (High priority or low priority)
   1. Current Scope: Only High Priority
4. Add audit functionality 

### Technologies
1. Java 11
2. Spring Boot - 2.7.4
3. Maven
4. Open Api (Swagger)

### APIs
1. /notify
2. 