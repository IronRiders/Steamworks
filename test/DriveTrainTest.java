import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.usfirst.frc.team4180.robot.DriveTrain;
import org.usfirst.frc.team4180.robot.Robot;


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
	public void callUpdateSpeed(){
		testUpdateSpeed(false);
		testUpdateSpeed(true);
	}
	
	public void testUpdateSpeed(boolean backwards) {
		final DriveTrain drivetrain = new DriveTrain(Robot.LEFT_DRIVETRAIN_PORT, Robot.RIGHT_DRIVETRAIN_PORT);
		
		final double[][] inputs = {
				{0,0,0}, // not moving
				{1,0,0},//full turn right
				{0,1,0},//forward
				{-1,0,0},
				{0,-1,0}
				
		};
		
		final double[][] outputs = {
				{0,0}, //both motors should be off
				{-1,-1},//right turn
				{1,-1},
				{1,1},
				{-1,1}
		};
		drivetrain.setBackwards(backwards);
		for(int i = 0 ; i < inputs.length ; i++){
			double[] input = inputs[i];
			double[] output = outputs[i];
			
			resetTest();
			drivetrain.updateSpeed(input); 
			
			double leftSpeed = drivetrain.getLeftVictor().getSpeed();
			double rightSpeed = drivetrain.getRightVictor().getSpeed();
			double expectedRightSpeed = backwards ? output[0] : output[0];
			double expectedLeftSpeed = backwards ? output[1] : output[1];
			
			System.out.printf(
					"Backwards? %s Input: %s, Expected: %s %s, got: %s %s\n",
					backwards, Arrays.toString(input), expectedLeftSpeed, expectedRightSpeed, leftSpeed, rightSpeed);
			
			Assert.assertEquals(leftSpeed, expectedLeftSpeed, 0);
			Assert.assertEquals(rightSpeed, expectedRightSpeed, 0);
			
			
		}
		
		/*final int back = backwards ? 1 : -1;
		drivetrain.setBackwards(backwards);
		
		resetTest();
		drivetrain.updateSpeed(new double[] {0, 0, 0});
		Assert.assertEquals(0, drivetrain.getLeftVictor().getSpeed(), 0);
		Assert.assertEquals(0, drivetrain.getRightVictor().getSpeed(), 0);
		
		resetTest();
		drivetrain.updateSpeed(new double[] {1, 1, 0});
		Assert.assertEquals(-1 * back, drivetrain.getLeftVictor().getSpeed(), 0);
		Assert.assertEquals(3 * back, drivetrain.getRightVictor().getSpeed(), 0);*/
		
		
	}
	
}
