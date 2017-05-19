package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber {
    private VictorSP ClimbVictor;

    public Climber(int VictorPort1) {
        ClimbVictor = new VictorSP(VictorPort1);
    }

    public void updateSpeed(double speed) {
        ClimbVictor.set(speed);
    }

    public void acceptJoystickData(LambdaJoystick.ThrottlePosition position) {
        double speed = (position.z + 1) / 2;
        SmartDashboard.putString("DB/String 9", "" + speed);
        updateSpeed(speed);
    }
}