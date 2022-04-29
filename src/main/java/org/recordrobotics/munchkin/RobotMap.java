package org.recordrobotics.munchkin;

/**
 * Hardware ports for computer and robot
 */
public class RobotMap {

	/**
	 * Acquisition Motor ports (CAN)
	 */
	public class Acquisition {
		// limit switch is PWN port
		public static final int LIMIT_SWITCH = 2;
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
		// Gamepad port for LegacyControl scheme
		public static final int LEGACY_GAMEPAD = 0;
		// Gamepad ports for DoubleControl scheme
		public static final int DOUBLE_GAMEPAD_1 = 0;
		public static final int DOUBLE_GAMEPAD_2 = 1;
	}

	/**
	 * Climber ports (CAN)
	 */
	public class Flywheel {
		// limit switch is PWN port
		public static final int BALL_DETECTOR_PORT = 3;
		public static final int MOTOR_PORT = 11;
		public static final int LEFT_SERVO_PORT = 1;
		public static final int RIGHT_SERVO_PORT = 0;
	}
}
