import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.DriveTrain;
import org.usfirst.frc.team4180.robot.Ramp;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import static org.usfirst.frc.team4180.robot.Robot.*;

@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void testOnUp() {
		testRamp(0.5, true,UP_SWITCH_PORT );
	}
	
	@Test
	public void testOnDown(){
		testRamp(-0.5, false, DOWN_SWITCH_PORT);
	}
	
	public void testRamp(double expectedSpeed, boolean up, int portNumber){
		Ramp ramp = new Ramp(RAMP_PORT, UP_SWITCH_PORT, DOWN_SWITCH_PORT, LEFT_CHECK_PORT, RIGHT_CHECK_PORT);
		Spark spark = ramp.getSpark();
		
		// Positive test tests that the ramp speed is what it's supposed to be, 0.25
		if(up){
			setDigitalInputOnPortTo(portNumber, false);
			ramp.up();
			Assert.assertEquals(expectedSpeed, spark.get(), 0);
		}
		else{
			setDigitalInputOnPortTo(portNumber, true);
			ramp.stop();
			ramp.down();
			Assert.assertNotEquals(expectedSpeed, spark.get(), 0);
			
			
		}
	}	
}
