package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;

public class Climber {
	
	private VictorSP ClimbVictor; 
	
	public Climber(int VictorPort) { 
		ClimbVictor = new VictorSP(VictorPort);
	}
	
	public void updateSpeed(double speed) { 
		ClimbVictor.set(speed);
	}
	public double getSpeed(){
		return ClimbVictor.get();
	}
}