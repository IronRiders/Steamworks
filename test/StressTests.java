
import org.usfirst.frc.team4180.robot.Climber;
import org.usfirst.frc.team4180.robot.DriveTrain;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class StressTests {
	Timer timer = new Timer();
	final int seconds = 60;
	public void climberTest(int seconds) throws InterruptedException {
		Climber climber = new Climber(Robot.CLIMBER_PORT, Robot.TOP_SWITCH_PORT);	
		timer.start();
		while((int)timer.get()< seconds){
			climber.updateSpeed(new double[]{0.0,0.2,0.0});
			Thread.sleep(seconds/6 * 1000);
			climber.updateSpeed(new double [] {0.0, 0.6, 0.0});
			Thread.sleep(seconds/6 * 1000);
			climber.updateSpeed(new double [] {0.0 ,0.8, 0.0});
			Thread.sleep(seconds/6 * 1000);
			climber.updateSpeed(new double [] {0.0, 0.6 ,0.0});	
		
		}	
	}
	public void driveTrainTest(int seconds) throws InterruptedException{	
		while((int)timer.get()< seconds){
			DriveTrain drivetrain = new DriveTrain(Robot.LEFT_DRIVETRAIN_PORT, Robot.RIGHT_DRIVETRAIN_PORT, Robot.SHIFTING_PORT_1 , Robot.SHIFTING_PORT_2);
			drivetrain.updateSpeed(new double[]{0.0,1.0,0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {0.0, -1.0, 0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {1.0 ,0.0, 0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {-1.0, 0.0 ,0.0});			
		}
	}	

	public void driveTrainCircleTest(int seconds) throws InterruptedException{
		while((int)timer.get()< seconds){
			DriveTrain drivetrain = new DriveTrain(Robot.LEFT_DRIVETRAIN_PORT, Robot.RIGHT_DRIVETRAIN_PORT, Robot.SHIFTING_PORT_1 , Robot.SHIFTING_PORT_2);
			drivetrain.updateSpeed(new double[]{-1.0,1.0,0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {1.0, -1.0, 0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {0.5 ,-0.5, 0.0});
			Thread.sleep(seconds/6 * 1000);
			drivetrain.updateSpeed(new double [] {-0.5, 0.5 ,0.0});			
		}
		
		
	}

	
	
}

