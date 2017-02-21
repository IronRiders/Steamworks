
import org.usfirst.frc.team4180.robot.Climber;
import org.usfirst.frc.team4180.robot.DriveTrain;
import org.usfirst.frc.team4180.robot.Ramp;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class StressTests {
	Timer timer = new Timer();
	public void climberTest(int seconds , int interval) {
		while((int)timer.get()< seconds){
			if(timer.get() >= interval*3){
				climber.updateSpeed(new double [] {0.0,0.6,0.0});		
			}
			else if(timer.get() >= interval*2){
				climber.updateSpeed(new double [] {0.0 ,0.8, 0.0});
				
			}
			else if(timer.get() >= interval){
				climber.updateSpeed(new double [] {0.0, 0.4, 0.0});
				
			}
			else{
				climber.updateSpeed(new double[]{0.0,0.2,0.0});
						
			}	
		}
	}
	public void driveTrainTest(int seconds , int interval) {
		while((int)timer.get()< seconds){	
			if(timer.get() >= interval*3){
				drivetrain.updateSpeed(new double [] {-1.0, 0.0 ,0.0});		
			}
			else if(timer.get() >= interval*2){
				drivetrain.updateSpeed(new double [] {1.0 ,0.0, 0.0});
			
			}
			else if(timer.get() >= interval){
				drivetrain.updateSpeed(new double [] {0.0, -1.0, 0.0});
			
			}
			else{
				drivetrain.updateSpeed(new double[]{0.0,1.0,0.0});
				
			}
		}		
	}
		

	public void driveTrainCircleTest(int seconds){
		while((int)timer.get()< seconds){
			if(timer.get() >= interval*3){
				drivetrain.updateSpeed(new double [] {-1.0, 1.0 ,0.0});		
			}
			else if(timer.get() >= interval*2){
				drivetrain.updateSpeed(new double [] {1.0 ,-1.0, 0.0});
				
			}
			else if(timer.get() >= interval){
				drivetrain.updateSpeed(new double [] {0.5, -0.5, 0.0});
				
			}
			else{
				drivetrain.updateSpeed(new double[]{-0.5,0.5,0.0});
			}
						
		}	
	}
	
	public void rampTest(int seconds , int interval) {
		while(timer.get() < seconds){
			if(timer.get() >= interval){
				ramp.toggleRamp();
			}	
		}
	}	
}

