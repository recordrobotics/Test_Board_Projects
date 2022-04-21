package frc.robot.control;

import edu.wpi.first.wpilibj.XboxController;

public class DoubleControl implements IControlInput {

	private XboxController _gamepad1;
	private XboxController _gamepad2;
	// Toggles for buttons on G2 - inversed when button is pressed
	private boolean _btnX = false, _btnY = false;
	
	public DoubleControl(int port1, int port2) {
		_gamepad1 = new XboxController(port1);
		_gamepad2 = new XboxController(port2);
	}

	@Override
	public double getDriveLong() {
		return _gamepad1.getLeftY();
	}

	@Override
	public double getDriveLat() {
		return _gamepad1.getLeftX();
	}

	@Override
	public double getRotate() {
		return _gamepad2.getLeftX();
	}

	@Override
	public double getClimb() {
		return _gamepad2.getLeftY();
	}

	@Override
	public double getAcqSpin() {
		double forward = (_gamepad1.getLeftTriggerAxis() + _gamepad1.getRightTriggerAxis()) / 2;
		// Uses a button, so map to -1
		double backward = (_gamepad1.getLeftBumper() || _gamepad1.getRightBumper()) ? -1 : 0;

		// Forward takes precedence
		return (forward > 0) ? forward : backward;
	}

	@Override
	public double getAcqTilt() {
		double out = (_gamepad2.getLeftTriggerAxis() + _gamepad2.getRightTriggerAxis()) / 2;
		// Uses a button, so map to -1
		double in = (_gamepad2.getLeftBumper() || _gamepad2.getRightBumper()) ? -1 : 0;

		// Out takes precedence
		return (out > 0) ? out : in;
	}

	@Override
	public FlywheelState getFlywheel() {
		if (_gamepad2.getXButtonPressed()) _btnX = !_btnX;
		// We still want to check Y, to reset it
		if (_gamepad2.getYButtonPressed()) _btnY = !_btnY;
		
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
		return _gamepad1.getAButton();
	}
}
