// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
	private CANSparkMax[] leftMotors = { new CanSparkMax(RobotMap.DriveBase.LEFT_FRONT_MOTOR_PORT, kBrushless),
									  new CanSparkMax(RobotMap.DriveBase.LEFT_BACK_MOTOR_PORT, kBrushless)};
	private CANSparkMax[] rightMotors = { new CanSparkMax(RobotMap.DriveBase.RIGHT_FRONT_MOTOR_PORT, kBrushless),
										new CanSparkMax(RobotMap.DriveBase.RIGHT_BACK_MOTOR_PORT, kBrushless)};
	private MotorControllerGroup leftDriveMotors = new MotorControllerGroup(leftMotors);
	private MotorControllerGroup rightDriveMotors = new MotorControllerGroup(rightMotors);
	private DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

	public DifferentialDrive GetDifferentialDrive(){
		return differentialDrive;
	}
	public void stopMotors(){
		leftDriveMotors.set(0);
		rightDriveMotors.set(0);
	}
	/** Creates a new ExampleSubsystem. */
	@SuppressWarnings({"PMD.UnnecessaryConstructor", "PMD.UncommentedEmptyConstructor"})
	public DriveTrain() {}

	@Override
	public void periodic() {

	}

	@Override
	public void simulationPeriodic() {
		// This method will be called once per scheduler run during simulation
	}
}
