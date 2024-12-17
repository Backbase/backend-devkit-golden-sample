package com.backbase.goldensample.token.controller;

import com.backbase.goldensample.token.model.Message;
import com.backbase.goldensample.token.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {
    private final ExampleService exampleService;

    @RequestMapping(method = RequestMethod.GET, value = "/message", produces = {
            "application/json"
    })
    @ResponseStatus(HttpStatus.OK)

    public Message getMessage() {
        return exampleService.getMessage();
    }
}