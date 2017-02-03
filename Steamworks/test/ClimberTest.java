
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.Climber;
import org.usfirst.frc.team4180.robot.DriveTrain;

@RunWith(PowerMockRunner.class)
public class ClimberTest extends RobotTest {
//	@Test
/*	public void testToggleBackward() {
		DriveTrain drivetrain = new DriveTrain(0, 1);
		
		Assert.assertEquals(false, drivetrain.getBackwards());
		drivetrain.toggleBackwards();
		Assert.assertEquals(true, drivetrain.getBackwards());
		drivetrain.setBackwards(true);
		Assert.assertEquals(true, drivetrain.getBackwards());
*/
	
	@Test
	public void testUpdateSpeed() {
		Climber climber  = new Climber(0, 1);// set this to the right port numbers
		
		
		resetTest();
		climber.updateSpeed(new double[] {0, 4, 0});
		Assert.assertEquals(4, climber.getSpeed(), 0);
		
		resetTest();
		climber.updateSpeed(new double[] {0, 2, 0});
		Assert.assertEquals(2, climber.getSpeed(), 0);
	}
	
}

	
	


