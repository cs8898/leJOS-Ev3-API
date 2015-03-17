package de.cs8898.lejos.ev3.api.sensors;

import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

/**
 * @author Christian Schmied <cs8898@gmx.de>
 *
 */
public class Sensor{
	protected float value;
	protected SensorModes sensor;
	protected SampleProvider sampleProvider;
	protected float[] sample;
	
	/**
	 * @param eval [true will also evaluate]
	 * @return value of the sensor
	 */
	public float getValue(boolean eval){
		if(eval)
			this.eval();
		return this.value;
	}
	
	/**
	 * evaluates mean value of sensor
	 */
	public void eval(){
		this.sampleProvider.fetchSample(this.sample,0);
		float a = 0;
		for (int i = 0; i < this.sample.length; i++){
			a+=this.sample[i];
		}
		a/=this.sample.length;
		this.value = a;
	}
}
