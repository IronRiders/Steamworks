

import org.usfirst.frc.team4180.robot.Ramp;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.Timer;

public class RampStressTest extends StressTest{

	public void testRamp(){
		//pulling from Ramp class
		Ramp ramp = new Ramp(Robot.RAMP_PORT, Robot.UP_SWITCH_PORT, Robot.DOWN_SWITCH_PORT, Robot.LEFT_CHECK_PORT, Robot.RIGHT_CHECK_PORT);
		//pulling from Timer class
		Timer timer = new Timer();
		timer.start();
		//for a duration of 2 minutes or 120 seconds the ramp will move up and down continuously
		while(timer.get() <= 120){ //seconds
			if (ramp.getIdealSpeed() <= 0 && !ramp.downSwitchCheck()) {//if the ramp is up, move it down
				ramp.getSpark().set(ramp.getIdealSpeed());
			}
			else if (ramp.getIdealSpeed() >= 0 && !ramp.upSwitchCheck()) {//if the ramp is down, move it up
				ramp.getSpark().set(ramp.getIdealSpeed());
			}
		}
	}

}