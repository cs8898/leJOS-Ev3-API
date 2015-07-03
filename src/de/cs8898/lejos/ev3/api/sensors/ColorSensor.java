package de.cs8898.lejos.ev3.api.sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;

public class ColorSensor extends Sensor{
	/**
	 * @param the Port where the sensor is atached
	 * @param the String of the sensor mode
	 */
	public ColorSensor(Port port,String modeName){
		this.sensor =  new EV3ColorSensor(port);
		this.sampleProvider = this.sensor.getMode(modeName);
		this.sample = new float[this.sensor.sampleSize()];
	}
	
	/**
	 * @param the Port where the sensor is atached
	 * @param the number of the sensor mode
	 */
	public ColorSensor(Port port, int mode){
		this.sensor =  new EV3ColorSensor(port);
		this.sampleProvider = this.sensor.getMode(mode);
		this.sample = new float[this.sensor.sampleSize()];
	}
}
