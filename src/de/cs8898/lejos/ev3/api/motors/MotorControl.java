package de.cs8898.lejos.ev3.api.motors;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;

public class MotorControl{
	private RegulatedMotor motorLeft, motorRight;
	private int[] motorSpeed;
	public MotorControl(RegulatedMotor motorLeft, RegulatedMotor motorRight){
		this.motorLeft = motorLeft;
		this.motorRight = motorRight;
		this.motorSpeed = new int[2];
	}
	/**
	 * 
	 * @param speed in deg per sec
	 * @param direction -100 to 100 left turn right turn abs(100) ^= rotate on place
	 * @param angle rotationAngle in deg
	 * @param immediateReturn
	 */
	public boolean rotate(int speed,int direction, int angle, boolean immediateReturn){
		this.setSpeed(speed, direction);
		//this.motorSpeed[0] = -1;
		//this.motorSpeed[1] = -1;
		System.out.println(this.motorSpeed[0]+";;"+this.motorSpeed[1]);
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		this.motorLeft.rotate((this.motorSpeed[0]<0?-1:1)*angle, immediateReturn);
		this.motorRight.rotate((this.motorSpeed[1]<0?-1:1)*angle, immediateReturn);
		this.motorLeft.addListener(new RegulatedMotorListener(){

			@Override
			public void rotationStarted(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
			}

			@Override
			public void rotationStopped(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				if (motorRight.isMoving()){
					motorRight.stop();
				}
			}
			
		});
		this.motorRight.addListener(new RegulatedMotorListener(){

			@Override
			public void rotationStarted(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
			}

			@Override
			public void rotationStopped(RegulatedMotor motor, int tachoCount, boolean stalled, long timeStamp) {
				if(motorLeft.isMoving()) motorLeft.stop();
			}
			
		});
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
		
		if(!immediateReturn){
			while(this.motorLeft.isMoving()||this.motorRight.isMoving()){
				try {
				    Thread.sleep(5);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
			return true;
		}else{
			return true;
		}
	}

	public double getMaxSpeed() {
		return (this.motorLeft.getMaxSpeed()<this.motorRight.getMaxSpeed()?this.motorLeft.getMaxSpeed():this.motorRight.getMaxSpeed());
	}

	public void setSpeed(int speed) {
		this.motorSpeed[0] = this.motorSpeed[1] = speed;
		this.motorLeft.setSpeed(speed);
		this.motorRight.setSpeed(speed);
	}
	/**
	 * 
	 * @param speed degree per secon
	 * @param direction -100 to 100 left turn - right turn
	 * 					abs(100) ^= rotate on place
	 * 					abs(50)  ^= only one rotates
	 */
	public void setSpeed(int speed, int direction){
		this.motorSpeed[0] = this.motorSpeed[1] = speed;
		
		int dir = -1;
		if(direction < 0){//links langsamer
			dir = 0;
		}else if (direction > 0){//rechts langsamer
			dir = 1;
		}
		
		if (dir > -1){//wenn einer langsamer werden muss
			this.motorSpeed[dir] = (int)(((50f-Math.abs(direction))/50f)*speed);
		}
		
		this.motorLeft.setSpeed(Math.abs(this.motorSpeed[0]));
		this.motorRight.setSpeed(Math.abs(this.motorSpeed[1]));
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
	
	public void on(){
		this.motorLeft.startSynchronization();
		this.motorRight.startSynchronization();
		if(this.motorSpeed[0] >= 0) {
			this.motorLeft.forward();
		}else{
			this.motorLeft.backward();
		}
		if(this.motorSpeed[1] >= 0) {
			this.motorRight.forward();
		}else{
			this.motorRight.backward();
		}
		this.motorLeft.endSynchronization();
		this.motorRight.endSynchronization();
	}

	public void close() {
		this.motorLeft.close();
		this.motorRight.close();
	}
	
	public boolean isMoving(){
		return this.motorLeft.isMoving()||this.motorRight.isMoving();
	}
}
