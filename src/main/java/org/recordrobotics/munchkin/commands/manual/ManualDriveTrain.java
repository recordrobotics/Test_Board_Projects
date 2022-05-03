// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin.commands.manual;

import org.recordrobotics.munchkin.control.IControlInput;
import org.recordrobotics.munchkin.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
@SuppressWarnings({"PMD.UncommentedEmptyMethodBody"})
public class ManualDriveTrain extends CommandBase {
	private DriveTrain _driveTrain;
	private IControlInput _controls;
	@SuppressWarnings({"unused", "PMD.SingularField"})


	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public ManualDriveTrain(DriveTrain driveTrain, IControlInput controls) {
		_driveTrain = driveTrain;
		_controls = controls;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(driveTrain);
	}

	private void RunLinearDrive(){
		_driveTrain.getDifferentialDrive().arcadeDrive(_controls.getDriveLong() * 0.45, _controls.getDriveLat() * 0.45);
	}
	private void stopDriveMotors(){
		_driveTrain.stopMotors();
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		stopDriveMotors();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		RunLinearDrive();
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
