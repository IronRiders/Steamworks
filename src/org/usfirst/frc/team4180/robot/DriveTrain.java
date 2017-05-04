package org.usfirst.frc.team4180.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
    private VictorSP leftVictor, rightVictor;
    private boolean backwards = false;
    private DoubleSolenoid gearShifting;
    private boolean state = false;

    public DriveTrain(int leftPort, int rightPort, int gearShiftPort1, int gearShiftPort2) {
        leftVictor = new VictorSP(leftPort);
        rightVictor = new VictorSP(rightPort);
        gearShifting = new DoubleSolenoid(gearShiftPort1, gearShiftPort2);
    }

    // This method takes the position of the joystick, and moves the robot accordingly.
    public void updateSpeed(LambdaJoystick.ThrottlePosition throttlePosition) {
        double x = throttlePosition.x;
        double y = throttlePosition.y;
        if (backwards) {
            leftVictor.set(-y - x);
            rightVictor.set(y - x);
        } else {
            leftVictor.set(y - x);
            rightVictor.set(-y - x);
        }
    }

    public void toggleGearShifting() {
        setGear(!state);
    }

    //shifts the boolean
    public void setGear(boolean b) {
        state = b;
        if (state) {
            SmartDashboard.putString("DB/String 8", "Fast");
            gearShifting.set(DoubleSolenoid.Value.kReverse);

        } else {
            SmartDashboard.putString("DB/String 8", "Slow");
            gearShifting.set(DoubleSolenoid.Value.kForward);
        }
    }

    //set backwards to notbackwards
    public void toggleBackwards() {
        backwards = !backwards;
    }

    //the boolean goes backwards
    public void setBackwards(boolean value) {
        backwards = value;
    }

    //when we win it goes left?
    public VictorSP getLeftVictor() {
        return leftVictor;
    }

    //when we win it goes left?
    public VictorSP getRightVictor() {
        return rightVictor;
    }
}