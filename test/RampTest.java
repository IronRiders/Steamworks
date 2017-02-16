import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;



import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team4180.robot.Ramp;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;


@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void testRamp(double expectedSpeed, int upDownOrOff, int portNumber){
		Ramp ramp = new Ramp(Robot.RAMP_PORT, Robot.RAMP_PORT2);
		DoubleSolenoid rampSolenoid = ramp.rampSolenoid;
		// Positive test tests that the ramp speed is what it's supposed to be, 0.25
		boolean b = rampSolenoid.get() == DoubleSolenoid.Value.kForward; // if it's kforward then it's true
		if(b){
			ramp.toggleRamp();
			Assert.assertEquals(rampSolenoid.get(), DoubleSolenoid.Value.kReverse);		
		}
		else{
			ramp.toggleRamp();
			Assert.assertEquals(rampSolenoid.get(), DoubleSolenoid.Value.kForward);			
		}		
	}
}