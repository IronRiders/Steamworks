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
	double velocity;
	
	public Autonomous (BuiltInAccelerometer accelerometer, AnalogGyro gyro){
		this.accelerometer = accelerometer;
		this.gyro = gyro;
		this.gyro.calibrate();
		time = -1;
		velocity = 0.0;
	}
	
	
	public boolean Start(){
		try {
			int i = Integer.parseInt(SmartDashboard.getString("DB/String 5", "1"));
		}
		catch (Exception e){
			SmartDashboard.putString("DB/String 5", "Invalid Input :(");
			return false;
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
		
		double a = Math.sqrt(x*x + y*y);
		velocity += a * dt;
				
		time = currentTime;
	}
}
