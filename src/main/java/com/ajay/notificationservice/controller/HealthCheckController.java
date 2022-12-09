package com.ajay.notificationservice.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    @GetMapping("/status")
    @Timed(value = "template.time", description = "Time taken to methods in application")
    public ResponseEntity<Object> statusCheck() {
        Map<String, Object> statusCheckObj = new HashMap<>();
        statusCheckObj.put("status", "ok");

        return new ResponseEntity<>(statusCheckObj, HttpStatus.OK);
    }
}
