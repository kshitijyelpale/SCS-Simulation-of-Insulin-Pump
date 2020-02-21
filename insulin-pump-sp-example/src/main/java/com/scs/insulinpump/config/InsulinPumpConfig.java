package com.scs.insulinpump.config;

import com.scs.insulinpump.domain.Patient;
import com.scs.insulinpump.service.PatientLogService;
import com.scs.insulinpump.service.PatientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class InsulinPumpConfig {

    @Bean
    public Patient patient() {
        return new Patient();
    }

    @Bean
    public PatientService patientService() { return new PatientService(); }

    @Bean
    public PatientLogService patientLogService() { return new PatientLogService(); }
}
