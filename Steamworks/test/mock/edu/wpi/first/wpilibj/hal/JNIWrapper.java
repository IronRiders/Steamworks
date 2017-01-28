package edu.wpi.first.wpilibj.hal;

public class JNIWrapper {

  public static int getPortWithModule(byte module, byte channel) { return channel * module; }

  public static int getPort(byte channel) { return channel * 1; }
}