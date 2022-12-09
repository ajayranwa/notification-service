package com.ajay.notificationservice.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

/**
 * @author amit.kumar
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final String CO_RELATION_ID = "co-relation-id";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MDC.put(CO_RELATION_ID, String.valueOf(UUID.randomUUID()));
        return true;
    }
}
