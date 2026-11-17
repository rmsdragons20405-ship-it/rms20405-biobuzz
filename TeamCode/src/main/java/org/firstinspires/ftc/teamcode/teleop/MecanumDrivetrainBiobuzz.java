package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
public class MecanumDrivetrainBiobuzz extends OpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void start() {
        super.start();
    }

    public void loop() {
        Update();
    }
    private void Update() {
        double y = gamepad1.left_stick_y;
        double x = gamepad1.right_stick_x;
        double s = gamepad1.left_stick_x;

        double frontLeftPower = y + x + s;
        double frontRightPower = y - x - s;
        double backLeftPower = y + x - s;
        double backRightPower = y - x + s;

        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
    }
    }
