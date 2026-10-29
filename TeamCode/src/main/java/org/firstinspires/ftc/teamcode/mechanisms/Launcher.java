package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Launcher {

    private DcMotor launcherMotor, rollerMotor;

    public Launcher(HardwareMap hardwareMap) {
        launcherMotor = hardwareMap.get(DcMotor.class, "launcherMotor");
        rollerMotor = hardwareMap.get(DcMotor.class, "rollerMotor");
    }

    public void launcherOn() {
        launcherMotor.setPower(0.61);
        rollerMotor.setPower(0.8);

    }
    public void launcherOff() {
        launcherMotor.setPower(0);
        rollerMotor.setPower(0);
    }
}