package com.scs.insulinpump.service;

import com.scs.insulinpump.dao.PatientLogRepository;
import com.scs.insulinpump.domain.Patient;
import com.scs.insulinpump.domain.PatientLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientLogService {

    @Autowired
    private PatientLogRepository patientLogRepository;

    public void logInsulinInjection(Patient patient, double value) {
        String message = value + " amount of Insulin Injected.";

        PatientLog patientLog = new PatientLog();
        patientLog.setMessage(message);
        patientLog.setPatient(patient);
        patientLogRepository.save(patientLog);
    }


    public void logGlucagonInjection(Patient patient, double value) {
        String message = value + " amount of glucagon Injected.";

        PatientLog patientLog = new PatientLog();
        patientLog.setMessage(message);
        patientLog.setPatient(patient);
        patientLogRepository.save(patientLog);
    }
}
