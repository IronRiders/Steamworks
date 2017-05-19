import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.Climber;
import org.usfirst.frc.team4180.robot.DriveTrain;

import static org.usfirst.frc.team4180.robot.Robot.*;

@RunWith(PowerMockRunner.class)
public class ClimberTest extends RobotTest {

	@Test
	public void testToggleBackward() {

    }/*
		DriveTrain drivetrain = new DriveTrain(0, 1);
		
		Assert.assertEquals(false, drivetrain.getBackwards());
		drivetrain.toggleBackwards();
		Assert.assertEquals(true, drivetrain.getBackwards());
		drivetrain.setBackwards(true);
		Assert.assertEquals(true, drivetrain.getBackwards());

	
	/*@Test
	public void testUpdateSpeed() {
		Climber climber = new Climber(CLIMBER_PORT, TOP_SWITCH_PORT);
		
		setDigitalInputOnPortTo(TOP_SWITCH_PORT, false);
		
		resetTest();
		climber.updateSpeed(new double[] {0, 4, 0});
		Assert.assertEquals(4, climber.getSpeed(), 0);
		
		resetTest();
		climber.updateSpeed(new double[] {0, 2, 0});
		Assert.assertEquals(2, climber.getSpeed(), 0);
	}
	
	@Test
	public void testAtTop(){
		Climber climber  = new Climber(CLIMBER_PORT, TOP_SWITCH_PORT);
		
		setDigitalInputOnPortTo(TOP_SWITCH_PORT, false);
		Assert.assertEquals(climber.atTop(), false);
		
		setDigitalInputOnPortTo(TOP_SWITCH_PORT, true);
		Assert.assertEquals(climber.atTop(), true);	
	}*/	
}
