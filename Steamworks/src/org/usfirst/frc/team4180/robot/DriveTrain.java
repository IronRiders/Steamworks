package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
	private VictorSP leftVictor, rightVictor;

	private boolean backwards = false; // boolean to control backwards or
										// forwards

	public DriveTrain(int leftPort, int rightPort) {
		leftVictor = new VictorSP(leftPort); // create leftvictor
		rightVictor = new VictorSP(rightPort);// create rightvictor
	}

	public void updateSpeed(double[] JoystickInfo) {
		double x = JoystickInfo[0];
		double y = JoystickInfo[1];
		if (backwards) {
			leftVictor.set((-y + x) * .50); // max speed is 35% of normal max
											// speed
			rightVictor.set((y + x) * .50); // aka speed cap instated
		} else {
			leftVictor.set((y - x) * .50); // max speed is 35% of normal max
											// speed
			rightVictor.set((-y - x) * .50); // aka speed cap instated
		}
	}

	public void toggleBackwards() {
		backwards = !backwards; // change the boolean when the button is pressed
								// in Robot.java
	}

	public boolean getBackwards() { // Output the data (true or false)
									// Eventaully this will be called, to show
									// to the drivers.
		return backwards;
	}

	public void setBackwards(boolean value) {
		backwards = value;
	}

	public VictorSP getLeftVictor() {
		return leftVictor;
	}

	public VictorSP getRightVictor() {
		return rightVictor;
	}
}