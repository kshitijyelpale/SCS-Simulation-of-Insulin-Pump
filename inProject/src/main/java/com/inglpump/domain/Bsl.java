package com.inglpump.domain;

public class Bsl {

	private int userId;
	private int timeCounter;
	private double previousBsl;
	private double currentBsl;
	private String message;
	private boolean injectionStarted;
	private double carbohydrates;

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

	@Override
	public String toString() {
		return "Bsl [userId=" + userId + ", timeCounter=" + timeCounter + ", previousBsl=" + previousBsl
				+ ", currentBsl=" + currentBsl + ", message=" + message + "]";
	}

}
