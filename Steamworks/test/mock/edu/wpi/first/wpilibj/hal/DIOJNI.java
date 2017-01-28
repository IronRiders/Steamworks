package edu.wpi.first.wpilibj.hal;

import java.util.ArrayList;

public class DIOJNI extends JNIWrapper {
	
	protected static ArrayList<Boolean> dioValue = new ArrayList<Boolean>();
	
	public static boolean checkDIOChannel(int dioPortHandle) {
		return (dioPortHandle < 0 || dioPortHandle > PortsJNI.getNumDigitalChannels());
	}
	
	public static void freeDIOPort(int dioPortHandle) {
		dioValue.set(dioPortHandle, null);
	}
	
	public static int initializeDIOPort(int dioPortHandle, boolean input) {
		return dioPortHandle;
	}
	
	public static boolean getDIO(int dioPortHandle) {
		return dioValue.get(dioPortHandle);
	}
	
	public static void setDIO(int dioPortHandle, short value) {
		dioValue.set(dioPortHandle, value == 1);
	}
}
