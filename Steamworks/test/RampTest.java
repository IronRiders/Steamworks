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

import static org.usfirst.frc.team4180.robot.Robot.*;

@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void testOnUp() {
		testRamp(0.25, Ramp::onUp, LEFT_CHECK_PORT);
	}
	
	@Test
	public void testOnDown(){
		testRamp(-0.25, Ramp::onDown, RIGHT_CHECK_PORT);
	}
	
	public void testRamp(double expectedSpeed, Consumer<Ramp> rampFunction, int portNumber){
		Ramp ramp = new Ramp(RAMP_PORT, UP_SWITCH_PORT, DOWN_SWITCH_PORT, LEFT_CHECK_PORT, RIGHT_CHECK_PORT);
		VictorSP victor = ramp.getVictor();
		
		// Positive test tests that the ramp speed is what it's supposed to be, 0.25
		setDigitalInputOnPortTo(portNumber, false);
		rampFunction.accept(ramp);
		Assert.assertEquals(expectedSpeed, victor.get(), 0);
		
		// Negative test tests that the ramp speed is not what it's not supposed to be, 0.25
		setDigitalInputOnPortTo(portNumber, true);
		ramp.off();
		rampFunction.accept(ramp);
		Assert.assertNotEquals(expectedSpeed, victor.get(), 0);
	}

	
}
