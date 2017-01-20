import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;

@PrepareForTest({Timer.class, VictorSP.class})
public abstract class RobotTest {

	@Mock private VictorSP mockVictorSP;
	
	@Before
	public void setup() throws Exception {
		mockWpiLibJ();
	}
	
	private void mockWpiLibJ() throws Exception {
		mockVictorSP = mock(VictorSP.class);	
		PowerMockito.whenNew(VictorSP.class).withAnyArguments().thenReturn(mockVictorSP);
		
		PowerMockito.mockStatic(Timer.class);
		when(Timer.getFPGATimestamp()).thenReturn(0.0);
	}
	
	@After
	public void resetTest() {
	}
	
}
