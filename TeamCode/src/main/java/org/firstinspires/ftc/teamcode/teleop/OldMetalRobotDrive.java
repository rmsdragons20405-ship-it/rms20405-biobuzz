package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "MetalRobotTeleop")
public class OldMetalRobotDrive extends OpMode {

    private DcMotor frontLeft, frontRight, backLeft, backRight;
    private IMU imu;

    @Override
    public void init() {
       frontLeft = hardwareMap.dcMotor.get("frontLeft");
       frontRight = hardwareMap.dcMotor.get("frontRight");
       backLeft = hardwareMap.dcMotor.get("backLeft");
       backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
    }
    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

        Update();

    }
    private void Update(){
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
