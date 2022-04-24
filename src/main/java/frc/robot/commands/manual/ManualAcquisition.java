package frc.robot.commands.manual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.control.IControlInput;
import frc.robot.subsystems.Acquisition;

public class ManualAcquisition extends CommandBase {
	private Acquisition _acquisition;
	private IControlInput _controls;
	// FIXME: set fields to the proper value
	private static final int SPIN_SPEED = 0;
	private static final int TILT_SPEED = 0;

	public ManualAcquisition(Acquisition _acq, IControlInput _controlInput) {
		_acquisition = _acq;
		_controls = _controlInput;
		addRequirements(_acquisition);
	}

	@Override
	public void execute() {
		// spin controls (if = intake balls, else if = expel balls,  else = stop motor)
		if (_controls.getAcqSpin() > 0) {
			_acquisition.spinAcquisition(SPIN_SPEED);
		} else if (_controls.getAcqSpin() < 0) {
			_acquisition.spinAcquisition(-SPIN_SPEED);
		} else {
			_acquisition.spinAcquisition(0);
		}

		// tilt controls (if = tilt down, else if = tilt up,  else = stop motor)
		if (_controls.getAcqTilt() > 0) {
			_acquisition.tiltAcquisition(TILT_SPEED);
		} else if (_controls.getAcqTilt() < 0) {
			_acquisition.tiltAcquisition(-TILT_SPEED);
		} else {
			_acquisition.tiltAcquisition(0);
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
