package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class GamepadRumbleTest extends OpMode {

    boolean wasA, isA;

    @Override
    public void init() {
        gamepad1.rumble(500);
    }

    @Override
    public void loop() {
        isA = gamepad1.a;
        if (isA && !wasA) {
            gamepad1.rumble(1.0, 0, 250);
        }
        wasA = isA;
    }
}
