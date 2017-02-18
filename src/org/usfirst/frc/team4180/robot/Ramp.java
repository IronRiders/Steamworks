package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
<<<<<<< Upstream, based on origin/master
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
=======
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
>>>>>>> ab465cc Fixed Solenoid mocker

public class Ramp {

	private DoubleSolenoid rampSolenoid;
	
	public Ramp(int solenoidPort1, int solenoidPor2) {
		rampSolenoid = new DoubleSolenoid(solenoidPort1, solenoidPor2);
		rampSolenoid.set(Value.kReverse);
	}

<<<<<<< Upstream, based on origin/master
	public void toggleRamp(){
		state = !state;
		if(state){
			rampSolenoid.set(DoubleSolenoid.Value.kForward);
			SmartDashboard.putString("DB/String 6", "kFOrward");
=======
	public void toggleRamp() {
		if(rampSolenoid.get() == Value.kReverse) {
			rampSolenoid.set(Value.kForward);
>>>>>>> ab465cc Fixed Solenoid mocker
		}
		else {
<<<<<<< Upstream, based on origin/master
			rampSolenoid.set(DoubleSolenoid.Value.kReverse);
			SmartDashboard.putString("DB/String 6", "kRev");
=======
			rampSolenoid.set(Value.kReverse);
>>>>>>> ab465cc Fixed Solenoid mocker
		}
	}
<<<<<<< Upstream, based on origin/master
}
=======
	
	public Value get() {
		return rampSolenoid.get();
	}
	
	public void set(Value newValue) {
		rampSolenoid.set(newValue);
	}
}
>>>>>>> ab465cc Fixed Solenoid mocker
