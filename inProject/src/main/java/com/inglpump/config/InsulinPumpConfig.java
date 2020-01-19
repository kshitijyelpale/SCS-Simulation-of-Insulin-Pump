package com.inglpump.config;

import com.inglpump.domain.User;
import com.inglpump.service.UserLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class InsulinPumpConfig {

    @Bean
    public User patient() {
        return new User();
    }

    /*@Bean
    public UserService userService() { return new UserService(); }*/

    @Bean
    public UserLogService patientLogService() { return new UserLogService(); }
}
