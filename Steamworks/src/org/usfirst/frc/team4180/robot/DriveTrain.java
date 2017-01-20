package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
	private VictorSP leftVictor, rightVictor;

	private boolean backwards = false;

	public DriveTrain(int leftPort, int rightPort) {
		leftVictor = new VictorSP(leftPort);
		rightVictor = new VictorSP(rightPort);
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
		backwards = !backwards;
	}
	
	public boolean getBackwards() {
		return backwards;
	}
	
	public void setBackwards(boolean value) {
		backwards = value;
	}
}
