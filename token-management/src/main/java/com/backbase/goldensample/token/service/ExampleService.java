package com.backbase.goldensample.token.service;

import com.backbase.audit.client.annotation.AuditableOperation;
import com.backbase.audit.client.constants.AuditClientConstants;
import com.backbase.goldensample.token.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ExampleService {
    @AuditableOperation(eventCategory = "Example", objectType = "SampleMessage", eventAction = AuditClientConstants.READ)
    // just a sample
    @PreAuthorize("checkPermission('Token', 'Message', {'view'})")
    public Message getMessage() {
        log.info("getMessage is called at {}", LocalDateTime.now());
        return new Message("Hello World");
    }
}
