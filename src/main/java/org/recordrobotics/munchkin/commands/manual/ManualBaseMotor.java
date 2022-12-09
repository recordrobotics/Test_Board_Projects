package org.recordrobotics.munchkin.commands.manual;

import org.recordrobotics.munchkin.control.IControlInput;
import org.recordrobotics.munchkin.subsystems.BaseMotor;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ManualBaseMotor extends CommandBase {
	private BaseMotor _baseMotor;
	private IControlInput _controls;

	public ManualBaseMotor(BaseMotor baseMotor, IControlInput controlInput) {
		if (baseMotor == null) {
			throw new IllegalArgumentException("baseMotor is null");
		}
		if (controlInput == null) {
			throw new IllegalArgumentException("Control is null");
		}

		_baseMotor = baseMotor;
		_controls = controlInput;
		addRequirements(_baseMotor);
	}

	@Override
	public void execute() {
		//fill with actions to run when robot is active
		if(_controls.getDriveLong() > 0){
			_baseMotor.run(_controls.getDriveLong());
		}
	}

	@Override
	public void end(boolean interrupted) {
		//fill with actions  to run when command ends
	}
}
