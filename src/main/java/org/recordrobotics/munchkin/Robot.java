// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
	private RobotContainer _robotContainer;
	private Command _autonomousCommand;

	org.recordrobotics.munchkin.subsystems.Vision vision = new org.recordrobotics.munchkin.subsystems.Vision();

	/**
	 * Robot initialization
	 */

	@Override
	public void robotInit() {
		System.out.println("Rootinit");
		// Create container
		_robotContainer = new RobotContainer();
	}


	/**
	 * Runs every robot tick
	 */
	@Override
	public void robotPeriodic() {
		//System.out.println("Robot periodic");
		// Run command scheduler
		CommandScheduler.getInstance().run();
	}

	/**
	 * Runs when robot enters disabled mode
	 */
	@Override
	public void disabledInit() {
		System.out.println("Disabled init");
		//_robotContainer.resetCommands();
	}

	/**
	 * Runs every tick during disabled mode
	 */
	@Override
	public void disabledPeriodic() {
		//System.out.println("Disabled periodic");
		// TODO
	}

	/**
	 * Runs when robot enters auto mode
	 */
	@Override
	public void autonomousInit() {
		System.out.println("Autonomous Init");
		_autonomousCommand = _robotContainer.getAutonomousCommand();

		// schedule the autonomous command (example)
		if (_autonomousCommand != null) {
			_autonomousCommand.schedule();
		}
		//TODO: set an initial value for the estimator here somehow?
	}

	/**
	 * Runs every tick during auto mode
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.println("Autonomous periodic");
		if (vision.checkForTarget(vision.camera, vision.robotToCam)){
			double[] globalPose = org.recordrobotics.munchkin.subsystems.Vision.getVisionPoseEstimate(vision.camera, vision.robotToCam);
			System.out.println(globalPose[0]);
			System.out.println(globalPose[1]);
			System.out.println(globalPose[2]);
			System.out.println(globalPose[3]);
		}
		}

	/**
	 * Runs when robot enters teleop mode
	 */
	@Override
	public void teleopInit() {
		System.out.println("Teleop init");
		// TODO
		if (_autonomousCommand != null) {
			_autonomousCommand.cancel();
		}
		_robotContainer.teleopInit();
	}


	/**
	* Runs every tick in teleop mode
	*/
	@Override
	public void teleopPeriodic() {
		//placholder
		}


	}

//}
