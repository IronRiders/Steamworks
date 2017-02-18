package edu.wpi.first.wpilibj.hal;

import java.util.HashMap;
import java.util.Map;

public class SolenoidJNI extends JNIWrapper {
	protected static Map<Integer, Boolean> solenoidValue = new HashMap<>();
	
    public static int initializeSolenoidPort(int halPortHandle) {
    	solenoidValue.put(halPortHandle, false);
		return halPortHandle;
	}

    public static boolean checkSolenoidModule(int module) {
		return true;
	}

    public static boolean checkSolenoidChannel(int channel) {
    	return (channel >= 0 && channel < PortsJNI.getNumSolenoidChannels());
	}

    public static void freeSolenoidPort(int portHandle) {
    	solenoidValue.remove(portHandle);
	}

    public static void setSolenoid(int portHandle, boolean on) {
    	solenoidValue.put(portHandle, on);
	}

    public static boolean getSolenoid(int portHandle) {
    	return solenoidValue.getOrDefault(portHandle, false);
	}

    public static byte getAllSolenoids(byte module) {
		byte ret = 0;
		for(int i = 0; i < PortsJNI.getNumSolenoidChannels(); i++) {
			boolean val = solenoidValue.getOrDefault(i, false);
			if(val) {
				// Sets the ith bit of ret to true
				ret = (byte) (ret | (1 << i));
			}
		}
		return ret;
	}

    public static int getPCMSolenoidBlackList(byte module) {
		return 0; // Todo: Might have to be 0xFF
	}

    public static boolean getPCMSolenoidVoltageStickyFault(byte module) {
		return false;
	}

    public static boolean getPCMSolenoidVoltageFault(byte module) {
		return false;
	}

    public static void clearAllPCMStickyFaults(byte module) {
	}
}
