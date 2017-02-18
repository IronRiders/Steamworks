package org.usfirst.frc.team4180.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {
	// DIO Port Constants
	public static final int TOP_SWITCH_PORT = 5;

	// PWM Port Constants
	public static final int LEFT_DRIVETRAIN_PORT = 0;
	public static final int RIGHT_DRIVETRAIN_PORT = 1;
	public static final int CLIMBER_PORT = 5;

	//Phneumatics ports
	public static final int SHIFTING_PORT_1 = 0;
	public static final int SHIFTING_PORT_2 = 1;
	public static final int RAMP_PORT = 3;
	public static final int RAMP_PORT2 = 2;
	
	// Joystick Port Constants
	public static final int DRIVING_JOYSTICK_PORT = 0;
	public static final int CLIMBING_JOYSTICK_PORT = 1;
	
	private LambdaJoystick climbingJoystick;
	private LambdaJoystick drivingJoystick;
	
	private DriveTrain driveTrain;
	private Climber climber;
	private Ramp ramp;
	
	private Thread cameraThread;
	private KinematicLocator locationSensor;
	
	private Autonomous auto;
	
	public void robotInit() {

		ramp = new Ramp(RAMP_PORT, RAMP_PORT2);
		driveTrain = new DriveTrain(LEFT_DRIVETRAIN_PORT, RIGHT_DRIVETRAIN_PORT, SHIFTING_PORT_1, SHIFTING_PORT_2);
		climber = new Climber(CLIMBER_PORT, TOP_SWITCH_PORT);
		locationSensor = new KinematicLocator();
		
		drivingJoystick = new LambdaJoystick(DRIVING_JOYSTICK_PORT, driveTrain::updateSpeed);
		drivingJoystick.addButton(3, ramp::rampOff, () -> {});
		drivingJoystick.addButton(2, () -> driveTrain.toggleGearShifting(), () -> {});
		drivingJoystick.addButton(1, ramp::toggleRamp, () -> {});
		
		climbingJoystick = new LambdaJoystick(CLIMBING_JOYSTICK_PORT, climber::updateSpeed);
		
		cameraThread = new Thread(this::thread);
    	cameraThread.start();
    	
	}
	
	public void thread()
	{
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
        
        CvSink cvSink = CameraServer.getInstance().getVideo();
        CvSource outputStream = CameraServer.getInstance().putVideo("DoesThisMatter", 640, 480);
        
        Mat mat = new Mat();
        GripPipeline pipeline = new GripPipeline();
        
        while(!Thread.interrupted()) {
            cvSink.grabFrame(mat);       
            pipeline.process(mat);
            
            ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();

            Imgproc.putText(mat, "contours: " + contours.size(), new Point(64, 64), Core.FONT_HERSHEY_SIMPLEX, 2.0, new Scalar(0.0, 0.0, 255.0, 255.0));
            for (int i = 0; i < contours.size(); ++i) {
            	Rect r = Imgproc.boundingRect(contours.get(i));
            	Imgproc.rectangle(mat, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(0.0, 0.0, 255.0, 255), 2);
            }

            outputStream.putFrame(mat);
        }
	}

	public void autonomousInit() {
		auto.Start();
	}

	public void autonomousPeriodic() {
		auto.Periodic();
	}

	public void teleopPeriodic() {
		climbingJoystick.listen();
		drivingJoystick.listen();
	}
}
