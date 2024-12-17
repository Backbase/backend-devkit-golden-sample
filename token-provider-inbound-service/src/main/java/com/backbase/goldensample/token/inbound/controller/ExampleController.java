package com.backbase.goldensample.token.inbound.controller;

import com.backbase.goldensample.token.inbound.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public Message getMessage() {
        return new Message("Hello World");
    }
}