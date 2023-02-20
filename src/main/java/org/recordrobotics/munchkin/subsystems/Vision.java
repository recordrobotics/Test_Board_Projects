package org.recordrobotics.munchkin.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase{

	public PhotonCamera camera = new PhotonCamera("OV5647"); //IMPORTANT: This camera name MUST match the one on the Raspberry Pi, accessible through the PhotonVision UI.
	public Transform3d robotToCam = new Transform3d(new Translation3d(0.2, 0, 0.2), new Rotation3d(0, 0, 0)); //Cam mounted facing forward, half a meter forward of center, half a meter up from center.

	static double[][] tags = {//april tags 1-8 in order. values contained are x, y, z, theta, in that order. x, y, z are distances in meters, theta is in radians.
		{15.513558, 1.071626, 0.462788, Math.PI}, //tag 1
		{15.513558, 2.748026, 0.462788, Math.PI}, //tag 2
		{15.513558, 4.424426, 0.462788, Math.PI}, //tag 3
		{16.178784, 6.749796, 0.695452, Math.PI}, //tag 4
		{0.36195, 6.749796, 0.695452, 0}, //tag 5
		{1.02743, 4.424426, 0.462788, 0}, //tag 6
		{1.02743, 2.748026, 0.462788, 0}, //tag 7
		{1.02743, 1.071626, 0.462788, 0}}; //tag 8
	double[] fieldDimensions = {16.54175, 8.0137};//x, y. The origin is at the bottom corner of the blue alliance wall as seen on the field drawings. 0 radians is parallel to the positive x-axis.

	public static double[] getVisionPoseEstimate(PhotonCamera camera, Transform3d robotToCam){
		var result = camera.getLatestResult();
		boolean hasTargets = result.hasTargets();
		if (hasTargets){
			PhotonTrackedTarget target = result.getBestTarget();
			int targetID = target.getFiducialId();
			Transform3d bestRobotToTarget = target.getBestCameraToTarget().plus(robotToCam.inverse());
			double yaw = target.getYaw();
			double distance = Math.sqrt((bestRobotToTarget.getX()*bestRobotToTarget.getX()) + (bestRobotToTarget.getY()*bestRobotToTarget.getY()));
			double x_transform = Math.cos(yaw)*distance;
			double y_transform = Math.sin(yaw)*distance;
			double global_x = tags[targetID][1] + x_transform;
			double global_y = tags[targetID][2] + y_transform;
			double global_theta = tags[targetID][4] + Math.PI + bestRobotToTarget.getRotation().toRotation2d().getRadians(); //Pi is added because if the camera sees the tag, it is necessarily looking in the direction opposite the tag's orientation.
			double[] globalPose = {global_x, global_y, global_theta};
			return globalPose;
		} else
		return null;
		
	}
	    public static double[] getColoredObjectPose(PhotonCamera camera, Transform3d robotToCam, DifferentialDrivePoseEstimator estimator){
        var result = camera.getLatestResult();//get a frame from the camera
        boolean hasTargets = result.hasTargets();//check for targets. This MUST be checked for, otherwise an error will occur if there isn't a target.
        if (hasTargets){
            PhotonTrackedTarget target = result.getBestTarget();//Choose the closest colored object of the type being looked for
            Transform3d bestRobotToTarget = target.getBestCameraToTarget().plus(robotToCam.inverse());//getting the transform from the robot's center to the object. These coordinates are robot-relative, and so for trajectory following will be converted to global coordinates below.
            Pose2d robotPose = estimator.getEstimatedPosition();
            double distance = Math.sqrt(bestRobotToTarget.getX()*bestRobotToTarget.getX() + bestRobotToTarget.getY()*bestRobotToTarget.getY());//getting the distance between the camera and the object from the x and y components of the transform.
            double globalX = Math.cos(robotPose.getRotation().getRadians())*distance;
            double globalY = Math.sin(robotPose.getRotation().getRadians())*distance;
            double globalTheta = bestRobotToTarget.getRotation().toRotation2d().getRadians() + robotPose.getRotation().getRadians();
            double[] globalPose = {globalX, globalY, globalTheta};
            return globalPose;
        } else
        return null;
    }

    public boolean checkForTarget(PhotonCamera camera, Transform3d robotToCam){
        var result = camera.getLatestResult();//get a frame from the camera
        boolean hasTargets = result.hasTargets();//check for targets. This MUST be checked for, otherwise an error will occur if there isn't a target.
        return hasTargets;
    }
}
