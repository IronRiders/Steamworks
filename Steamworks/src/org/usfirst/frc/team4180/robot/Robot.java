package org.usfirst.frc.team4180.robot;

import java.io.IOException;
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
	private DriveTrain driveTrain;
	private LambdaJoystick drivingJoystick;
	private Climber climber;
	private LambdaJoystick climbingJoystick;
	private Ramp ramp;
	private Thread cameraThread;
	
	private Autonomous auto;
	
	public void robotInit() {
		try {
			PropertyReader propertyReader = new PropertyReader("ports.properties");
			
			ramp = new Ramp(propertyReader.get("RAMP_PORT"), propertyReader.get("UP_SWITCH_PORT"), propertyReader.get("DOWN_SWITCH_PORT"), propertyReader.get("LEFT_CHECK_PORT"), propertyReader.get("RIGHT_CHECK_PORT"));
			driveTrain = new DriveTrain(propertyReader.get("LEFT_DRIVETRAIN_PORT"), propertyReader.get("RIGHT_DRIVETRAIN_PORT"));
			climber = new Climber(propertyReader.get("CLIMBER_PORT"), propertyReader.get("TOP_SWITCH_PORT"));

			drivingJoystick = new LambdaJoystick(propertyReader.get("DRIVING_JOYSTICK_PORT"), driveTrain::updateSpeed);

			drivingJoystick.addButton(6, driveTrain::toggleBackwards, () -> {});
			drivingJoystick.addButton(1, () -> ramp.up(), () -> ramp.stop());
			drivingJoystick.addButton(3, () -> ramp.down(), () -> ramp.stop()); 

			climbingJoystick = new LambdaJoystick(propertyReader.get("CLIMBING_JOYSTICK_PORT"), climber::updateSpeed);
		} catch (IOException e) {
			throw new IllegalStateException("Error while finding properties file " + e);
		} catch (IllegalArgumentException e) {
			throw new IllegalStateException("Error while parsing port" + e);
		}
		
		
		//autonomous stuff
		SmartDashboard.putString("DB/String 0", "Autonomous Mode (1-3) ------->");
		SmartDashboard.putString("DB/String 5", "1");
		
		auto = new Autonomous(new BuiltInAccelerometer(), new AnalogGyro(0));
		
		
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
		ramp.updateSpeed();
	}

	public void testPeriodic() {
	}
}
