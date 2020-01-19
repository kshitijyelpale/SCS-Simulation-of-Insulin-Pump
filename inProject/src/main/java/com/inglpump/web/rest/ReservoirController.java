package com.inglpump.web.rest;

import com.inglpump.service.ReservoirService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/reservoir")
public class ReservoirController {

    Logger logger = LoggerFactory.getLogger(ReservoirController.class);

    @Autowired
    private ReservoirService reservoirService;

    @GetMapping(path = "/insulin/{patientId}")
    public @ResponseBody
    Double getInsulinLevel(@PathVariable Integer patientId) {
        if(patientId == null) return null;

        return reservoirService.getInsulinLevel(patientId);
    }

    @GetMapping(path = "/glucagon/{patientId}")
    public @ResponseBody
    Double getGlucagonLevel(@PathVariable Integer patientId) {
        if(patientId == null) return null;

        return reservoirService.getGlucagonLevel(patientId);
    }
}
