package edu.wpi.first.wpilibj.hal;

public class JNIWrapper {

  // This must produce a unique port for each input pair
  // Currently it concatenates the two bytes bits
  public static int getPortWithModule(byte module, byte channel) { return channel | (module << 8); }

  public static int getPort(byte channel) { return channel * 1; }
}