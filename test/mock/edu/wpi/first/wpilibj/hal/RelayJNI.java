package edu.wpi.first.wpilibj.hal;

public class RelayJNI extends DIOJNI {
    public static int initializeRelayPort(int halPortHandle, boolean forward) {
		return halPortHandle;
    }

    public static void freeRelayPort(int relayPortHandle) {
	}

    public static boolean checkRelayChannel(int channel) {
		return 0 <= channel && channel < PortsJNI.getNumRelayChannels();
	}

    public static void setRelay(int relayPortHandle, boolean on) {
	}

    public static boolean getRelay(int relayPortHandle) {
		return false;
	}
}
