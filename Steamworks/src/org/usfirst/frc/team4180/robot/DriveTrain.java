package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
	private VictorSP leftVictor, rightVictor; 

	private boolean backwards = false;  // boolean to control backwards or forwards

	public DriveTrain(int leftPort, int rightPort) {
		leftVictor = new VictorSP(leftPort);  //create leftvictor
		rightVictor = new VictorSP(rightPort);//create rightvictor
	}

	public void updateSpeed(double[] JoystickInfo) {
		double x = JoystickInfo[0];    // create x axis of joystick 
		double y = JoystickInfo[1];    // create y axis of joystick  
		if (backwards) { // if the variable backwards is set to true then go backwards, otherwise go forwards
			leftVictor.set(-y + x); 
											
			rightVictor.set(y + x); 
		} else {
			leftVictor.set(y - x); 
											
			rightVictor.set(-y - x); 
		}
	}

	public void toggleBackwards() {
		backwards = !backwards; // change the boolean when the button is pressed in Robot.java
	}
	
	public boolean getBackwards() { //Output the data (true or false) Eventaully this will be called, to show to the drivers.
		return backwards;
	}
	
	public void setBackwards(boolean value) { 
		backwards = value;
	}
}