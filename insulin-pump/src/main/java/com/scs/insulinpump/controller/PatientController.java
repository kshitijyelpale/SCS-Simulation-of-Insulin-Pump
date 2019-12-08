package com.scs.insulinpump.controller;

import com.scs.insulinpump.dao.PatientRepository;
import com.scs.insulinpump.domain.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    private static Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping(path = "/create")
    public @ResponseBody String addPatient (@RequestBody Patient patient) {
        logger.info(patient.toString());
        patientRepository.save(patient);

        return "Saved.";
    }


    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }


    @GetMapping(path = "/ID/{patientId}")
    public @ResponseBody Patient getPatient(@PathVariable Integer patientId) {
        logger.info("Patient Id: " + patientId);
        if(patientId == null) return null;

        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        return patientOptional.isPresent() ? patientOptional.get() : null;
    }


    /*@GetMapping(path = "/username/{username}")
    public @ResponseBody Patient getPatientByName(@PathVariable String username) {
        logger.info("Patient's Username: " + username);
        if(username == null) return null;

        Optional<Patient> patientOptional = patientRepository.findByName(username);
        return patientOptional.isPresent() ? patientOptional.get() : null;
    }*/


}
