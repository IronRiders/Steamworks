package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;

public class Ramp {
	private VictorSP  RampVictor;  // Declares the main (only) motor variable.
	private boolean ForwardBack = false; // Declares variable ForwardBack - Which will be used to determine which direction
										 // to move the ramp in.


	public Ramp(int VictorPort) {
// Creates main method
		RampVictor = new VictorSP(VictorPort);
		// Defines the (only) motor variable.
	}


	public void updateSpeed(double[] JoystickInfo) {
	// Creates the method that makes the motor gogogogo
		
		double z = JoystickInfo[2]; // Defines variable z, because the motor rotates on the z axis
		
		// The below if statment says that if ForwardBack (the boolean) is true, make the ramp motor go forwards. If
		// false, go backwards. 
		if (ForwardBack) { 
			RampVictor.set(z);
		}
		else{
			RampVictor.set(-z);
		}

	}
	
	//Changes the mode. This is used in Robot.java when the button is added. This way, the boolean is changed, and
	// the button makes it go forwards/back
	public void toggleForwardBack() {
		ForwardBack =! ForwardBack;
		
	}


}
