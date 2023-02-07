// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin;

import java.io.IOException;

import edu.wpi.first.wpilibj.RobotBase;

// This will suppress all the PMD warnings in this class
@SuppressWarnings("PMD")
public final class Main {

	private Main() {}

	public static void main(String... args) {
		RobotBase.startRobot(() -> {
			try {
				return new Robot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		});
	}
}
