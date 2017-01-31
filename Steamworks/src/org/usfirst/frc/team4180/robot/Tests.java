package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.Timer;

public class Tests {
	private final Robot robot;
	
	public Tests(Robot robot) {
		this.robot = robot;
	}
	
	// This is a stress test that drives the robot around in circles
	// switching direction after a certain period of time
	public void circles() {
		Timer stressTestOne = new Timer();
		stressTestOne.start();
		double[] joystickLocation = new double[]{0,1};
		
		while(stressTestOne.get() == 300){
			DriveTrain.updateSpeed(joystickLocation[]);
			
			
			
			
		}
		
	
	}

}
