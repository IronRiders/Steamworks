package org.usfirst.frc.team4180.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	// PWM Port Constants
	public static final int LEFT_DRIVE_PORT = 0;
	public static final int RIGHT_DRIVE_PORT = 1;
	public static final int CLIMBER_PORT = 2;

	// Phneumatics ports
	public static final int SHIFTING_PORT_1 = 0;
	public static final int SHIFTING_PORT_2 = 1;
	public static final int RAMP_PORT = 3;
	public static final int RAMP_PORT2 = 2;

	// Joystick Port Constants
	private static final int DRIVING_JOYSTICK_PORT = 0;
	private static final int CLIMBING_JOYSTICK_PORT = 1;

	private LambdaJoystick climbingJoystick;
	private LambdaJoystick drivingJoystick;

	private DriveTrain driveTrain;
	private Climber climber;
	private Ramp ramp;

	private Autonomous auto;

	@Override
	public void robotInit() {

		ramp = new Ramp(RAMP_PORT, RAMP_PORT2);
		driveTrain = new DriveTrain(LEFT_DRIVE_PORT, RIGHT_DRIVE_PORT, SHIFTING_PORT_1, SHIFTING_PORT_2);
		climber = new Climber(CLIMBER_PORT);
		
		drivingJoystick = new LambdaJoystick(DRIVING_JOYSTICK_PORT, driveTrain::updateSpeed);
		drivingJoystick.addButton(2, driveTrain::toggleGearShifting);
		drivingJoystick.addButton(3, driveTrain::toggleBackwards);
		drivingJoystick.addButton(1, ramp::toggleRamp);
		
		climbingJoystick = new LambdaJoystick(CLIMBING_JOYSTICK_PORT, climber::acceptJoystickData);
		climbingJoystick.addButton(1, ramp::toggleRamp);
		setUpCameras();
	}

    private void setUpCameras() {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        CameraServer.getInstance().addAxisCamera("10.41.80.11");
        //camera.setResolution(640, 480);
    }

    @Override
    public void autonomousInit() {
        auto = new Autonomous(driveTrain, ramp);
    }

    @Override
    public void autonomousPeriodic() {
        auto.Periodic();
    }

    @Override
    public void teleopPeriodic() {
        drivingJoystick.listen();
        climbingJoystick.listen();
    }
}
