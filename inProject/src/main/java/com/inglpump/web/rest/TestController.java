package com.inglpump.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inglpump.domain.User;
import com.inglpump.service.PumpService;
import com.inglpump.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private PumpService pumpService;

    @GetMapping("/test")
    public String sendEmail() {
        testService.sendMail();

        return "Sent successfully";
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") Integer userId) {
        User user = pumpService.getUser(userId);
        ObjectMapper objectMapper = new ObjectMapper();
        String asString = null;
        try {
            asString = objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return asString;
    }
}
