package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Transfer {

    private DcMotor transferMotor;

    public Transfer(HardwareMap hardwareMap) {
        transferMotor = hardwareMap.get(DcMotor.class, "transferMotor");

    }
    public void transferOn() {
        transferMotor.setPower(0.74);
    }
    public void transferOff() {
        transferMotor.setPower(0);
    }
    public void transferReverse() {
        transferMotor.setPower(-0.75);
    }
}