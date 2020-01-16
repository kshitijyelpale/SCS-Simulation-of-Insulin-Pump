package com.scs.insulinpump.service;

import com.scs.insulinpump.dao.PatientRepository;
import com.scs.insulinpump.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    public Patient patient;
    @Autowired
    private PatientLogService patientLogService;
    @Autowired
    private PatientRepository patientRepository;


    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }


    public Patient getPatientById(Integer patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        return patientOptional.isPresent() ? patientOptional.get() : null;
    }


    public Patient getPatientByUsername(String username) {
        List<Patient> patients = patientRepository.findByUsername(username);

        return patients.isEmpty() ? null : patients.iterator().next();
    }


    public Integer storePatient(Patient patient) {
        logger.info(patient.toString());
        Patient patient1 = patientRepository.save(patient);

        return patient1.getPatientId();
    }


    public Integer validatePatient(String username, String password) {
        logger.info("Username: " + username + " and password: " + password);
        List<Patient> patients = patientRepository.findByUsernameAndPassword(username, password);
        if (!patients.isEmpty()) {
            Patient patient = patients.get(0);

            return patient.getPatientId();
        }

        return 0;
    }
}
