package com.ajay.notificationservice.filter;

import com.ajay.notificationservice.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * This class enables application to log request,response
 */
@Component
public class LoggingFilter extends OncePerRequestFilter implements Ordered {
    private static final Logger LOGGER = LoggerUtil.getLogger(LoggingFilter.class);

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 8;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        long reqStartTime = System.currentTimeMillis();
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrapperResponse = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(wrappedRequest, wrapperResponse);
        long timeConsumed = System.currentTimeMillis() - reqStartTime;
        LOGGER.info("""
                        \n==========================
                         Request Details:{}\s
                         Response Details:{}\s
                         serving time:{}ms
                         =============================
                         """,
                getRequestDetails(wrappedRequest), getResponseDetails(wrapperResponse), timeConsumed);
        wrapperResponse.copyBodyToResponse();
        MDC.clear();
    }

    private Map<String, Object> getRequestDetails(ContentCachingRequestWrapper request) {
        Map<String, Object> requestTrace = new HashMap<>();
        requestTrace.put("client ip", request.getRemoteAddr());
        requestTrace.put("method", request.getMethod());
        requestTrace.put("headers", getHeaders(request));
        requestTrace.put("path", request.getRequestURI());
        requestTrace.put("query", request.getQueryString());
        requestTrace.put("parameters", request.getParameterMap());
        requestTrace.put("body", getReqBody(request));
        return requestTrace;
    }

    private Map<String, Object> getResponseDetails(ContentCachingResponseWrapper response) {
        Map<String, Object> responseTrace = new HashMap<>();
        responseTrace.put("status", response.getStatus());
        responseTrace.put("response", getResBody(response));
        return responseTrace;
    }

    private String getPayload(byte[] buf, String characterEncoding) {
        String payload = "";
        if (buf.length > 0) {
            try {
                payload = new String(buf, 0, buf.length, characterEncoding);
            } catch (UnsupportedEncodingException ex) {
                payload = "[unknown]";
            }
        }
        return payload;
    }

    private String getReqBody(ContentCachingRequestWrapper request) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null)
            return getPayload(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());

        return "";
    }

    private String getResBody(ContentCachingResponseWrapper response) {
        ContentCachingResponseWrapper wrapper =
                WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null)
            return getPayload(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());

        return "";
    }

    private Map<String, Object> getHeaders(HttpServletRequest request) {
        Map<String, Object> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        return headers;
    }

}
