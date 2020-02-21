package com.scs.insulinpump.service;

import com.scs.insulinpump.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PumpService {

    Logger logger = LoggerFactory.getLogger(PumpService.class);

    @Autowired
    private Patient patient;
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientLogService patientLogService;

    public double calculateInsulinDosage(double bloodGlucoseLevel) {

        double dosageAmount = 0;

        //TODO: Algorithm to find insulin dosage

        patientLogService.logInsulinInjection(patient, dosageAmount);

        return dosageAmount;
    }


    public double calculateGlucagonDosage(double bloodGlucoseLevel) {

        double dosageAmount = 0;

        //TODO: Algorithm to find glucagon dosage

        patientLogService.logGlucagonInjection(patient, dosageAmount);

        return dosageAmount;
    }
}
