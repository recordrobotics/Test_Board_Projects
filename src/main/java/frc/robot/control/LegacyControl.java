package frc.robot.control;

import edu.wpi.first.wpilibj.XboxController;

public class LegacyControl implements IControlInput {

	private XboxController _gamepad;
	// Toggles for buttons - inversed when button is pressed
	private boolean _btnX = false, _btnY = false;
	
	public LegacyControl(int port) {
		_gamepad = new XboxController(port);
	}

	@Override
	public double getDriveLong() {
		return _gamepad.getLeftY();
	}

	@Override
	public double getDriveLat() {
		return _gamepad.getLeftX();
	}

	@Override
	public double getRotate() {
		return _gamepad.getRightX();
	}

	@Override
	public double getClimb() {
		return _gamepad.getRightY();
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
		if (_gamepad.getXButtonPressed()) _btnX = !_btnX;
		// We still want to check Y, to reset it
		if (_gamepad.getYButtonPressed()) _btnY = !_btnY;
		
		// If X not pressed, clear Y
		if (!_btnX) {
			_btnY = false;
			return FlywheelState.OFF;
		}

		// Otherwise Y determines the state
		return (_btnY) ? FlywheelState.HIGH : FlywheelState.LOW;
	}

	@Override
	public boolean getServos() {
		return _gamepad.getAButton();
	}
	
}
