package com.inglpump.service;

import com.inglpump.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PumpService {

    Logger logger = LoggerFactory.getLogger(PumpService.class);

    @Autowired
    private User user;
    @Autowired
    private UserService userService;
    @Autowired
    private UserLogService userLogService;

    public double calculateInsulinDosage(double bloodGlucoseLevel) {

        double dosageAmount = 0;

        //TODO: Algorithm to find insulin dosage

        userLogService.logInsulinInjection(user, dosageAmount);

        return dosageAmount;
    }


    public double calculateGlucagonDosage(double bloodGlucoseLevel) {

        double dosageAmount = 0;

        //TODO: Algorithm to find glucagon dosage

        userLogService.logGlucagonInjection(user, dosageAmount);

        return dosageAmount;
    }
}
