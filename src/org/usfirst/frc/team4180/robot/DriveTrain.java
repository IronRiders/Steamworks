package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
	private VictorSP leftVictor, rightVictor;
	private boolean backwards = false;
	private DoubleSolenoid gearShifting;
	
	private boolean state = false;

	public DriveTrain(int leftPort, int rightPort, int gearShiftPort1, int gearShiftPort2) {
		leftVictor = new VictorSP(leftPort); 
		rightVictor = new VictorSP(rightPort);
		gearShifting = new DoubleSolenoid(gearShiftPort1, gearShiftPort2);
	}

	// This method takes the position of the joystick, and moves the robot accordingly.
	public void updateSpeed(double[] JoystickInfo) {
		double x = JoystickInfo[0];
		double y = JoystickInfo[1];
		if (backwards) {
			leftVictor.set(-y - x); 							
			rightVictor.set(y - x); 
		} else {
			leftVictor.set(y - x); 								
			rightVictor.set(-y - x); 
		}
	}
	
	public void toggleGearShifting() {
		setGear(!state);
	}
	
	public void setGear(boolean b){
		state = b;
		if (state) {
			gearShifting.set(DoubleSolenoid.Value.kReverse);
		} else {
			gearShifting.set(DoubleSolenoid.Value.kForward);
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

	public VictorSP getLeftVictor() {
		return leftVictor;
	}

	public VictorSP getRightVictor() {
		return rightVictor;
	}
}