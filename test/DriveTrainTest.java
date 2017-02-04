import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.DriveTrain;


@RunWith(PowerMockRunner.class)
public class DriveTrainTest extends RobotTest {

	@Test
	public void test() {
		DriveTrain drivetrain = new DriveTrain(0, 1);
		
		Assert.assertEquals(false, drivetrain.getBackwards());
		drivetrain.toggleBackwards();
		Assert.assertEquals(true, drivetrain.getBackwards());
		drivetrain.setBackwards(true);
		Assert.assertEquals(true, drivetrain.getBackwards());
	}
	
	@Test
	public void testUpdateSpeed() {
		DriveTrain drivetrain = new DriveTrain(0, 1);
		drivetrain.setBackwards(false);
		
		resetTest();
		drivetrain.updateSpeed(new double[] {0, 0, 0});
		Assert.assertEquals(0, drivetrain.getLeftVictor().getSpeed(), 0);
		Assert.assertEquals(0, drivetrain.getRightVictor().getSpeed(), 0);
		
		resetTest();
		drivetrain.updateSpeed(new double[] {1, 2, 0});
		Assert.assertEquals(0.5, drivetrain.getLeftVictor().getSpeed(), 0);
		Assert.assertEquals(-1.5, drivetrain.getRightVictor().getSpeed(), 0);
	}
	
}
