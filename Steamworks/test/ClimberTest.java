import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.Climber;


@RunWith(PowerMockRunner.class)
public class ClimberTest extends RobotTest {
	public static final int CLIMBER_PORT = 3;
	public static final int TOP_SWITCH_PORT = 5;
	Climber climber  = new Climber(CLIMBER_PORT, TOP_SWITCH_PORT);// set this to the right port numbers
	@Test
	public void testUpdateSpeed() {		
		resetTest();
		climber.updateSpeed(new double[] {0, 4, 0});
		Assert.assertEquals(4, climber.getSpeed(), 0);
		
		resetTest();
		climber.updateSpeed(new double[] {0, 2, 0});
		Assert.assertEquals(2, climber.getSpeed(), 0);
	}
	@Test
	public void testAtTop(){
		setDigitalInputOnPortTo(TOP_SWITCH_PORT, false);
		Assert.assertEquals(climber.atTop(), false);
		setDigitalInputOnPortTo(TOP_SWITCH_PORT, true);
		Assert.assertEquals(climber.atTop(), true);
		
	}

}
	


	
	


