package com.scs.insulinpump;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsulinPumpApplication {

	private static Logger logger = LoggerFactory.getLogger(InsulinPumpApplication.class);

	public static void main(String[] args) {
		logger.info("in main");
		SpringApplication.run(InsulinPumpApplication.class, args);
	}

}
