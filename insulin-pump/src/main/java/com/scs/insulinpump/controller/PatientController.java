package com.scs.insulinpump.controller;

import com.scs.insulinpump.domain.Patient;
import com.scs.insulinpump.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @PostMapping(path = "/create")
    public @ResponseBody Integer addPatient (@RequestBody Patient patient) {
        logger.info(patient.toString());
        Integer storedPatientID = patientService.storePatient(patient);

        return storedPatientID;
    }


    @PostMapping(value = "/validate")
    public @ResponseBody Integer validatePatient(@RequestBody Patient patient) {
        logger.info(patient.toString());
        if (patient.getUsername().isEmpty() || patient.getPassword().isEmpty()) return 0;

        return patientService.validatePatient(patient.getUsername(), patient.getPassword());
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }


    @GetMapping(path = "/ID/{patientId}")
    public @ResponseBody Patient getPatient(@PathVariable Integer patientId) {
        logger.info("Patient Id: " + patientId);
        if(patientId == null) return null;

        return patientService.getPatientById(patientId);
    }


    @GetMapping(path = "/username/{username}")
    public @ResponseBody Patient getPatientByUsername(@PathVariable String username) {
        logger.info("Patient's Username: " + username);
        if(username == null) return null;

        return patientService.getPatientByUsername(username);
    }
}
