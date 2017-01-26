package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	//All below creates variables that store the ports (will be added later, along with more thorough comments)
	private static final int DRIVING_JOYSTICK_PORT = -1;
	private static final int CLIMBING_JOYSTICK_PORT = -1;
	private static final int LEFT_DRIVETRAIN_PORT = -1;
	private static final int RIGHT_DRIVETRAIN_PORT = -1;
	private static final int CLIMBER_PORT = -1;
	private static final int RAMP_PORT = -1;
	private static final int UP_SWITCH_PORT = -1;
	private static final int DOWN_SWITCH_PORT = -1;
	//
	private DriveTrain driveTrain;
	private LambdaJoystick drivingJoystick;
	private Climber climber;
	private LambdaJoystick climbingJoystick;
	private Ramp ramp;
	
    public void robotInit() {
    	
    	ramp = new Ramp(RAMP_PORT,UP_SWITCH_PORT,DOWN_SWITCH_PORT);
    	driveTrain = new DriveTrain(LEFT_DRIVETRAIN_PORT,RIGHT_DRIVETRAIN_PORT);
    	climber = new Climber(CLIMBER_PORT);
    	
        drivingJoystick = new LambdaJoystick(DRIVING_JOYSTICK_PORT, driveTrain::updateSpeed);
 	drivingJoystick.addButton(6, driveTrain::toggleBackwards,  () -> {});
 	drivingJoystick.addButton(11, ramp::onUp, ramp::off);
 	drivingJoystick.addButton(10, ramp::onDown, ramp::off);
 		
 		
 	climbingJoystick = new LambdaJoystick(CLIMBING_JOYSTICK_PORT, climber::updateSpeed);
 		
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
