package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    private DcMotor launcherMotor, rollerMotor;



    public double power = 0.2;
    public Launcher(HardwareMap hardwareMap) {
        launcherMotor = hardwareMap.get(DcMotor.class, "launcherMotor");
        rollerMotor = hardwareMap.get(DcMotor.class, "rollerMotor");

    }
    public void launcherOn() {
        launcherMotor.setPower(0.775);
        rollerMotor.setPower(0.25);

    }
    public void launcherOff() {
        launcherMotor.setPower(0.0);
        rollerMotor.setPower(0);
    }

    public void launcherReverse() {
        launcherMotor.setPower(-0.5);
        rollerMotor.setPower(-0.5);
    }

}