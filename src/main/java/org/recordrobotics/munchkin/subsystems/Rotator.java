package org.recordrobotics.munchkin.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.recordrobotics.munchkin.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rotator extends SubsystemBase {
	private CANSparkMax leftMotor = new CANSparkMax(RobotMap.Rotator.LEFT_MOTOR_PORT, MotorType.kBrushless);
    private CANSparkMax rightMotor = new CANSparkMax(RobotMap.Rotator.RIGHT_MOTOR_PORT, MotorType.kBrushless);
    private RelativeEncoder leftEncoder = leftMotor.getEncoder();
    private RelativeEncoder rightEncoder = rightMotor.getEncoder();
	private DigitalInput forwardLimit = new DigitalInput(RobotMap.Rotator.FWD_LIMIT_PORT);
    private DigitalInput backwardLimit = new DigitalInput(RobotMap.Rotator.BWD_LIMIT_PORT);

	/**
	 * stops motors
	 */
	public void stop() {
		leftMotor.set(0);
		rightMotor.set(0);
	}

	/**
	 * gets position from encoders
	 * @return position
	 */
	public double getPosition() {
		return (leftEncoder.getPosition() - rightEncoder.getPosition()) / 2;
	}

	/**
	 * sets encoder values to 0
	 */
	public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }

	/**
     * rotate lift forwards and backwards
     * @param speed speed
     */
    public void rotate(double speed) {
        if ((speed > 0 && forwardLimit.get()) || (speed < 0 && backwardLimit.get())) {
            leftMotor.set(Subsystems.limitSpeed(speed));
            rightMotor.set(-Subsystems.limitSpeed(speed));
        } else {
            stop();
        }
    }
}
