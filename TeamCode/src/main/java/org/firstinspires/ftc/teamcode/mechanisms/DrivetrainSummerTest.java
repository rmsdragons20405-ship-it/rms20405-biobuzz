package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DrivetrainSummerTest extends OpMode {
    private DcMotor frontRight, backRight, frontLeft, backLeft;
    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        // Driving code
        double y = -gamepad1.left_stick_y; //Forward and backward
        double x = gamepad1.right_stick_x; //Turning
        double s = gamepad1.left_stick_x; //Strafing
        //Making variables to apply power to each motor
        double frontLeftPower = y + x - s;
        double frontRightPower = y - x + s;
        double backLeftPower = y + x + s;
        double backRightPower = y - x - s;
        //Actually applying power to motors.
        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);

    }
}
