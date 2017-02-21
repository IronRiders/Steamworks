package org.usfirst.frc.team4180.robot;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Autonomous {
	KinematicLocator location;
	State state;
	Timer autoTime;
	DriveTrain drive;
	Ramp ramp;
	
	int startLocation;
	
	public Autonomous (KinematicLocator loc, DriveTrain drive, Ramp ramp){
		location = loc;
		state = State.Idle;
		autoTime = new Timer();
		this.drive = drive;
		this.drive.setBackwards(false);
		this.drive.setGear(true);
		this.ramp = ramp;
		ramp.set(DoubleSolenoid.Value.kReverse);
		String locationStr = SmartDashboard.getString("DB/String 5", "0");
		startLocation = Integer.parseInt(locationStr);
	}
	
	public void Periodic(){
		switch (state) {
		
		case Idle:
			autoTime.start();
			if(startLocation == 0){
				state = State.Done;
			}
			else{
				state = State.Driving;
			}
			break;
			
		case Driving:
			SmartDashboard.putString("DB/String 7", autoTime.get()+"");
			if(autoTime.get()>1){
				state = State.Done;
			}
			else if ((startLocation == 1 || startLocation == 3) && autoTime.get()>0.5){
				if(startLocation == 1){
					drive.updateSpeed(new double[]{0.5,0.5,0});
				}
				else{
					drive.updateSpeed(new double[]{-0.5,0.5,0});
				}
			}
			else{
				drive.updateSpeed(new double[]{0,-0.5,0});
			}
			break;
			
		case Done:
			drive.updateSpeed(new double[]{0,0,0});
			break;
		}
	}
	
	//State Machine
	public enum State {
		Idle, Driving, ErrorCorrecting, Done
	}
}
