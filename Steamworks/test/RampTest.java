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
import static org.usfirst.frc.team4180.robot.Robot.*;

@RunWith(PowerMockRunner.class)

public class RampTest extends RobotTest {
	@Test
	public void testUp() {
		testRamp(0.5, 1, UP_SWITCH_PORT );
	}
	
	@Test
	public void testDown(){
		testRamp(-0.5, 2, DOWN_SWITCH_PORT);
	}
	@Test
	public void testStop(){
		testRamp(0,3, UP_SWITCH_PORT );
	}
	
	public void testRamp(double expectedSpeed, int upDownOrOff, int portNumber){
		Ramp ramp = new Ramp(RAMP_PORT, UP_SWITCH_PORT, DOWN_SWITCH_PORT, LEFT_CHECK_PORT, RIGHT_CHECK_PORT);
		Spark spark = ramp.getSpark();
		
		// Positive test tests that the ramp speed is what it's supposed to be, 0.25
		setDigitalInputOnPortTo(portNumber, false);
		if(upDownOrOff == 1) {			
			ramp.up();
		}
		else if(upDownOrOff == 2){
			ramp.down();
		}
		else if(upDownOrOff == 3) {
			ramp.stop(); 
		}
		else{
			Assert.fail();	
		}
		ramp.updateSpeed();
		Assert.assertEquals(expectedSpeed, spark.get(), 0);
	}	
}
