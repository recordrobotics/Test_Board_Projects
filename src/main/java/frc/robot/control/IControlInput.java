package frc.robot.control;

/**
 * Specifies all control inputs needed for the robot
 */
public interface IControlInput {

	/**
	 * Flywheel has three states:
	 * 	OFF - disable
	 * 	LOW - low target shot
	 * 	HIGH - high target shot
	 */
	public enum FlywheelState {
		OFF,
		LOW,
		HIGH
	}
	
	/**
	 * Logitudinal drive input (forward & backward) value
	 * @return [-1, 0) - backward; (0, 1] - forward
	 */
	public double getDriveLong();

	/**
	 * Latitudinal drive input (left & right) value
	 * @return [-1, 0) - left; (0, 1] - right
	 */
	public double getDriveLat();

	/**
	 * Rotator input value
	 * @return [-1, 0) - backward; (0, 1] - forward
	 */
	public double getRotate();

	/**
	 * Climber input value
	 * @return [-1, 0) - down; (0, 1] - up
	 */
	public double getClimb();

	/**
	 * Acquisition spin input value
	 * @return [-1, 0) - backward; (0, 1] - forward
	 */
	public double getAcqSpin();

	/**
	 * Acquisition tilt input value
	 * @return [-1, 0) - in; (0, 1] - out
	 */
	public double getAcqTilt();

	/**
	 * Flywheel requested state
	 * @return OFF - off, LOW - low speed, HIGH - high speed
	 */
	public FlywheelState getFlywheel();

	/**
	 * Servo input
	 * @return false - servos down; true - servos up
	 */
	public boolean getServos();

}
