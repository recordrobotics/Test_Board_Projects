package org.recordrobotics.munchkin.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import org.recordrobotics.munchkin.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
	// constants related to servo positions
	private static final double RIGHT_SERVO_SHOOT = 0.0;
	private static final double LEFT_SERVO_SHOOT = 0.33;
	private static final double RIGHT_SERVO_RESET = 0.33;
	private static final double LEFT_SERVO_RESET = 0.0;

	// motor, servo, and limit switch variables
	private WPI_TalonFX _motor = new WPI_TalonFX(RobotMap.Flywheel.MOTOR_PORT);
	private Servo _leftServo = new Servo(RobotMap.Flywheel.LEFT_SERVO_PORT);
	private Servo _rightServo = new Servo(RobotMap.Flywheel.RIGHT_SERVO_PORT);
	private DigitalInput _ballDetector = new DigitalInput(RobotMap.Flywheel.BALL_DETECTOR_PORT);

	/**
	 * resets motor and servos upon creation
	 */
	public Flywheel() {
		_motor.enableVoltageCompensation(true);
		_motor.setVoltage(11.5);
		spin(0);
	}

	/**
	 * spins motor at speed 'speed'
	 * @param speed speed to spin motor
	 */
	public void spin(double speed) {
		_motor.set(ControlMode.PercentOutput, speed);
	}

	/**
	 * sets the servos to shooting position (launching the ball into the flywheel in the process)
	 */
	public void shootServos() {
		_leftServo.set(LEFT_SERVO_SHOOT);
		_rightServo.set(RIGHT_SERVO_SHOOT);
	}

	/**
	 * puts the servos back into idle position
	 */
	public void resetServos() {
		_leftServo.set(LEFT_SERVO_RESET);
		_rightServo.set(RIGHT_SERVO_RESET);
	}

	/**
	 * not sure where this is used, but it was in the old code
	 * @return flywheel voltage output
	 */
	public double getVoltage() {
		return _motor.getMotorOutputVoltage();
	}

	/**
	 * returns ball detector state
	 * @return ball detector switch is active (true = active, false = not active)
	 */
	public boolean getBallDetector() {
		return !_ballDetector.get();
	}
}
