package com.inglpump.domain;

public class Bsl {

	private int userId;
	private int timeCounter;
	private double previousBsl;
	private double currentBsl;
	private String message;
	private boolean injectionStarted;
	private double carbohydrates;
	private double insulinInReservoir;
	private double glucagonInReservoir;
	private boolean isEmailSentForInsulin;
	private boolean isEmailSentForGlucagon;
	private int alertCounterForHyperLevel;
	private int alertCounterForHypoLevel;

    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTimeCounter() {
		return timeCounter;
	}

	public void setTimeCounter(int timeCounter) {
		this.timeCounter = timeCounter;
	}

	public double getPreviousBsl() {
		return previousBsl;
	}

	public void setPreviousBsl(double previousBsl) {
		this.previousBsl = previousBsl;
	}

	public double getCurrentBsl() {
		return currentBsl;
	}

	public void setCurrentBsl(double currentBsl) {
		this.currentBsl = currentBsl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public boolean isInjectionStarted() {
        return injectionStarted;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setInjectionStarted(boolean injectionStarted) {
        this.injectionStarted = injectionStarted;
    }

    public double getInsulinInReservoir() {
        return insulinInReservoir;
    }

    public void setInsulinInReservoir(double insulinInReservoir) {
        this.insulinInReservoir = insulinInReservoir;
    }

    public double getGlucagonInReservoir() {
        return glucagonInReservoir;
    }

    public void setGlucagonInReservoir(double glucagonInReservoir) {
        this.glucagonInReservoir = glucagonInReservoir;
    }

    public boolean isEmailSentForInsulin() {
        return isEmailSentForInsulin;
    }

    public boolean isEmailSentForGlucagon() {
        return isEmailSentForGlucagon;
    }

    public void setEmailSentForInsulin(boolean emailSentForInsulin) {
        isEmailSentForInsulin = emailSentForInsulin;
    }

    public void setEmailSentForGlucagon(boolean emailSentForGlucagon) {
        isEmailSentForGlucagon = emailSentForGlucagon;
    }

    public void incrementHyperAlertCounter() {
        this.alertCounterForHyperLevel++;
    }

    public void resetHyperAlertCounter() {
        this.alertCounterForHyperLevel = 0;
    }

    public void incrementHypoAlertCounter() {
        this.alertCounterForHypoLevel++;
    }

    public void resetHypoAlertCounter() {
        this.alertCounterForHypoLevel = 0;
    }

    public int getAlertCounterForHyperLevel() {
        return alertCounterForHyperLevel;
    }

    public int getAlertCounterForHypoLevel() {
        return alertCounterForHypoLevel;
    }

    @Override
    public String toString() {
        return "Bsl{" + "userId=" + userId + ", timeCounter=" + timeCounter + ", previousBsl=" + previousBsl + ", " +
            "currentBsl=" + currentBsl + ", message='" + message + '\'' + ", injectionStarted=" + injectionStarted +
            ", carbohydrates=" + carbohydrates + ", insulinInReservoir=" + insulinInReservoir + ", " +
            "glucagonInReservoir=" + glucagonInReservoir + ", isEmailSentForInsulin=" + isEmailSentForInsulin + ", " +
            "isEmailSentForGlucagon=" + isEmailSentForGlucagon + '}';
    }
}
