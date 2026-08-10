package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "bioTeleopV1", group = "Summer wood robot")
public class bioTeleopV1 extends OpMode {

    //Introduce motors
    private DcMotor frontRight, backRight, frontLeft, backLeft, intakeMotor;
    private DcMotorEx viperSlides;

    //Introduce axon servo
    private Servo bucketTilt;
    private ElapsedTime timer = new ElapsedTime(); //A timer to replace sleep from Linear OpMode

    //Make variables for encoder ticks and other info needed to run the viper slides.

    static final double TICKS_PER_REV = 384.5;
    static final double PULLEY_CIRCUMFERENCE_MM = 120.0;
    static final double TICKS_PER_MM = TICKS_PER_REV / PULLEY_CIRCUMFERENCE_MM;
    static final int POS_STOWED = 0; //Not extended

    /*
    static final int POS_LOW    = 200; Low position
    static final int POS_MED    = 450; Medium position
     */
    static final int POS_HIGH   = 700; // High position - Adjust based on total 4-stage extension
    // --- Axon positions---
    static final double POS_OPEN = 0.5; //Bucket ready
    static final double POS_TILT = 0; //Bucket tilted

    @Override
    public void init() {
        //HARDWARE MAPS
        //Initialize drivetrain
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        //Initialize outtake
        viperSlides = hardwareMap.get(DcMotorEx.class, "viperSlides");
        bucketTilt = hardwareMap.get(Servo.class, "bucketTilt");
        //Initialize intake
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");

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
        // 1. Set direction to reverse so that the balls spill out the correct way.
        // 2. Set the range of the servo.
        bucketTilt.scaleRange(0,1);
        //Timer resets




//TELEMETRY
        telemetry.addData("Status", "Initialized");
        telemetry.update();

    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        //All of these functions are below this loop.

        updateDrive(); //Driving code
        updateOuttake();//Outtake (bucket and slides) code
        updateIntake();// Intake code
    }

    private void updateDrive() {
        // Driving code
        double y = -gamepad1.left_stick_y; //Forward and backward
        double x = gamepad1.right_stick_x; //Turning
        double s = gamepad1.left_stick_x; //Strafing
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
    private void updateOuttake() {
        //Viper slides and bucket tilter
        if (gamepad2.a) {
            setSlidePosition(POS_STOWED);//Down
        } else if (gamepad2.y) {
            setSlidePosition(POS_HIGH);//Extended fully (high)
        }
        //Manual control for Bucket tilter servo
        if (gamepad2.left_bumper) {
            bucketTilt.setPosition(POS_OPEN); //Tilts balls into goal
        } else if (gamepad2.right_bumper) {
            bucketTilt.setPosition(POS_TILT); // Bucket is available for next batch of balls
        }
    }
    private void updateIntake() {
        //Intake code
        if (gamepad2.x) {
            intakeMotor.setPower(1); //On
        } else if (gamepad2.b) {
            intakeMotor.setPower(0); //Off
        } else if (gamepad2.dpad_up) {
            intakeMotor.setPower(-1);
        }
    }
    public void setSlidePosition(int mm) { //Set slide position
        int targetTicks = (int)(mm * TICKS_PER_MM); //Assign variable targetTicks
        viperSlides.setTargetPosition(targetTicks); //Set position
        viperSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION); //Set mode
        viperSlides.setPower(1.0); // Maximum speed to reach target
    }
}
