package org.recordrobotics.munchkin;

/**
 * Hardware ports for computer and robot
 */
public class RobotMap {

	/**
	 * Acquisition Motor Ports
	 */
	public class Acquisition {
		// DIO
		public static final int LIMIT_SWITCH = 2;

		// CAN
		public static final int SPIN_MOTOR_PORT = 7;
		public static final int BALL_CHANNEL_MOTOR_PORT = 8;
		public static final int TILT_MOTOR_PORT = 9;
	}

	/**
	 * Climber ports (CAN)
	 */
	public class Climbers {
		public static final int LEFT_MOTOR_PORT = 10;
		public static final int RIGHT_MOTOR_PORT = 6;
	}

	/**
	 * Control ports (PC USB)
	 */
	public class Control {
		// LegacyControl
		public static final int LEGACY_GAMEPAD = 0;

		// DoubleControl
		public static final int DOUBLE_GAMEPAD_1 = 0;
		public static final int DOUBLE_GAMEPAD_2 = 1;
	}

	/**
	 * Climber ports
	 */
	public class Flywheel {
		// DIO
		public static final int BALL_DETECTOR_PORT = 3;

		// PWN
		public static final int RIGHT_SERVO_PORT = 0;
		public static final int LEFT_SERVO_PORT = 1;

		// CAN
		public static final int MOTOR_PORT = 11;
	}
}
