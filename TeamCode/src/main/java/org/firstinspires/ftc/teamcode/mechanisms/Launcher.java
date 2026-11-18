package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class Launcher {
    private DcMotorEx launcher;
    public DcMotor counterRoller;
    public double power = -0.8;

    public Launcher(HardwareMap hardwareMap) {
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        counterRoller = hardwareMap.get(DcMotor.class, "counterRoller");
    }

    public void launcherOn() {
        launcher.setPower(power);
    }

    public void launcherOff() {
        launcher.setPower(0);

    }

    public void launcherReverse() {
        launcher.setPower(-power);

    }

    public void counterRollerOn() {

        counterRoller.setPower(0.25);
    }

    public void counterRollerOff() {

        counterRoller.setPower(0);
    }

    public void counterRollerReverse() {

        counterRoller.setPower(-0.25);
    }

    public void setPIDFCoefficients(DcMotor.RunMode runMode, PIDFCoefficients pidfCoefficients) {
        launcher.setPIDFCoefficients(runMode, pidfCoefficients);
    }


    public void setVelocity(double velocity) {
        launcher.setVelocity(velocity);
    }


    public double getVelocity() {
        return launcher.getVelocity();
    }
}
