package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeSummerTest extends OpMode {
    private DcMotor intakeMotor;

    @Override
    public void init() {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        if (gamepad2.x) {
            intakeMotor.setPower(1); //On
        } else if (gamepad2.b) {
            intakeMotor.setPower(0); //Off
        } else if (gamepad2.dpad_up) {
            intakeMotor.setPower(-1);
        }
    }
}
