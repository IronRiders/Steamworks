package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class Climber {

	private VictorSP ClimbVictor; 
	private DigitalInput topSwitch; 
	// Top switch is used to ensure that the motors don't spin when the robot is at the top of the rope.

	public Climber(int VictorPort, int TopSwitchPort) { 
		ClimbVictor = new VictorSP(VictorPort);
		topSwitch = new DigitalInput(TopSwitchPort);
	}
	
	//sets speed of motor to the y location of the joystick.
	public void updateSpeed(double[] JoystickInfo) { 
		double y = JoystickInfo[1];						
		if (atTop() && y > 0) {
			ClimbVictor.set(0);
		}
		else {
			ClimbVictor.set(y); 
		}
	}

	public boolean atTop() {
		return topSwitch.get(); 
	}

}