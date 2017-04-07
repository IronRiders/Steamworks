package org.usfirst.frc.team4180.robot;

import java.util.function.Consumer;

public class LambdaJoystick extends edu.wpi.first.wpilibj.Joystick {
    public Button[] buttons = new Button[11];
    private Consumer<ThrottlePosition> joystickListener;

    public LambdaJoystick(int port, Consumer<ThrottlePosition> joystickListener) {
        super(port);
        this.joystickListener = joystickListener;
    }

    //accounts for joy-stick error by rounding small numbers to 0
    public static double buffer(double d) {
        return (d > 0.03 || d < -0.03) ? d : 0;
    }

    public void addButton(int buttonNum, Runnable onKeyDown, Runnable onKeyUp) {
        buttons[buttonNum - 1] = new Button(onKeyDown, onKeyUp);
    }

    public void addButton(int buttonNum, Runnable onKeyDown) {
        addButton(buttonNum, onKeyDown, () -> {});
    }

    public void listen() {
        //Iterate through the array of buttons and do whatever they're asking
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i] != null) {
                buttons[i].listen(this.getRawButton(i + 1));
            }
        }

        joystickListener.accept(new ThrottlePosition(buffer(this.getX()), buffer(this.getY()), buffer(this.getZ())));
    }

    private class Button {
        public boolean currentState = false;
        public Runnable onKeyDown;
        public Runnable onKeyUp;

        public Button(Runnable onKeyDown, Runnable onKeyUp) {
            this.onKeyDown = onKeyDown;
            this.onKeyUp = onKeyUp;
        }

        public void listen(boolean newState) {
            //If the button has been toggled and it's down, run onKeyDown
            //If it's now up, run onKeyUp
            if (currentState != newState) {
                currentState = newState;
                if (newState) onKeyDown.run();
                else onKeyUp.run();
            }
        }
    }

    public static class ThrottlePosition {
        public double x, y, z;

        public ThrottlePosition(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public ThrottlePosition(double x, double y) {
            this(x, y, 0);
        }
    }
}
