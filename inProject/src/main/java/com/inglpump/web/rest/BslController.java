package com.inglpump.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inglpump.config.Constants;
import com.inglpump.service.PumpService;

@RestController
@RequestMapping("/api")
public class BslController {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);
	
	private final PumpService pumpService = new PumpService();

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
}
