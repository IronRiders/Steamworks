package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class Ramp {
	private VictorSP RampVictor; // Declares the main (only) motor variable.
	private DigitalInput upSwitch; // Declares the switch that is (IDK) when the
									// ramp moves upwards.
	private DigitalInput downSwitch; // Declares the switch that is under the
										// ramp (when it moves back into place)
	private DigitalInput rightGearCheckSwitch;
	private DigitalInput leftGearCheckSwitch;

	public Ramp(int VictorPort, int upSwitchPort, int downSwitchPort, int leftCheckPort, int rightCheckPort) {
		// Creates main method
		RampVictor = new VictorSP(VictorPort);
		// Defines the (only) motor variable.
		upSwitch = new DigitalInput(upSwitchPort);
		downSwitch = new DigitalInput(downSwitchPort);
		rightGearCheckSwitch = new DigitalInput(rightCheckPort);
		leftGearCheckSwitch = new DigitalInput(leftCheckPort);
	}

	public void onUp() {
		if (upSwitch.get() == false) {
			RampVictor.set(.25);
		}
	}

	public void onDown() {
		if (downSwitch.get() == false) {
			RampVictor.set(-.25);
		}
	}

	public void off() {
		RampVictor.set(0);
	}

	public boolean rightGearCheck() {
		return rightGearCheckSwitch.get();

	}

	public boolean lefttGearCheck() {
		return leftGearCheckSwitch.get();

	}
	public VictorSP getVictor(){
		return RampVictor;
	}

}