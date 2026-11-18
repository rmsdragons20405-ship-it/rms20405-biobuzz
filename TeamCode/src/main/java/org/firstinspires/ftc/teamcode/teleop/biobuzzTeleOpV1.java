package org.firstinspires.ftc.teamcode.teleop;

import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.ServoGate;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
@Disabled
@TeleOp(name = "biobuzzTeleopV1")
public class biobuzzTeleOpV1 extends OpMode {
    // Imports
    private MecanumDrivetrain drivetrain;
    private Intake intakeMotor;
    private Transfer transferMotor;
    private Launcher launcherMotor, counterRoller;
    private ServoGate bench = new ServoGate();
    // create the booleans
    private boolean lastDpadUp = false;
    private boolean lastDpadDown = false;
    private boolean isLauncherRunning = false;
    public double highVelocity = 1350;
    public double lowVelocity = 1350;
    double P = 16.259;
    double F = 12.908;
    double[] stepSizes = {10.0, 1.0, 0.1, 0.01, 0.001};
    int stepIndex = 1;
    double curTargetVelocity = highVelocity;
    private Timer timer;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        intakeMotor = new Intake(hardwareMap);
        transferMotor = new Transfer(hardwareMap);
        launcherMotor = new Launcher(hardwareMap);
        counterRoller = new Launcher(hardwareMap);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 16.259 ,12.908, F);
        launcherMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        bench.init(hardwareMap);
// only use if necessary
        timer = new Timer();
        timer.resetTimer();
        telemetry.addLine("Init Complete");
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {
        // define the booleans
        boolean currentDpadUp = gamepad2.dpad_up;
        boolean currentDpadDown = gamepad2.dpad_down;

        telemetry.addData("Laucher Speed", launcherMotor.power);

        drivetrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

        // PIDF TUNING
        if (curTargetVelocity == highVelocity) {
            curTargetVelocity = lowVelocity;
        } else {
            curTargetVelocity = highVelocity;
        }

        if (gamepad1.bWasPressed()) {
            stepIndex =(stepIndex + 1) % stepSizes.length;
        }

        if (gamepad1.dpadLeftWasPressed()) {
            F -= stepSizes [stepIndex];
        }
        if (gamepad1.dpadRightWasPressed()) {
            F += stepSizes [stepIndex];
        }
        if (gamepad1.dpadUpWasPressed()) {
            P += stepSizes [stepIndex];
        }
        if (gamepad1.dpadDownWasPressed()) {
            P -= stepSizes [stepIndex];
        }

// everything on
        if (gamepad2.a) {
            intakeMotor.intakeOn();
            counterRoller.counterRollerOn();
            isLauncherRunning = true;
        }

// everything off
        if (gamepad2.b) {
            intakeMotor.intakeOff();
            transferMotor.transferOff();
            counterRoller.counterRollerOff();
            isLauncherRunning = false;
        }

// everything reverse
        if (gamepad2.x) {
            transferMotor.transferReverse();
            intakeMotor.intakeReverse();
            isLauncherRunning = false;
        }

//transfer stuff
        if (gamepad2.left_bumper) {
            transferMotor.transferOn();
        }
        if (gamepad2.right_bumper) {
            transferMotor.transferOff();
        }
// gate stuff

        if (gamepad2.y) {
            bench.setServoPos(-0.2);
        }

// adding power to the launcher if dpad up is pressed
        if (currentDpadUp && !lastDpadUp) {
            launcherMotor.power = Math.min(1.0, launcherMotor.power + 0.01);
            if (isLauncherRunning) {
                launcherMotor.launcherOn();
            }
        }
// subtracting power to the launcher if dpad down is pressed
        if (currentDpadDown && !lastDpadDown) {
            launcherMotor.power = Math.max(0.0, launcherMotor.power - 0.01);
            if (isLauncherRunning) {
                launcherMotor.launcherOn();
            }
        }
// reset the booleans
        lastDpadUp = currentDpadUp;
        lastDpadDown = currentDpadDown;

        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(P, 0 ,0, F);
        launcherMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);

        launcherMotor.setVelocity(curTargetVelocity);

        double curVelocity = launcherMotor.getVelocity();
        double error = curTargetVelocity - curVelocity;

        telemetry.addData("Target Velocity", curTargetVelocity);
        telemetry.addData("Current Velocity", "%.2f", curVelocity);
        telemetry.addData("Error", "%.2f", error);
        telemetry.addLine("--------------------------------");
        telemetry.addData("Tuning P", "%.4f (D-Pad U/D)", P);
        telemetry.addData("Tuning F", "%.4f (D-Pad L/R)", F);
        telemetry.addData("Step Size", "%.4f (B Button)", stepSizes[stepIndex]);



    }
}
