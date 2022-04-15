package frc.robot;

import frc.robot.controllers.XboxJoystick;

public class OI {
	/**
	 * TODO: set these based on what the controllers will actually do
	 * controller 1 is for ___
	 * controller 2 is for ___
	 */
	private final XboxJoystick xbox1;
	private final XboxJoystick xbox2;

	//creates controllers when initialized
	public OI() {
		xbox1 = new XboxJoystick(RobotMap.xboxPort1);
		xbox2 = new XboxJoystick(RobotMap.xboxPort2);
	}
	// GETTERS for XBox controllers
	public XboxJoystick getXbox1() {
		return xbox1;
	}
	public XboxJoystick getXbox2() {
		return xbox2;
	}
}
