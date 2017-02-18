package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ramp {

	public DoubleSolenoid rampSolenoid;
	private boolean state =  false;
	
	public Ramp(int solenoidPort1, int solenoidPor2) {
		rampSolenoid = new DoubleSolenoid(solenoidPort1, solenoidPor2);
	}

	public void toggleRamp(){
		state = !state;
		if(state){
			rampSolenoid.set(DoubleSolenoid.Value.kForward);
			SmartDashboard.putString("DB/String 6", "kFOrward");
		}
		else {
			rampSolenoid.set(DoubleSolenoid.Value.kReverse);
			SmartDashboard.putString("DB/String 6", "kRev");
		}
	}
}
