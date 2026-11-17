package org.firstinspires.ftc.teamcode.autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AutonMethods;

@Autonomous(name = "BiobuzzRobotTester")
public class BiobuzzRobotTester extends LinearOpMode {
    private AutonMethods drivetrain;

    @Override
    public void runOpMode() {

        drivetrain = new AutonMethods(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            //The following  lines make the drivetrain move forward and backward quickly,
            // and after it is done, it gives the viewer 5 seconds to check results.
            drivetrain.forwardFast();
            sleep(1000);
            drivetrain.backwardFast();
            sleep(1000);
            drivetrain.forwardFast();
            sleep(1000);
            drivetrain.backwardFast();
            sleep(1000);
            drivetrain.stop();
            sleep(5000);
            //END OF FORWARD BACKWARD TEST

            //The following lines make the robot tank turn right quickly for 5 secs, then left quickly
            // for 5 seconds, and then gives the viewer 5 seconds to check results.
            drivetrain.tankTurnRight();
            sleep(5000);
            drivetrain.tankTurnLeft();
            sleep(5000);
            drivetrain.stop();
            sleep(5000);
            //END OF TANK TURN TESTING

            drivetrain.strafeRight();
            sleep(1000);
            drivetrain.strafeLeft();
            sleep(1000);
            drivetrain.stop();

            telemetry.addLine("Done with all testing scenarios! Repeat again for consistency.");

        }


    }
}
