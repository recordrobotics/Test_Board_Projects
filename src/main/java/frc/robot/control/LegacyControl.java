package frc.robot.control;

import edu.wpi.first.wpilibj.XboxController;

public class LegacyControl implements IControlInput {

	private XboxController _gamepad;
	
	public LegacyControl(int port) {
		_gamepad = new XboxController(port);
	}

	@Override
	public double getDriveLong() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDriveLat() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRotate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getClimb() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAcqSpin() {
		double forward = _gamepad.getLeftTriggerAxis();
		// Uses a button, so map to -1
		double backward = _gamepad.getLeftBumper() ? -1 : 0;

		// Forward takes precedence
		return (forward > 0) ? forward : backward;
	}

	@Override
	public double getAcqTilt() {
		double out = _gamepad.getRightTriggerAxis();
		// Uses a button, so map to -1
		double in = _gamepad.getRightBumper() ? -1 : 0;

		// Out takes precedence
		return (out > 0) ? out : in;
	}

	@Override
	public FlywheelState getFlywheel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getServos() {
		return _gamepad.getAButton();
	}
	
}
