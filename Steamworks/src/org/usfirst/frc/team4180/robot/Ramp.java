package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class Ramp {
	private Spark RampSpark; 
	private DigitalInput upSwitch; 
									
	private DigitalInput downSwitch; 
	private DigitalInput rightGearCheckSwitch;
	private DigitalInput leftGearCheckSwitch;

	public Ramp(int SparkPort, int upSwitchPort, int downSwitchPort, int leftCheckPort, int rightCheckPort) {
		// Creates main method
		RampSpark = new Spark(SparkPort);
		// Defines the (only) motor variable.
		upSwitch = new DigitalInput(upSwitchPort);
		downSwitch = new DigitalInput(downSwitchPort);
		rightGearCheckSwitch = new DigitalInput(rightCheckPort);
		leftGearCheckSwitch = new DigitalInput(leftCheckPort);
	}

	public void onUp() {
		if (upSwitch.get() == false) {
			RampSpark.set(.25);
		}
	}

	public void onDown() {
		if (downSwitch.get() == false) {
			RampSpark.set(-.25);
		}
	}

	public void off() {
		RampSpark.set(0);
	}

	public boolean rightGearCheck() {
		return rightGearCheckSwitch.get();

	}

	public boolean lefttGearCheck() {
		return leftGearCheckSwitch.get();

	}
	public Spark getSpark(){
		return RampSpark;
	}

}