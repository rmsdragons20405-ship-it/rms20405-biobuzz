package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutonMethods {
    private DcMotor frontLeft, backLeft, frontRight, backRight;

    public AutonMethods(HardwareMap hardwareMap) {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void forwardFast() {
        frontRight.setPower(1);
        frontLeft.setPower(1);
        backLeft.setPower(1);
        backRight.setPower(1);
    }
    public void backwardFast() {
        frontRight.setPower(-1);
        frontLeft.setPower(-1);
        backLeft.setPower(-1);
        backRight.setPower(-1);
    }
    public void forwardSlow() {
        frontRight.setPower(0.5);
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0.5);
    }
    public void backwardSlow() {
        frontRight.setPower(-0.5);
        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(-0.5);
    }
    public void tankTurnRight() {
        frontRight.setPower(-0.5);
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(-0.5);
    }
    public void tankTurnLeft() {
        frontRight.setPower(0.5);
        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.5);
        backRight.setPower(0.5);
    }
    public void pivotRight() {
        frontRight.setPower(0);
        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        backRight.setPower(0);
    }
    public void pivotLeft() {
        frontRight.setPower(0.5);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0.5);
    }

    public void strafeRight() {
        frontRight.setPower(-1);
        frontLeft.setPower(1);
        backLeft.setPower(-1);
        backRight.setPower(1);
    }
    public void strafeLeft() {
        frontRight.setPower(1);
        frontLeft.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(-1);
    }

    public void stop() {
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
