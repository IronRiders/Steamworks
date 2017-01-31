package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	// DIO Port Constants
	public static final int UP_SWITCH_PORT = -1;
	public static final int DOWN_SWITCH_PORT = -1;
	public static final int LEFT_CHECK_PORT = -1;
	public static final int RIGHT_CHECK_PORT = -1;
	public static final int TOP_SWITCH_PORT = -1;

	// PWM Port Constants
	public static final int LEFT_DRIVETRAIN_PORT = 0;
	public static final int RIGHT_DRIVETRAIN_PORT = 1;
	public static final int CLIMBER_PORT = 3;
	public static final int RAMP_PORT = 2;
	
	// Analog Ports
	
	// Joystick Port Constants
	public static final int DRIVING_JOYSTICK_PORT = 0;
	public static final int CLIMBING_JOYSTICK_PORT = 1;
	
	private DriveTrain driveTrain;
	private LambdaJoystick drivingJoystick;
	private Climber climber;
	private LambdaJoystick climbingJoystick;
	private Ramp ramp;

	public void robotInit() {

		ramp = new Ramp(RAMP_PORT, UP_SWITCH_PORT, DOWN_SWITCH_PORT, LEFT_CHECK_PORT, RIGHT_CHECK_PORT);
		driveTrain = new DriveTrain(LEFT_DRIVETRAIN_PORT, RIGHT_DRIVETRAIN_PORT);
		climber = new Climber(CLIMBER_PORT, TOP_SWITCH_PORT);

		drivingJoystick = new LambdaJoystick(DRIVING_JOYSTICK_PORT, driveTrain::updateSpeed);

		drivingJoystick.addButton(6, driveTrain::toggleBackwards, () -> {});
		drivingJoystick.addButton(1, () -> ramp.setIdealSpeed(0.5), () -> ramp.setIdealSpeed(0));
		drivingJoystick.addButton(3, () -> ramp.setIdealSpeed(-0.5), () -> ramp.setIdealSpeed(0)); 

		climbingJoystick = new LambdaJoystick(CLIMBING_JOYSTICK_PORT, climber::updateSpeed);

	}

	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
	}

	public void teleopPeriodic() {
		climbingJoystick.listen();
		drivingJoystick.listen();
		ramp.updateSpeed();
	}

	public void testPeriodic() {
	}
}
