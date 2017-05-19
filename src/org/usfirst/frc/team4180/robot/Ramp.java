package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Ramp {
    private DoubleSolenoid rampSolenoid;

    public Ramp(int solenoidPort1, int solenoidPort2) {
        rampSolenoid = new DoubleSolenoid(solenoidPort1, solenoidPort2);
        rampSolenoid.set(Value.kReverse);
    }

    public void toggleRamp() {
        rampSolenoid.set(rampSolenoid.get() == Value.kReverse ? Value.kForward : Value.kReverse);
    }

    public void set(Value value) {
        rampSolenoid.set(value);
    }
}