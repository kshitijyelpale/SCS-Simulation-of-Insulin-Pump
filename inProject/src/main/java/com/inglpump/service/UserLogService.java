package com.inglpump.service;

import com.inglpump.domain.User;
import com.inglpump.domain.UserLog;
import com.inglpump.repository.UserLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLogService {

    @Autowired
    private UserLogRepository patientLogRepository;

    public void logInsulinInjection(User user, double value) {
        String message = value + " amount of Insulin Injected.";

        UserLog userLog = new UserLog();
        userLog.setMessage(message);
        userLog.setUser(user);
        patientLogRepository.save(userLog);
    }


    public void logGlucagonInjection(User user, double value) {
        String message = value + " amount of glucagon Injected.";

        UserLog userLog = new UserLog();
        userLog.setMessage(message);
        userLog.setUser(user);
        patientLogRepository.save(userLog);
    }
}
