import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.DriveTrain;
import org.usfirst.frc.team4180.robot.Ramp;

import edu.wpi.first.wpilibj.VictorSP;


@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void testOnUp() {
		testRamp(0.25, Ramp::onUp);
	}
	
	@Test
	public void testOnDown(){
		testRamp(-0.25, Ramp::onDown);
	}
	
	public void testRamp(double expectedSpeed, Consumer<Ramp> rampFunction){
		Ramp ramp = new Ramp(1,2,3,4,5);
		VictorSP victor = ramp.getVictor();
		
		// Positive test tests that the ramp speed is what it's supposed to be, 0.25
		setDigitalInputOnPortTo(2, false);
		rampFunction.accept(ramp);
		Assert.assertEquals(-0.25, victor.get(), 0);
		
		// Negative test tests that the ramp speed is not what it's not supposed to be, 0.25
		setDigitalInputOnPortTo(2, true);
		ramp.off();
		rampFunction.accept(ramp);
		Assert.assertNotEquals(-0.25, victor.get(), 0);
	}

	
}
