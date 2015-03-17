package de.cs8898.lejos.ev3.api.motors;

import lejos.robotics.RegulatedMotor;

public class MotorControl{
	private RegulatedMotor motorLeft, motorRight;
	public MotorControl(RegulatedMotor motorLeft, RegulatedMotor motorRight){
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
	}
	
	public void rotate(int speed,int direction, int angle, boolean immediateReturn){
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.setSpeed((int)(direction<0?(100+direction)*0.01*speed:speed));
		this.motorRight.setSpeed((int)(direction>0?(100-direction)*0.01*speed:speed));
		this.motorLeft.rotate(angle, immediateReturn);
		this.motorRight.rotate(angle, immediateReturn);
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}
}
