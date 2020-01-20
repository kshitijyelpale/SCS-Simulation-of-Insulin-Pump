package com.inglpump.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";
    
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";
    
    public static final int InitialBSL = 90;
	public static int MinimumBloodSugarLevel = 70;
	public static int MaximumBloodSugarLevel = 120;
	
	public static double CurrentBSL = 90;
	public static double PreviousBSL = 90;
	public static double TempBSL = 90;
	public static final double TargetBSL = 90;
	
	public static double CarbohydrateIntake = 0;
	public static double insulinToCarbo = 0;
	public static final int ICR = 10; // Insulin  to carbo ratio
	public static final int ISF = 50; // Insulin Sensitivity factor
	
	public static double k1 = 0.0453;	// Glycemic index
	public static double k2 = 0.0224;	// insulin injection rate
    public static int TIME_INTERVAL = 3;

    private Constants() {
    }
}
