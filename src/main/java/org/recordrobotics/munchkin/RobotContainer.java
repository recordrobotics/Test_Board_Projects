// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin;

import org.recordrobotics.munchkin.control.*;
import org.recordrobotics.munchkin.subsystems.*;
//import org.recordrobotics.munchkin.commands.manual.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * Contains subsystems, control and command scheduling
 */
@SuppressWarnings("PMD")
public class RobotContainer {
	// Control Scheme
	private IControlInput _controlInput;

	// Subsystems
	private BaseMotor _baseMotor;


	public RobotContainer() {
		_controlInput = new LegacyControl(RobotMap.Control.LEGACY_GAMEPAD);
		// _controlInput = new DoubleControl(RobotMap.Control.DOUBLE_GAMEPAD_1,
			// RobotMap.Control.DOUBLE_GAMEPAD_2);
		_baseMotor = new BaseMotor();
	}

	/**
	 * Create teleop commands
	 */
	public void teleopInit() {
		//CommandScheduler.getInstance().schedule(true,
		//	new ManualBaseMotor(_baseMotor, _controlInput));
	}

	/**
	 * Clear commands
	 */
	public void resetCommands() {
		CommandScheduler.getInstance().cancelAll();
	}

	public Command getAutonomousCommand() {
		return null;
	}
}
