package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ViperSlides {
    private DcMotorEx viperSlides;
    static final double TICKS_PER_REV = 384.5;
    static final double PULLEY_CIRCUMFERENCE_MM = 120.0;
    static final double TICKS_PER_MM = TICKS_PER_REV / PULLEY_CIRCUMFERENCE_MM;

    // --- Preset Positions (in Millimeters) ---
    static final int POS_STOWED = 0;
    static final int POS_LOW    = 200;
    static final int POS_MED    = 450;
    static final int POS_HIGH   = 800; // Adjust based on total 4-stage extension

    public ViperSlides(HardwareMap hardwareMap) {
        viperSlides = hardwareMap.get(DcMotorEx.class, "viperSlides");
        viperSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        viperSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        viperSlides.setTargetPosition(0);
        viperSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlides.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void viperSlidesDown() {
        setSlidePosition(POS_STOWED);
    }
    public void viperSlidesLow() {
        setSlidePosition(POS_LOW);
    }
    public void viperSlidesMec() {
        setSlidePosition(POS_MED);
    }
    public void viperSlidesHigh() {
        setSlidePosition(POS_HIGH);
    }

    public void setSlidePosition(int mm) {
        int targetTicks = (int)(mm * TICKS_PER_MM);
        viperSlides.setTargetPosition(targetTicks);
        viperSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlides.setPower(1.0); // Maximum speed to reach target
    }
}
