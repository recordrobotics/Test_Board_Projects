// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin;

import org.recordrobotics.munchkin.commands.ExampleCommand;
import org.recordrobotics.munchkin.control.*;
import org.recordrobotics.munchkin.subsystems.*;
import org.recordrobotics.munchkin.commands.manual.*;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * Contains subsystems, control and command scheduling
 */
public class RobotContainer {
	// Subsystems
	private ExampleSubsystem _exampleSubsystem = new ExampleSubsystem();
	// warnings to suppress
	private static final String WARNING_1 = "unused";
	private static final String WARNING_2 = "PMD.SingularField";

	// Acquisition
	@SuppressWarnings({WARNING_1, WARNING_2})
	private Acquisition _acquisition;

	// Climbers
	@SuppressWarnings({WARNING_1, WARNING_2})
	private Climbers _climbers;

	// Control scheme
	@SuppressWarnings({WARNING_1, WARNING_2})
	private IControlInput _controlInput;

	// Flywheel
	@SuppressWarnings({WARNING_1, WARNING_2})
	private Flywheel _flywheel;

	// Autonomous command
	private ExampleCommand _autoCommand = new ExampleCommand(_exampleSubsystem);

	public RobotContainer() {
		_controlInput = new LegacyControl(RobotMap.Control.LEGACY_GAMEPAD);
		//_controlInput = new DoubleControl(RobotMap.Control.DOUBLE_GAMEPAD_1, RobotMap.Control.DOUBLE_GAMEPAD_2);
		_acquisition = new Acquisition();
		_acquisition.setDefaultCommand(new ManualAcquisition(_acquisition, _controlInput));
		_climbers = new Climbers();
		_climbers.setDefaultCommand(new ManualClimbers(_climbers, _controlInput));
		_flywheel = new Flywheel();
		_flywheel.setDefaultCommand(new ManualFlywheel(_flywheel, _controlInput));
	}

	public Command getAutonomousCommand() {
		return _autoCommand;
	}
}
