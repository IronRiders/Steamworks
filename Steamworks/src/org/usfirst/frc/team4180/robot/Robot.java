package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	private static final int DRIVING_JOYSTICK_PORT = 1;
	
	private DriveTrain DriveTrain;
	private LambdaJoystick drivingJoystick;
	
    public void robotInit() {
        drivingJoystick = new LambdaJoystick(DRIVING_JOYSTICK_PORT, joystickInfo -> DriveTrain.updateSpeed(joystickInfo));
 		drivingJoystick.addButton(6, () -> {DriveTrain.toggleBackwards();},  () -> {});
    }
    

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {   	
    }
  
    public void teleopPeriodic() {     
    }
  
    public void testPeriodic() {    
    }   
}
