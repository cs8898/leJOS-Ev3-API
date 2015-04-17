package de.cs8898.lejos.ev3.api.motors;

import lejos.robotics.RegulatedMotor;

public class MotorControl{
	private RegulatedMotor motorLeft, motorRight;
	public MotorControl(RegulatedMotor motorLeft, RegulatedMotor motorRight){
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
	}
	
	public void rotate(int speed,int direction, int angle, boolean immediateReturn){
		this.setSpeed(speed, direction);
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.rotate(angle, immediateReturn);
		this.motorRight.rotate(angle, immediateReturn);
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}

	public double getMaxSpeed() {
		return (this.motorLeft.getMaxSpeed()<this.motorRight.getMaxSpeed()?this.motorLeft.getMaxSpeed():this.motorRight.getMaxSpeed());
	}

	public void setSpeed(int speed) {
		this.motorLeft.setSpeed(speed);
		this.motorRight.setSpeed(speed);
	}
	
	public void setSpeed(int speed, int direction){
		int[] motorSpeed = {speed,speed};
		
		int dir = -1;
		if(direction < 0)
			dir = 0;
		else if (direction > 0)
			dir = 1;
		
		double uDir = Math.abs(direction);
		
		if (dir > -1)
			motorSpeed[dir] = (int)((50-uDir)/50*speed);
		
		this.motorLeft.setSpeed(motorSpeed[0]);
		this.motorRight.setSpeed(motorSpeed[1]);
	}
	
	public void stop(){
		this.stop(false);
	}
	
	public void stop(boolean immediateReturn){
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.stop(immediateReturn);
		this.motorRight.stop(immediateReturn);
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}
	
	public void forward(){
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.forward();
		this.motorRight.forward();
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}
	
	public void backward(){
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.backward();
		this.motorRight.backward();
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}
}
