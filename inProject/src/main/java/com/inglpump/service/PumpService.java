package com.inglpump.service;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inglpump.config.Constants;
import com.inglpump.domain.Bsl;
import com.inglpump.domain.User;

@Service
public class PumpService {

	Logger logger = LoggerFactory.getLogger(PumpService.class);

	@Autowired
	private User user;
	@Autowired
	private UserService userService;
	@Autowired
	private UserLogService userLogService;

	private static HashMap<Integer, Bsl> userJsonMap = new HashMap<Integer, Bsl>();
	private Bsl bsl;

//	private static BSLCalculation BSLCalculation = null;

	public double calculateInsulinDosage(double bloodGlucoseLevel) {

		double dosageAmount = 0;

		// TODO: Algorithm to find insulin dosage

		userLogService.logInsulinInjection(user, dosageAmount);

		return dosageAmount;
	}

	public double calculateGlucagonDosage(double bloodGlucoseLevel) {

		double dosageAmount = 0;

		// TODO: Algorithm to find glucagon dosage

		userLogService.logGlucagonInjection(user, dosageAmount);

		return dosageAmount;
	}

	public Bsl getBSL(Integer userId) {

		if ((userJsonMap.get(userId) == null)) {

			bsl = new Bsl();
			bsl.setUserId(userId);
			bsl.setPreviousBsl(90);
			bsl.setCurrentBsl(90);
			bsl.setTimeCounter(1);

			userJsonMap.put(userId, bsl);

			return bsl;
		}

		bsl = userJsonMap.get(userId);
		double currentBsl = bsl.getCurrentBsl();
		double calculatedBsl;
		String message = null;

		if (currentBsl > 120) {

			// case: BSL > 120
			calculatedBsl = calculateBslForHyperGlycemia(currentBsl);
			Double insulinDosageValue = getInsulinDosageValue(currentBsl);
			message = "After " + insulinDosageValue + " of insulin injection, BSL decreased from " + currentBsl + " to " + calculatedBsl + "mg/dl";

		} else if (currentBsl < 70) {

			// case: BSL < 70
			calculatedBsl = calculateBslForHypoGlycemia(currentBsl);
			Double glucagonDosageValue = getGlucagonDosageValue(currentBsl);
			message = "After " + glucagonDosageValue + " of glucagon injection, BSL increased from " + currentBsl + " to " + calculatedBsl + "mg/dl";

		} else {

			// case: 70 < BSL < 120
			calculatedBsl = calculateBslforIdeal(currentBsl);

		}
		bsl.setPreviousBsl(currentBsl);
		bsl.setCurrentBsl(calculatedBsl);
		int timeCounter = bsl.getTimeCounter();
		bsl.setTimeCounter(++timeCounter);
		bsl.setMessage(message);
		userJsonMap.put(userId, bsl);

		return bsl;
	}

	private double calculateBslforIdeal(double currentBsl) {

		// Logic for ideal case

		currentBsl -= (3 * (Constants.k1 / (Constants.k2 - Constants.k1))
				* (Math.exp(-Constants.k1 * 5) - Math.exp(-Constants.k2 * 5)));

		return currentBsl;

	}

	private double calculateBslForHypoGlycemia(double currentBsl) {

		// Logic for HypoGlycemia
		Double glucagonDosageValue = getGlucagonDosageValue(currentBsl);
//		if (glucagonDosageValue < 0)
//			return currentBsl;

		currentBsl += glucagonDosageValue * 3;

		return currentBsl;
	}

	private double calculateBslForHyperGlycemia(double currentBsl) {

		// Logic for HyperGlycemia
		Double insulinDosageValue = getInsulinDosageValue(currentBsl);
		if (insulinDosageValue > 0) {
			currentBsl -= (Constants.ISF * insulinDosageValue);
		}

		return currentBsl;
	}

	public static Double getInsulinDosageValue(double currentBSL) {
		double calculatedinsulindose = 0;
		if (currentBSL >= Constants.MaximumBloodSugarLevel) {
			double insulinCorrectionFactor = (getChangeInBSForInsulin(currentBSL)) / Constants.ISF;

			calculatedinsulindose = Double.parseDouble(new DecimalFormat("##.##").format(insulinCorrectionFactor));
		}

		return calculatedinsulindose;
	}

	private static double getChangeInBSForInsulin(double currentBSL) {
		if (currentBSL <= 130) {
			return 5;
		} else if (currentBSL <= 150) {
			return 10;
		} else if (currentBSL <= 180) {
			return 15;
		} else if (currentBSL <= 220) {
			return 20;
		}

		return 25;
	}

	private static double getChangeInBSForGlucagon(double currentBSL) {
		if (currentBSL >= 65 && currentBSL < 80) {
			return 3;
		}

		return 6;
	}

	public static Double getGlucagonDosageValue(double currentBSL) {
		double calculatedGlucagondose = 0;
		if (currentBSL < Constants.MinimumBloodSugarLevel) {
			calculatedGlucagondose = getChangeInBSForGlucagon(currentBSL) / 3;
			calculatedGlucagondose = Double.parseDouble(new DecimalFormat("##.##").format(calculatedGlucagondose));
		}

		return calculatedGlucagondose;
	}
}
