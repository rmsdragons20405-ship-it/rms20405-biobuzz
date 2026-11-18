package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class MecanumDrivetrainBiobuzz extends OpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private static final double TURN_SPEED = 0.5;

    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void start() {
        super.start();
    }

    public void loop() {
        Update();
    }

    private void Update() {
        double y = gamepad1.left_stick_y;
        double x = gamepad1.right_stick_x * TURN_SPEED;
        double s = gamepad1.left_stick_x;

        double frontLeftPower = y - x - s;
        double frontRightPower = y + x + s;
        double backLeftPower = y - x + s;
        double backRightPower = y + x - s;

        // Keeps motor powers proportional if they exceed 1.0
        double max = Math.max(Math.abs(frontLeftPower), Math.max(Math.abs(frontRightPower),
                Math.max(Math.abs(backLeftPower), Math.abs(backRightPower))));
        if (max > 1.0) {
            frontLeftPower /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
}
