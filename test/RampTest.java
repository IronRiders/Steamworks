import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;



import org.junit.Assert;
import org.junit.Test;
import org.usfirst.frc.team4180.robot.Ramp;
import org.usfirst.frc.team4180.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;


@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void test() {
		Ramp ramp = new Ramp(Robot.RAMP_PORT, Robot.RAMP_PORT2);
		ramp.toggleRamp();
//		Assert.assertEquals(Value.kForward, ramp.get());
		ramp.toggleRamp();
	//	Assert.assertEquals(Value.kReverse, ramp.get());
	}
}
