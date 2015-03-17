package de.cs8898.lejos.ev3.api.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;

public class UltrasonicSensor extends Sensor{
	/**
	 * @param the Port where the sensor is atached
	 * @param the String of the sensor mode
	 */
	public UltrasonicSensor(Port port,String modeName){
		this.sensor =  new EV3UltrasonicSensor(port);
		this.sampleProvider = this.sensor.getMode(modeName);
		this.sample = new float[this.sensor.sampleSize()];
	}
	
	/**
	 * @param the Port where the sensor is atached
	 * @param the number of the sensor mode
	 */
	public UltrasonicSensor(Port port, int mode){
		this.sensor =  new EV3UltrasonicSensor(port);
		this.sampleProvider = this.sensor.getMode(mode);
		this.sample = new float[this.sensor.sampleSize()];
	}
}
