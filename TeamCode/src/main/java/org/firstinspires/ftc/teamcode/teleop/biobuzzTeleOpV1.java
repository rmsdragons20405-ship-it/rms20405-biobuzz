package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrivetrain;
@TeleOp(name = "biobuzzTeleopV1")
public class biobuzzTeleOpV1 extends OpMode {

    private MecanumDrivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        drivetrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
    }
}
