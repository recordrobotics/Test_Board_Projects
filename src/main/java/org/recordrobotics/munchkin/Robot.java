// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.recordrobotics.munchkin;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;
//import org.photonvision.PhotonUtils;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.PhotonPoseEstimator;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.io.IOException;

//TO-DO: organize this in a separate file
//AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
@SuppressWarnings("PMD")
public class Robot extends TimedRobot {
	private RobotContainer _robotContainer;
	private Command _autonomousCommand;


	public Robot() throws IOException
	{
		System.out.println("verily, an IOException error hath been thrown");
	}

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
		_robotContainer.resetCommands();
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
		// TODO
		_autonomousCommand = _robotContainer.getAutonomousCommand();

		// schedule the autonomous command (example)
		if (_autonomousCommand != null) {
			_autonomousCommand.schedule();
		}
	}

	/**
	 * Runs every tick during auto mode
	 */
	@Override
	public void autonomousPeriodic() {
		System.out.println("Autonomous periodic");
		// TODO
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



	//try
	//{
	//    AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);
	//}
	//catch (IOException e) {
	//    System.out.println(e.getMessage()); //if you're using a logger, you can use that instead to print.
	//    //e.printStackTrace(); //or print the full stack.
	//}

	/**
	 * Initializes the PhotonCamera object
	 */

	//AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.loadAprilTagFieldLayout(AprilTagFields.k2023ChargedUp.m_resourceFile);
	AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadFromResource(AprilTagFields.k2023ChargedUp.m_resourceFile);

	PhotonCamera camera = new PhotonCamera("OV5647");
	Transform3d robotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0,0,0)); //Cam mounted facing forward, half a meter forward of center, half a meter up from center.
	PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.CLOSEST_TO_REFERENCE_POSE, camera, robotToCam);
	public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose) {
		photonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
		return photonPoseEstimator.update();
	}

	/**
	 * Runs every tick in teleop mode
	 */
	@Override
	public void teleopPeriodic() {
		//System.out.println("STARTING2");

		var result = camera.getLatestResult();

		//EstimatedRobotPose pose = getEstimatedGlobalPose([0,0]);
		
		System.out.println(result.getTargets());
		System.out.println(camera.toString());
		camera.takeInputSnapshot();
		//java.util.concurrent.TimeUnit.SECONDS.sleep(15);

		/**

		boolean hasTargets = result.hasTargets();
		System.out.println(hasTargets);
		if (hasTargets == true){
			PhotonTrackedTarget target = result.getBestTarget();
			double yaw = target.getYaw();
			double pitch = target.getPitch();
			double area = target.getArea();
			double skew = target.getSkew();
			//Transform2d pose = target.getCameraToTarget();
			//List<TargetCorner> corners = target.getCorners();
			//int targetID = target.getFiducialId();
			//double poseAmbiguity = target.getPoseAmbiguity();
			//Transform3d bestCameraToTarget = target.getBestCameraToTarget();
			//Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();

			System.out.println(yaw);
			System.out.println(pitch);
			System.out.println(area);
			System.out.println(skew);
			//System.out.println(targetID);
			//System.out.println(poseAmbiguity);
		}

		*/


	}

}
