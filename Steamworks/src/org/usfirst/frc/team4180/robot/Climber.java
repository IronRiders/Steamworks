package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;


public class Climber { //This class is to climb the rope at the end of round.

	private VictorSP  ClimbVictor; //Declares the variable ClimbVictor - which is a motor
	private DigitalInput topSwitch;

	public Climber(int VictorPort, int TopSwitchPort) { //Method Climber takes in the port that ClimbVictor is plugged into, and creates ClimbVictor

		ClimbVictor = new VictorSP(VictorPort);
		topSwitch = new DigitalInput(TopSwitchPort);

	}

	public void updateSpeed(double[] JoystickInfo) { 


		double y = JoystickInfo[1]; //Creates y which is the y axis of the joystick

		ClimbVictor.set(y); //Sets the climb victor to go up/down based on joystick movments.

	}
	
	public boolean checkTopSwitch() {
		return topSwitch.get();
	}

	
}
