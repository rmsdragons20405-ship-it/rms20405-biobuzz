package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;

@TeleOp(name = "biobuzzTeleopV1")
public class biobuzzTeleOpV1 extends OpMode {

    private MecanumDrivetrain drivetrain;
    private Intake intakeMotor;

    private Transfer transferMotor;

    private Launcher launcherMotor;

    @Override
    public void init() {
        drivetrain = new MecanumDrivetrain(hardwareMap);
        intakeMotor = new Intake(hardwareMap);
        transferMotor = new Transfer(hardwareMap);
        launcherMotor = new Launcher(hardwareMap);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void loop() {

        //DRIVETRAIN CODE - SEE DRIVETRAIN MECHANISM CODE
        drivetrain.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

        //INTAKE CODE - SEE INTAKE MECHANISM CODE
        if (gamepad2.a) {
            intakeMotor.intakeOn();
        }
        if (gamepad2.b) {
            intakeMotor.intakeOff();
        }
        if (gamepad2.right_trigger_pressed) {
            intakeMotor.intakeReverse();
        }
        //TRANSFER CODE - SEE TRANSFER MECHANISM CODE
        if (gamepad2.x) {
            transferMotor.transferOn();
        }
        if (gamepad2.y) {
            transferMotor.transferOff();
        }
        if (gamepad2.left_trigger_pressed) {
            transferMotor.transferReverse();
        }






        //LAUNCHER CODE - SEE LAUNCHER MECHANISM CODE

        if (gamepad2.left_bumper) {
            launcherMotor.launcherOn();
        }
        if (gamepad2.right_bumper) {
            launcherMotor.launcherOff();
        }



    }
}