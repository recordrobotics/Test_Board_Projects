package org.recordrobotics.munchkin.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.recordrobotics.munchkin.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rotator extends SubsystemBase {
	private CANSparkMax _leftMotor = new CANSparkMax(RobotMap.Rotator.LEFT_MOTOR_PORT, MotorType.kBrushless);
	private CANSparkMax _rightMotor = new CANSparkMax(RobotMap.Rotator.RIGHT_MOTOR_PORT, MotorType.kBrushless);
	private RelativeEncoder _leftEncoder = _leftMotor.getEncoder();
	private RelativeEncoder _rightEncoder = _rightMotor.getEncoder();
	private DigitalInput _forwardLimit = new DigitalInput(RobotMap.Rotator.FWD_LIMIT_PORT);
	private DigitalInput _backwardLimit = new DigitalInput(RobotMap.Rotator.BWD_LIMIT_PORT);

	public Rotator() {
		_leftMotor.set(0);
		_rightMotor.set(0);
	}

	/**
	 * gets position from encoders
	 * @return position
	 */
	public double getPosition() {
		// right encoder will be incrementing opposite to left encoder. because left motor spins in the same direction as the input, right encoder is subtracted.
		return (_leftEncoder.getPosition() - _rightEncoder.getPosition()) / 2;
	}

	/**
	 * sets encoder values to 0
	 */
	public void resetEncoders() {
		_leftEncoder.setPosition(0);
		_rightEncoder.setPosition(0);
	}

	/**
	 * rotate lift forwards and backwards
	 * @param speed speed
	 */
	public void rotate(double speed) {
		if (speed > 0 && _forwardLimit.get() || speed < 0 && _backwardLimit.get()) {
			_leftMotor.set(Subsystems.limitSpeed(speed));
			_rightMotor.set(Subsystems.limitSpeed(-speed));
		} else {
			_leftMotor.set(0);
			_rightMotor.set(0);
		}
	}
}
