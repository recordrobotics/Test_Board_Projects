package org.recordrobotics.munchkin.commands.manual;

import org.recordrobotics.munchkin.control.IControlInput;
import org.recordrobotics.munchkin.subsystems.Rotator;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualRotator extends CommandBase {
	Rotator _rotator;
	IControlInput _controls;

	public ManualRotator(Rotator rotator, IControlInput control) {
		_rotator = rotator;
		_controls = control;
	}
	
	@Override
	public void execute() {
		double speed = _controls.getRotate();
		if (speed < 0.15) {
			speed = 0;
		}
		_rotator.rotate(speed);
	}
}
