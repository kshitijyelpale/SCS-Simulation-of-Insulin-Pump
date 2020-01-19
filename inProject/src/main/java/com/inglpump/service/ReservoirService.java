package com.inglpump.service;

import com.inglpump.domain.Reservoir;
import com.inglpump.repository.ReservoirRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservoirService {

    Logger logger = LoggerFactory.getLogger(ReservoirService.class);

    @Autowired
    private ReservoirRepository reservoirRepository;


    public double getInsulinLevel(Integer patientID) {
        List<Reservoir> reservoir = reservoirRepository.findByPatientIdAndType(patientID, Reservoir.RESERVOIR_TYPE_INSULIN);
        logger.info(String.valueOf(reservoir));

        return 0.0;
    }


    public double getGlucagonLevel(Integer patientID) {
        List<Reservoir> reservoir = reservoirRepository.findByPatientIdAndType(patientID, Reservoir.RESERVOIR_TYPE_GLUCAGON);

        return 0.0;
    }
}
