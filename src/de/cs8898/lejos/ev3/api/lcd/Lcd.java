package de.cs8898.lejos.ev3.api.lcd;

import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;

public class Lcd {
	private GraphicsLCD gLcd;
	private Font font;
	private int offsetTop = 0;
	private int[] size;
	
	public Lcd(){
		this.gLcd = lejos.hardware.ev3.LocalEV3.get().getGraphicsLCD();
		this.size = new int[2];
		this.size[0] = this.gLcd.getWidth();
		this.size[1] = this.gLcd.getHeight();
		this.setFont(lejos.hardware.lcd.Font.getSmallFont());
	}
	
	public void print (String text){
		this.gLcd.drawString(text, this.offsetTop, 0, 0);
		this.offsetTop += this.font.height + 2;
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public void scroll(int pix){
		byte[] oldDisplay = this.gLcd.getDisplay();
		//byte[] newDisplay = new byte[oldDisplay.length];
		print("Old Display Length " + oldDisplay.length);
	}
}
