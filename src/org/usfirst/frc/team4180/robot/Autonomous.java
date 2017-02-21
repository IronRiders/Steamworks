package org.usfirst.frc.team4180.robot;


import org.objenesis.strategy.SerializingInstantiatorStrategy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Autonomous {
	KinematicLocator location;
	State state;
	Timer autoTime;
	
	int start;
	int gear;
	int baseline;
	
	public Autonomous (KinematicLocator loc){
		location = loc;
		state = State.Idle;
		autoTime = new Timer();
		
		String seq = SmartDashboard.getString("DB/String 5", "000");
		start = Integer.parseInt(seq.substring(0, 1));
		gear = Integer.parseInt(seq.substring(1, 2));
		baseline = Integer.parseInt(seq.substring(2));
	}
	
	public void Periodic(){
		switch (state) {
		case Idle:
			autoTime.start();
			if(start == 0) state = state.Done;
			break;
		}
	}
	
	//State Machine
	public enum State {
		Idle, Gears, Unloading, Baseline, Done
	}
}
