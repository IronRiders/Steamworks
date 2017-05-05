package org.usfirst.frc.team4180.robot;

import java.util.function.Consumer;

public class LambdaJoystick extends edu.wpi.first.wpilibj.Joystick {
    public Button[] buttons = new Button[11];
    private Consumer<ThrottlePosition> joystickListener;

    /**
     * Create a new Joystick Listener using lambdas given the USB port and the throttle listener
     * @param port USB port on the computer that the joystick is plugged in.
     * @param joystickListener Consumer of the Joystick's throttle position (x, y, and z).
     */
    public LambdaJoystick(int port, Consumer<ThrottlePosition> joystickListener) {
        super(port);
        this.joystickListener = joystickListener;
    }

    /**
     * Accounts for joy-stick error by rounding small numbers to 0.
     * @param d A joystick value from -1 to 1.
     * @return If d is within 0.03 of 0 d is rounded down to 0.
     */
    private static double buffer(double d) {
        return (d > 0.03 || d < -0.03) ? d : 0;
    }

    /**
     * Create a new button listener to run when a button on the joystick is pressed or released.
     * @param buttonNum The number of the button written on the joystick (1 - 10).
     * @param onKeyDown Action to be executed when the button is pressed.
     * @param onKeyUp Action to be executed when the button is released.
     */
    public void addButton(int buttonNum, Runnable onKeyDown, Runnable onKeyUp) {
        buttons[buttonNum - 1] = new Button(onKeyDown, onKeyUp);
    }

    /**
     * Create a new button listener to run when a button on the joystick is pressed.
     * @param buttonNum The number of the button written on the joystick (1 - 10).
     * @param onKeyDown Action to be executed when the button is pressed.
     */
    public void addButton(int buttonNum, Runnable onKeyDown) {
        addButton(buttonNum, onKeyDown, () -> {});
    }

    /**
     * Listens to buttons and the throttle and calls action listeners
     */
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

        /**
         * Create a new Button listener given a Runnable for when the button is pressed or released.
         * @param onKeyDown Runnable for when the button is pressed down.
         * @param onKeyUp Runnable for when the button is released.
         */
        public Button(Runnable onKeyDown, Runnable onKeyUp) {
            this.onKeyDown = onKeyDown;
            this.onKeyUp = onKeyUp;
        }

        /**
         * Given the value of the button, the listener calls the appropriate runnable if it has changed.
         * @param newState The current state of the button.
         */
        public void listen(boolean newState) {
            if (currentState != newState) {
                currentState = newState;
                if (newState) {
                    onKeyDown.run();
                } else {
                    onKeyUp.run();
                }
            }
        }
    }

    public static class ThrottlePosition {
        public double x, y, z;

        /**
         * Create a Throttle position given a (x, y) position of the throttle and the position of the dial (z).
         * @param x The right left position of the joystick (1 to -1).
         * @param y The vertical position of the joystick, 1 forwards, -1 is backwards (1 to -1).
         * @param z Position of the dial on the joystick (1 to -1).
         */
        public ThrottlePosition(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * Create a Throttle position given a (x, y) position of the joystick throttle.
         * @param x The right left position of the joystick (1 to -1).
         * @param y The vertical position of the joystick, 1 forwards, -1 is backwards (1 to -1).
         */
        public ThrottlePosition(double x, double y) {
            this(x, y, 0);
        }
    }
}