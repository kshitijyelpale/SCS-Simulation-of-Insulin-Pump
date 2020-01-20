package com.inglpump.domain;

public class Bsl {

	private int userId;
	private int timeCounter;
	private double previousBsl;
	private double currentBsl;
	private String message;

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

	@Override
	public String toString() {
		return "Bsl [userId=" + userId + ", timeCounter=" + timeCounter + ", previousBsl=" + previousBsl
				+ ", currentBsl=" + currentBsl + ", message=" + message + "]";
	}

}
