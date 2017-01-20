import org.junit.Test;
import org.usfirst.frc.team4180.robot.DriveTrain;

import junit.framework.Assert;



public class DriveTrainTest {

	@Test
	public void testToggleBackwards() {
		DriveTrain drivetrain = new DriveTrain(0, 0);
		
		Assert.assertEquals(false, drivetrain.getBackwards());
		drivetrain.toggleBackwards();
		Assert.assertEquals(true, drivetrain.getBackwards());
		drivetrain.setBackwards(true);
		Assert.assertEquals(true, drivetrain.getBackwards());
	}
	
}
