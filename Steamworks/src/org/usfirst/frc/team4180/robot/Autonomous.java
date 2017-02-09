package org.usfirst.frc.team4180.robot;

import java.util.Timer;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Autonomous {
	
	
	BuiltInAccelerometer accelerometer;
	AnalogGyro gyro;
	long time;
	double vx;
	double vy;
	double distance;
	int start;
	int target;
	int heading;
	
	public Autonomous (BuiltInAccelerometer accelerometer, AnalogGyro gyro){
		this.accelerometer = accelerometer;
		this.gyro = gyro;
		this.gyro.calibrate();
		time = -1;
		vx = 0.0;
		vy = 0.0;
		distance = 0.0;
		start = 2;
		target = 2;
	}
	
	
	public boolean Start(){
		gyro.reset();
		try {
			int i = Integer.parseInt(SmartDashboard.getString("DB/String 5", "2"));
		}
		catch (Exception e){
			SmartDashboard.putString("DB/String 5", "Invalid Input :(");
			return false;
		}
		try {
			int i = Integer.parseInt(SmartDashboard.getString("DB/String 6", "2"));
		}
		catch (Exception e){
			SmartDashboard.putString("DB/String 6", "Invalid Input :(");
			return false;
		}
		start = Integer.parseInt(SmartDashboard.getString("DB/String 5", "2"));
		target = Integer.parseInt(SmartDashboard.getString("DB/String 6", "2"));
		
		switch(3*start + target - 3) {
		case 1: // start 1, target 1
			heading = 0; //or whatever
			break;
		case 2: // start 1, target 2
			heading = 60; 
			break;
		case 3: // start 1, target 3
			break;
		case 4: // start 2, target 1
			break;
		case 5: // start 2, target 2
			break;
		case 6: // ..etc
			break;
		case 7:
			break;
		case 8:
			break;
		case 9:
			break;		
		}
		
		return true;
	}
	
	public void Periodic(){
		double x = accelerometer.getX() * 9.8;
		double y = accelerometer.getY() * 9.8;
		long currentTime = System.currentTimeMillis();
		
		if (time < 0) {
			time = currentTime;
		}
		
		double dt = (currentTime - time) / 1000.0;
		
		vx += x * dt;
		vy += y * dt;
		
		distance += Math.sqrt((vx * vx) + (vy * vy)) * dt;
				
		time = currentTime;
		
		double angle = gyro.getAngle() % 360;
		if(angle < heading)
		{
			// robot turn
		} else if(angle > heading) {
			// robot turn, other direction
				
		}
		
	}
}
