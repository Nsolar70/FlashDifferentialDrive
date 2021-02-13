// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class EncoderConstants{

        /**
         * Which PID slot to pull gains from. Starting 2018, you can choose from
         * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
         * configuration.
         */
	    public static final int kSlotIdx = 0;

        /*
        * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
        * now we just want the primary one.
        */
	    public static final int kPIDLoopIdx = 0;

        /*
        * set to zero to skip waiting for confirmation, set to nonzero to wait and
        * report to DS if action fails.
        */
	    public static final int kTimeoutMs = 10;

	    /* Current threshold to trigger current limit */
	    static final int kPeakCurrentAmps = 42;
    
	    /* Duration after current exceed Peak Current to trigger current limit */
	    static final int kPeakTimeMs = 10;
 
	    /* Current to mantain once current limit has been triggered */
	    static final int kContinCurrentAmps = 40;
 
        /**
         * Timeout value generally used in parameter configs
        * Non-zero to block the config until success, zero to skip checking 
        */
        static final int  CurrentLimitingkTimeoutMs= 30;
    }
}
