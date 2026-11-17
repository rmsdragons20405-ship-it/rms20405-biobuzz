package org.firstinspires.ftc.teamcode.autonomous;

import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
@Autonomous(name = "AutonScrimmageHalloween")
public class AutonScrimmageHalloween extends OpMode {
    private Launcher launcherMotor;

    private Transfer transferMotor;
    private Timer opModeTimer;

    private Intake intakeMotor;
    public void init() {
        intakeMotor = new Intake(hardwareMap);
        transferMotor = new Transfer(hardwareMap);
        launcherMotor = new Launcher(hardwareMap);
        opModeTimer = new Timer();
    }

    @Override
    public void start() {
        super.start();
        opModeTimer.resetTimer();
    }

    @Override
    public void loop() {
        launcherMotor.launcherOn();
        if (opModeTimer.getElapsedTimeSeconds() >3) {
            intakeMotor.intakeOn();
            transferMotor.transferOn();
        }
        if (opModeTimer.getElapsedTimeSeconds() > 10) {
            launcherMotor.launcherOff();
            intakeMotor.intakeOff();
            transferMotor.transferOff();
        }

    }
}
