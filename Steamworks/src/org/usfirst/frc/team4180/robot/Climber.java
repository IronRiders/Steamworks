package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber {
	private VictorSP  ClimbVictor; // set a victor for climb

	public Climber(int VictorPort) {
		ClimbVictor = new VictorSP(VictorPort);
	}

	public void updateSpeed(double[] JoystickInfo) {
		double x = JoystickInfo[0];
		double y = JoystickInfo[1];
		ClimbVictor.set(y - x); 
	}
}
