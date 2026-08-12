package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrivetrain {
    private DcMotor frontLeft, backLeft, frontRight, backRight;

    public MecanumDrivetrain(HardwareMap hardwareMap) {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    }

    public void drive(double y, double x, double s) {
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
