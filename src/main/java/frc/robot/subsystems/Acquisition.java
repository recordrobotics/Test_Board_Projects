package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Acquisition extends SubsystemBase {
	// motor and limit switch variables
	private CANSparkMax _spinMotor = new CANSparkMax(RobotMap.acqSpinMotorPort, MotorType.kBrushless);
    private CANSparkMax _tiltMotor = new CANSparkMax(RobotMap.acqTiltMotorPort, MotorType.kBrushed);
	private CANSparkMax _ballChannelMotor = new CANSparkMax(RobotMap.ballChannelMotorPort, MotorType.kBrushless);
	private DigitalInput _tiltLimitSwitch = new DigitalInput(RobotMap.acqTiltiLimitSwitch);
	// variable stating the current tilt state of the acquisition - only has 'up' and 'down' values
	private String _tiltState = "up";

	public Acquisition() {
		_spinMotor.set(0);
		_tiltMotor.set(0);
		_ballChannelMotor.set(0);
	}

	/**
	 * returns the state of the acquisition tilt
	 * @return true if up, false if down
	 */
	public boolean getTiltState() {
		return _tiltState.equals("up");
	}

	/**
	 * sets a new value for _tiltState
	 * @param newState the value to set _tiltState to
	 * *PRECONDITION* newState only holds the value 'up' or 'down'
	 */
	public void setTiltState(String newState) {
		_tiltState = newState;
	}

	/**
	 * spins spin motor at 'speed' speed and ball channel motor at '-5/3 * speed' speed
	 * @param speed base value for speed calculations, always >0
	 */
	public void spinAcquisition(double speed) {
		_spinMotor.set(speed);
		_ballChannelMotor.set(-speed * 5 / 3);
	}

	/**
	 * spins tilt motor at 'speed' speed if it's raising it or lowering it and hasn't hit the limit switch
	 * @param speed speed of motor
	 */
	public void tiltAcquisition(double speed) {
		if ((speed < 0 && _tiltLimitSwitch.get()) || speed > 0 ) {
            _tiltMotor.set(speed);
        } else {
            _tiltMotor.set(0);
        }
	}

	/**
	 * returns if the limit switch has been activated (and the acquisition can't tilt farther down)
	 * @return the value of the limit switch (true is pressed, false is not pressed)
	 */
	public boolean getTiltLimit() {
		return _tiltLimitSwitch.get();
	}

	/**
	 * returns if the acquisition is spinning (this is used for dashboard)
	 * @return acquisition spinning (true = spinning, false = not spinning)
	 */
	public boolean getSpinState() {
		return _spinMotor.get() > 0;
	}
}
