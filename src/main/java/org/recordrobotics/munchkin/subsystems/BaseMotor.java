package org.recordrobotics.munchkin.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BaseMotor extends SubsystemBase {
	// motor and limit switch variables
	private CANSparkMax _baseMotor = new CANSparkMax(2, MotorType.kBrushless);

	public BaseMotor() {
		_baseMotor.set(0);
	}

	//basic running method, replace as needed
	public void run(double v){
		_baseMotor.set(v);
	}

}
