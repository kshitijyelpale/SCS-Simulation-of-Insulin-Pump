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
import com.inglpump.web.rest.UserResource;

@Service
public class PumpService {

	private final Logger log = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private User user;
	@Autowired
	private UserService userService;
	@Autowired
	private UserLogService userLogService;
	@Autowired
    private MailService mailService;

	private static HashMap<Integer, Bsl> userJsonMap = new HashMap<Integer, Bsl>();
	private Bsl bsl;

//	private static BSLCalculation BSLCalculation = null;


    //================================== public methods ================================================================

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


    public User getUser(Integer userid) {
        return userService.getUser(userid);
    }

    public Bsl getBSL(Integer userId) {
        try {
            if ((userJsonMap.get(userId) == null)) {
                return initializeBsl(userId);
            }

            bsl = userJsonMap.get(userId);
            double currentBsl = bsl.getCurrentBsl();
            double calculatedBsl = 0;
            String message = null;

            if (currentBsl > 120) {
                log.debug("In range of hyper");
                // case: BSL > 120
                calculatedBsl = calculateBslForHyperGlycemia(currentBsl);
                Double insulinDosageValue = getInsulinDosageValue(currentBsl);
                double insulinAmount = updateInsulinInReservoir(bsl.getInsulinInReservoir(), insulinDosageValue);
                bsl.setInsulinInReservoir(insulinAmount);

                message = "After " + insulinDosageValue + " of insulin injection, BSL decreased from " + currentBsl + " " +
                    " to " + calculatedBsl + "mg/dL";

                bsl.setInjectionStarted(true);

                log.debug("New value of BSL calculated when in hyper range. New current BSL value: " + calculatedBsl);
            } else if (currentBsl < 70) {
                log.debug("In range of hypo");
                // case: BSL < 70
                calculatedBsl = calculateBslForHypoGlycemia(currentBsl);
                Double glucagonDosageValue = getGlucagonDosageValue(currentBsl);
                double glucagonnAmount = updateGlucagonInReservoir(bsl.getGlucagonInReservoir(), glucagonDosageValue);
                bsl.setGlucagonInReservoir(glucagonnAmount);

                message = "After " + glucagonDosageValue + " of glucagon injection, BSL increased from " + currentBsl +
                    " to " + calculatedBsl + "mg/dL";

                bsl.setInjectionStarted(true);
                log.debug("New value of BSL calculated when in hypo range. New current BSL value: " + calculatedBsl);

            } else if (currentBsl < 120 && bsl.isInjectionStarted()) {
                log.debug("In normal range ");
                // case: 70 < BSL < 120
                calculatedBsl = calculateBslforIdeal(currentBsl);

                log.debug("New BSL calculated considering metabolic rate. New current BSL value: " + calculatedBsl);
            }
            else {
                log.debug("Injection is not yet started. Calculating BSL by considering carbohydrates.");
                return getBslForCarbo(userId, bsl.getCarbohydrates(), false);
            }

            if (calculatedBsl != 0) {
                bsl.setPreviousBsl(currentBsl);
                bsl.setCurrentBsl(calculatedBsl);
                int timeCounter = bsl.getTimeCounter();
                bsl.setTimeCounter(++timeCounter);
                bsl.setMessage(message);
                userJsonMap.put(userId, bsl);
            }

            return bsl;
        } catch (Exception e) {
            log.error("Error in calculation of BSL");
            log.error(e.getMessage());

            return null;
        }
    }


    public Bsl getBslForCarbo(Integer userId, double carbs, boolean newActivity) {
        try {
            if ((userJsonMap.get(userId) == null)) {
                bsl = initializeBsl(userId);
            } else {
                bsl = userJsonMap.get(userId);
            }
            if (newActivity) {
                bsl.setCarbohydrates(carbs);
                bsl.setTimeCounter(0);
                bsl.setInjectionStarted(false);

                log.info("New activity - values of carbohydrates and time counter reset");
                log.debug("New activity - values of carbohydrates and time counter reset");
            }

            double currentBsl = bsl.getCurrentBsl();
            double calculatedBsl = bslAfterActivity(currentBsl, carbs, bsl.getTimeCounter());
            if (calculatedBsl != 0) {
                bsl.setPreviousBsl(currentBsl);
                bsl.setCurrentBsl(calculatedBsl);
                int timeCounter = bsl.getTimeCounter();
                bsl.setTimeCounter(++timeCounter);
                userJsonMap.put(userId, bsl);
            }

            return bsl;
        } catch (Exception e) {
            log.error("Error in calculation of BSL when carbohydrates as input");
            log.error(e.getMessage());

            return null;
        }
    }


    public void resetReservoir(int userId, String reservoirType) {
        System.out.println("in resetReservoir");
        if ((userJsonMap.get(userId) == null)) {
            return;
        }
        bsl = userJsonMap.get(userId);

	    if (reservoirType.equalsIgnoreCase("insulin")) {
	        bsl.setInsulinInReservoir(10);
        }
	    else if (reservoirType.equalsIgnoreCase("glucagon")) {
	        bsl.setGlucagonInReservoir(10);
        }
        userJsonMap.put(userId, bsl);
    }


	//================================== private methods ===============================================================


    private Bsl initializeBsl(Integer userId) {
        bsl = new Bsl();
        bsl.setUserId(userId);
        bsl.setPreviousBsl(90);
        bsl.setCurrentBsl(90);
        bsl.setTimeCounter(0);
        bsl.setMessage(null);
        bsl.setInjectionStarted(false);
        bsl.setCarbohydrates(0);
        bsl.setInsulinInReservoir(10);
        bsl.setGlucagonInReservoir(10);
        userJsonMap.put(userId, bsl);

        return bsl;
    }


    private double bslAfterActivity(double currentBsl, double carbs, int tCounter) {
	    double bsl = 0;
        if (carbs != 0) {
            bsl =
                currentBsl
              + (2 * carbs * (Constants.k1 / (Constants.k2 - Constants.k1))
                    * (Math.exp(-Constants.k1 * Constants.TIME_INTERVAL * tCounter)
                        - Math.exp(-Constants.k2 * Constants.TIME_INTERVAL * tCounter)
                    )
                );
        }

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

	private static Double getInsulinDosageValue(double currentBSL) {
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

	private static Double getGlucagonDosageValue(double currentBSL) {
		double calculatedGlucagondose = 0;
		if (currentBSL < Constants.MinimumBloodSugarLevel) {
			calculatedGlucagondose = getChangeInBSForGlucagon(currentBSL) / 3;
			calculatedGlucagondose = Double.parseDouble(new DecimalFormat("##.##").format(calculatedGlucagondose));
		}

		return calculatedGlucagondose;
	}


	private void sendEMail(Integer userId) {
        User user = userService.getUser(userId);
        String conatcts = user.getEmergencyContacts();
	    mailService.sendEmail(conatcts, "","", false, false);
    }


    private double updateInsulinInReservoir(double insulinAmount, Double insulinDosageValue) {
	    if (insulinAmount < 2) {

        }

	    if ((insulinAmount - insulinAmount) > 0) {
            insulinAmount -= insulinDosageValue;

            return insulinAmount;
        }

	    return 0;
    }


    private double updateGlucagonInReservoir(double glucagonnAmount, Double glucagonDosageValue) {
        if (glucagonnAmount < 2) {

        }

        if ((glucagonnAmount - glucagonnAmount) > 0) {
            glucagonnAmount -= glucagonDosageValue;

            return glucagonnAmount;
        }

        return 0;
    }
}
