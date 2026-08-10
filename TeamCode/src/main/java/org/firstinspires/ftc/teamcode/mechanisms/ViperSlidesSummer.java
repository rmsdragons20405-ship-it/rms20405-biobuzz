package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ViperSlides", group="Summer wood robot")
public class ViperSlidesSummer extends LinearOpMode {

    private DcMotorEx viperSlides;
    private Servo bucketTilt;

    // --- Launcher Velocities (ticks per second) ---
// Gobilda 5203-2402-0014 (435 RPM) Specs: 384.5 Ticks Per Rev
    static final double TICKS_PER_REV = 384.5;
    static final double PULLEY_CIRCUMFERENCE_MM = 120.0;
    static final double TICKS_PER_MM = TICKS_PER_REV / PULLEY_CIRCUMFERENCE_MM;

    // --- Preset Positions (in Millimeters) ---
    static final int POS_STOWED = 0;
    static final int POS_LOW    = 200;
    static final int POS_MED    = 450;
    static final int POS_HIGH   = 800; // Adjust based on total 4-stage extension
    // --- Axon positions---
    static final double POS_OPEN = 0.8;
    static final double POS_TILT = 0.3;

    @Override
    public void runOpMode() {
        viperSlides = hardwareMap.get(DcMotorEx.class, "viperSlides");
        bucketTilt = hardwareMap.get(Servo.class, "bucketTilt");
// ---VIPER SLIDE INIT---
        // 1. Reset Encoders to zero on start
        viperSlides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // 2. Set to BRAKE so the slides don't fall when power is 0
        viperSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // 3. Initial Target
        viperSlides.setTargetPosition(0);
        viperSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        // 4. Set direction of motor to reverse so that viper slides goes up
        viperSlides.setDirection(DcMotorSimple.Direction.REVERSE);
// ---AXON SERVO INIT---
        // 1. Set range of servo
        bucketTilt.scaleRange(0,1);

//TELEMETRY
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // --- Preset Buttons for VIPER SLIDES(Gamepad 2) ---
            if (gamepad2.a) {
                setSlidePosition(POS_STOWED); //Down
            } else if (gamepad2.x) {
                setSlidePosition(POS_LOW); // Low
            } else if (gamepad2.y) {
                setSlidePosition(POS_MED); // Medium
            } else if (gamepad2.b) {
                setSlidePosition(POS_HIGH); //Extended fully (high)
            }
            // --- Button for Bucket tilter servo ---
            if (gamepad2.left_bumper) {
                bucketTilt.setPosition(POS_TILT); //Tilts balls into goal
            } else if (gamepad2.right_bumper) {
                bucketTilt.setPosition(POS_OPEN); // Bucket is available for next batch of balls
            }
            // Telemetry
            telemetry.addData("Target Pos Ticks", viperSlides.getTargetPosition());
            telemetry.addData("Current Pos Ticks", viperSlides.getCurrentPosition());
            telemetry.addData("Current Pos MM", viperSlides.getCurrentPosition() / TICKS_PER_MM);
            telemetry.addData("Bucket Position", bucketTilt.getPosition());
            telemetry.update();
        }
    }

    /**
     * Converts Millimeters to Ticks and moves the slide
     * @param mm desired height in millimeters
     */
    //Raj uncle helped with this part below and the encoder stuff
    public void setSlidePosition(int mm) {
        int targetTicks = (int)(mm * TICKS_PER_MM);
        viperSlides.setTargetPosition(targetTicks);
        viperSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        viperSlides.setPower(1.0); // Maximum speed to reach target
    }
}
