package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Bucket;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.ViperSlides;

@TeleOp(name = "bioTeleopV2", group = "Summer wood robot")
public class bioTeleopV2 extends OpMode {
    private MecanumDrivetrain drivetrain;
    private Intake intakeMotor;
    private ViperSlides viperSlides;
    private Bucket bucketTilt;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        intakeMotor = new Intake(hardwareMap);
        viperSlides = new ViperSlides(hardwareMap);
        bucketTilt = new Bucket(hardwareMap);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        //Drivetrain
        drivetrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);
        //Intake
        if (gamepad2.x) {
            intakeMotor.intakeOn();
        } else if (gamepad2.b) {
            intakeMotor.intakeOff();
        } else if(gamepad2.dpad_up) {
            intakeMotor.intakeReverse();
        }
        //Viper Slides
        if (gamepad2.a) {
            viperSlides.viperSlidesDown();
        } else if (gamepad2.y) {
            viperSlides.viperSlidesHigh();
        }
        //Bucket
        if (gamepad2.left_bumper) {
            bucketTilt.bucketTilted(); //Tilts balls into goal
        } else if (gamepad2.right_bumper) {
            bucketTilt.bucketOpen(); // Bucket is available for next batch of balls
        }
    }
}
