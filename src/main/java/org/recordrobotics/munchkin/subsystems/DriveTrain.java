// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.recordrobotics.munchkin.RobotMap;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
	private CANSparkMax[] _leftMotors = { new CANSparkMax(RobotMap.DriveBase.LEFT_FRONT_MOTOR_PORT, MotorType.kBrushless),
									new CANSparkMax(RobotMap.DriveBase.LEFT_BACK_MOTOR_PORT, MotorType.kBrushless)};
	private CANSparkMax[] _rightMotors = { new CANSparkMax(RobotMap.DriveBase.RIGHT_FRONT_MOTOR_PORT, MotorType.kBrushless),
										new CANSparkMax(RobotMap.DriveBase.RIGHT_BACK_MOTOR_PORT, MotorType.kBrushless)};
	private MotorControllerGroup _leftDriveMotors = new MotorControllerGroup(_leftMotors);
	private MotorControllerGroup _rightDriveMotors = new MotorControllerGroup(_rightMotors);
	private DifferentialDrive _differentialDrive = new DifferentialDrive(_leftDriveMotors, _rightDriveMotors);

	public DifferentialDrive getDifferentialDrive(){
		return _differentialDrive;
	}
	public void stopMotors(){
		_leftDriveMotors.set(0);
		_rightDriveMotors.set(0);
	}
	/** Creates a new ExampleSubsystem. */
	@SuppressWarnings({"PMD.UnnecessaryConstructor", "PMD.UncommentedEmptyConstructor"})
	public DriveTrain() {}
}
