package de.cs8898.lejos.ev3.api.motors;

import lejos.robotics.RegulatedMotor;

public class MotorControl{
	private RegulatedMotor motorLeft, motorRight;
	public MotorControl(RegulatedMotor motorLeft, RegulatedMotor motorRight){
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
	}
	
	public void rotate(int speed,int direction, int angle, boolean immediateReturn){
		int[] motorSpeed = {speed,speed};
		
		int dir = -1;
		if(direction < 0)
			dir = 0;
		else if (direction > 0)
			dir = 1;
		
		double uDir = Math.pow(Math.pow(direction, 2),0.5);
		
		if (dir > -1)
			motorSpeed[dir] = (int)(50-uDir)/50*speed;
		
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.setSpeed(motorSpeed[0]);
		this.motorRight.setSpeed(motorSpeed[1]);
		this.motorLeft.rotate(angle, immediateReturn);
		this.motorRight.rotate(angle, immediateReturn);
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}
}
