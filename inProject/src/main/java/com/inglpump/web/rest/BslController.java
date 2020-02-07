package com.inglpump.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inglpump.config.Constants;
import com.inglpump.service.PumpService;

@RestController
@RequestMapping("/api")
public class BslController {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private PumpService pumpService;

	@GetMapping("/bsl/{userId:" + Constants.USERID_REGEX + "}")
	public String getBslLevel(@PathVariable int userId) {
		
		log.debug("REST request to get BSL for userId : {}", userId);
		
		// BSL object to JSON string mapping
		String jsonBsl = null;
		ObjectMapper mapper = new ObjectMapper();
        try {
        	jsonBsl = mapper.writeValueAsString(pumpService.getBSL(userId));
//            System.out.println("ResultingJSONstring = " + jsonBsl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
}
		
		return jsonBsl;
	}
	
	@GetMapping("/bslCarbs/{userId:" + Constants.USERID_REGEX + "}/{carbs:" + Constants.CARBS_REGEX + "}")
	public String getBslLevelCarb(@PathVariable int userId, @PathVariable double carbs ) {
		
		log.debug("REST request to get BSL after carbohydrate change for userId : {}", userId);
		log.info("**** carbs ***** " + carbs);
		
		// BSL object to JSON string mapping
		String jsonBsl = null;
		ObjectMapper mapper = new ObjectMapper();
        try {
        	// * update the service method to be called after integration *
//        	jsonBsl = mapper.writeValueAsString(pumpService.getBSL(userId));
        	jsonBsl = mapper.writeValueAsString(pumpService.getBslForCarbo(userId,carbs,true));
//            System.out.println("ResultingJSONstring = " + jsonBsl);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
}
		
		return jsonBsl;
	}


    @PostMapping("/reservoir/refill/{userId}/{reservoirType}")
    public void resetReservoir(@PathVariable int userId, @PathVariable("reservoirType") String reservoirType) {
	    log.debug("Reset reservoir: " + reservoirType + " for User id: " + userId);
        pumpService.resetReservoir(userId, reservoirType);
    }

}
