import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import edu.wpi.first.wpilibj.Timer;

@PrepareForTest({Timer.class})
public abstract class RobotTest {
	
	@Before
	public void setup() throws Exception {
		PowerMockito.mockStatic(Timer.class);
		when(Timer.getFPGATimestamp()).thenReturn(0.0);
	}
	
	@After
	public void resetTest() {
	}
	
}
