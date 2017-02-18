import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;



import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team4180.robot.Ramp;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void callRamp(){
		testRamp(DoubleSolenoid.Value.kForward, DoubleSolenoid.Value.kReverse);
		testRamp(DoubleSolenoid.Value.kReverse, DoubleSolenoid.Value.kForward);
		
		
	}
	
	public void testRamp(Value value, Value notValue){
		Ramp ramp = new Ramp(Robot.RAMP_PORT, Robot.RAMP_PORT2);
		DoubleSolenoid rampSolenoid = ramp.rampSolenoid;
		rampSolenoid.set(notValue);
		ramp.toggleRamp();
		Assert.assertEquals(rampSolenoid.get(), value);
				
	}	
						
}
