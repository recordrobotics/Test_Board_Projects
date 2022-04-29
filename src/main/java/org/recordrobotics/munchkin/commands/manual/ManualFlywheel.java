package org.recordrobotics.munchkin.commands.manual;

import org.recordrobotics.munchkin.subsystems.Flywheel;
import org.recordrobotics.munchkin.control.IControlInput;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualFlywheel extends CommandBase {
	private Flywheel _flywheel;
	private IControlInput _controls;
	// TODO: implement dashboard once we make it (it was there in the old code)
	private static final double HIGH_SPEED = 0.35;
	private static final double LOW_SPEED = 0.22;
	private static final double IDLE_SPEED = 0;
	// used to reset servos
	private boolean _servosUp;

	public ManualFlywheel(Flywheel flywheel, IControlInput controlInput) {
		_flywheel = flywheel;
		_controls = controlInput;
		addRequirements(_flywheel);
		_flywheel.resetServos();
	}

	@Override
	public void execute() {
		switch (_controls.getFlywheel()) {
			case OFF:
				_flywheel.spin(IDLE_SPEED);
				_flywheel.resetServos();
				return;
			case LOW:
				_flywheel.spin(LOW_SPEED);
				break;
			case HIGH:
				_flywheel.spin(HIGH_SPEED);
				break;
		}
		if (_controls.getServos()) {
			_flywheel.shootServos();
			_servosUp = true;
		} else if (_servosUp) {
			_flywheel.resetServos();
			_servosUp = false;
		}
	}
}
