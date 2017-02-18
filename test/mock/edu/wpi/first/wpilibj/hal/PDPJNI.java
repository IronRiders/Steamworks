package edu.wpi.first.wpilibj.hal;

@SuppressWarnings("AbbreviationAsWordInName")
public class PDPJNI extends JNIWrapper {
    public static void initializePDP(int module) {
	}

    public static boolean checkPDPModule(int module) {
		return 0 <= module && module < PortsJNI.getNumPDPModules();
	}

    public static boolean checkPDPChannel(int channel) {
    	return 0 <= channel && channel < PortsJNI.getNumPDPChannels();
	}

    public static double getPDPTemperature(int module) {
		return 0;
	}

    public static double getPDPVoltage(int module) {
		return 0;
	}

    public static double getPDPChannelCurrent(byte channel, int module) {
		return 0;
	}

    public static double getPDPTotalCurrent(int module) {
		return 0;
	}

    public static double getPDPTotalPower(int module) {
		return 0;
	}

    public static double getPDPTotalEnergy(int module) {
		return 0;
	}

    public static void resetPDPTotalEnergy(int module) {
	}

    public static void clearPDPStickyFaults(int module) {
	}
}