package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;

public class Ramp {
	private Spark RampSpark; 
	
	private DigitalInput upSwitch;
	private DigitalInput downSwitch; 
	private DigitalInput rightGearCheckSwitch;
	private DigitalInput leftGearCheckSwitch;
	
	double idealSpeed = 0;

	public Ramp(int SparkPort, int upSwitchPort, int downSwitchPort, int leftCheckPort, int rightCheckPort) {
		RampSpark = new Spark(SparkPort);
		upSwitch = new DigitalInput(upSwitchPort);
		downSwitch = new DigitalInput(downSwitchPort);
		rightGearCheckSwitch = new DigitalInput(rightCheckPort);
		leftGearCheckSwitch = new DigitalInput(leftCheckPort);
	}

	public void setIdealSpeed(double idealSpeed) {
		 this.idealSpeed = idealSpeed;
	}
	
	public void updateSpeed() {
		if (idealSpeed <= 0 && ! downSwitch.get()) {
			RampSpark.set(idealSpeed);
		}
		else if (idealSpeed >= 0 && ! upSwitch.get()) {
			RampSpark.set(idealSpeed);
		}
		else {
			RampSpark.set(0);
		}
	}

	public void up() {
		setIdealSpeed(0.5);
	}
	
	public void down() {
		setIdealSpeed(-0.5);
	}
	
	public void stop() {
		setIdealSpeed(0);
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