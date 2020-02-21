package com.inglpump.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private MailService mailService;

    public void sendMail() {
        mailService.sendEmail("kshitij.yelpale@gmail.com", "Test email", "Empty", false, false);
    }
}
