package edu.wpi.first.wpilibj.hal;

import java.util.HashMap;
import java.util.Map;

public class DIOJNI extends JNIWrapper {
	
	protected static Map<Integer, Boolean> dioValue = new HashMap<Integer, Boolean>();
	
	public static boolean checkDIOChannel(int dioPortHandle) {
		return (dioPortHandle > 0 && dioPortHandle <= PortsJNI.getNumDigitalChannels());
	}
	
	public static void freeDIOPort(int dioPortHandle) {
		dioValue.remove(dioPortHandle);
	}
	
	public static int initializeDIOPort(int dioPortHandle, boolean input) {
		return dioPortHandle;
	}
	
	public static boolean getDIO(int dioPortHandle) {
		return dioValue.get(dioPortHandle);
	}
	
	public static void setDIO(int dioPortHandle, short value) {
		dioValue.put(dioPortHandle, value == 1);
	}
}
