package org.usfirst.frc.team4180.robot;

import java.util.concurrent.locks.Lock;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class KinematicLocator {
 BuiltInAccelerometer accelerometer;
 ADXRS450_Gyro gyro;
 Timer t;
 double lastTime;
 double vx,vy;
 double x,y;
 double xError, yError;
 Thread locationThread;
 
 public KinematicLocator() {
	accelerometer = new BuiltInAccelerometer();
	gyro = new ADXRS450_Gyro();
	gyro.calibrate();
	lastTime = 0;
	vx = 0;
	vy = 0;
	x = 0;
	y = 0; 
	locationThread = new Thread(this::thread);
   	locationThread.start(); 	
}
 
 private void thread(){
	 t = new Timer();
	 t.start();
	 calibrateAccelerometer();
	 t.reset();
	 while (true) {
		 Run();
	}
 }
 
 private void calibrateAccelerometer(){
	 int reps = 40;
	 double interval = 0.05;
	 double totalXError = 0;
	 double totalYError = 0;
	 for(int i =0; i< reps; i++){
		 totalXError += accelerometer.getX();
		 totalYError += accelerometer.getY();
		 t.delay(interval);
	 }
	 xError = totalXError / reps;
	 yError = totalYError / reps;
 }
 
 public void Run(){
	 double currentTime = t.get();
	 double dt = currentTime - lastTime;
	 if(dt>0.1){
		 lastTime = currentTime;
		 return;
	 }
	 double ay = round(accelerometer.getY()-yError,2)*9.8;
	 double ax = round(accelerometer.getX()-xError,2)*9.8;
	 double angle =  Math.toRadians(gyro.getAngle());
	 double sin = Math.sin(angle);
	 double cos = Math.cos(angle);
	 vy += dt * (ay*cos + ax*sin);
	 vx += dt * (ax*cos + ay*sin);
	 x += vx * dt;
	 y += vy * dt;
	 lastTime = currentTime;
 }
 
 public static double round(double n, int places){
	 int pow = (int)Math.pow(10, places);
	 return ((double)Math.round(n*pow))/pow;
 }
 
 public double getX(){
	 return x;
 }
 
 public double getY(){
	 return y;
 }

 public double getAngle() {
	 return gyro.getAngle();
 }
 public double getXAcceleration(){
	 return round(accelerometer.getX()-xError,2)*9.8;
 }
 public double getYAcceleration() {
	 return round(accelerometer.getY()-yError,2)*9.8;
 }
 
}
