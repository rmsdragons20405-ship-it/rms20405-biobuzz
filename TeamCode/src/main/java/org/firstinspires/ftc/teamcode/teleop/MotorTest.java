package org.firstinspires.ftc.teamcode.teleop;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "MotorTest")
public class MotorTest extends OpMode {
    private DcMotor random;

    public void init() {
        random = hardwareMap.get(DcMotor.class, "random");
    }
    public void loop() {
        if (gamepad1.a) {
            random.setPower(1);
        }
        if (gamepad1.b) {
            random.setPower(0.2);
        }

    }

}
