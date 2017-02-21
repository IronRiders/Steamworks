package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ramp {

	private DoubleSolenoid rampSolenoid;
	
	public Ramp(int solenoidPort1, int solenoidPort2) {
		rampSolenoid = new DoubleSolenoid(solenoidPort1, solenoidPort2);
		rampSolenoid.set(Value.kReverse);
	}
	
	public void toggleRamp() {
		if(rampSolenoid.get() == Value.kReverse) {
			rampSolenoid.set(Value.kForward);
		}
		else {
			rampSolenoid.set(Value.kReverse);
		}
	}
	
	public Value get() {
		return rampSolenoid.get();
	}
	
	public void set(Value newValue) {
		rampSolenoid.set(newValue);
	}
}
