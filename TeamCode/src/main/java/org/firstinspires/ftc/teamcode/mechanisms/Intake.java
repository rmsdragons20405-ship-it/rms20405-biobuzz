package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor intakeMotor;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
    }

    public void intakeOn() {
        intakeMotor.setPower(1);
    }
    public void intakeOff() {
        intakeMotor.setPower(0);
    }
    public void intakeReverse() {
        intakeMotor.setPower(-1);
    }
}
