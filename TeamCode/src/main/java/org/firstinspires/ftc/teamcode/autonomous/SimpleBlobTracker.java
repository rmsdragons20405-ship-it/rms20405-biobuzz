package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.AutonMethods;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;

@Autonomous(name = "LimeLightBlobs", group = "Summer wood robot")
public class SimpleBlobTracker extends OpMode {
    private Limelight3A limelight3A;
    private AutonMethods drivetrain;
    private Intake intakeMotor;



    @Override
    public void init() {
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(5);

        drivetrain = new AutonMethods(hardwareMap);
        intakeMotor = new Intake(hardwareMap);
    }

    @Override
    public void start() {
        limelight3A.start();
    }

    @Override
    public void loop() {
        LLResult llResult = limelight3A.getLatestResult();
        double tx = llResult.getTx();
        double ty = llResult.getTy();
        double ta = llResult.getTa();
        if (llResult.isValid()) {
            if (tx > 2) {
                drivetrain.pivotRight();
                telemetry.addData("Direction", "Right");

            } else if (tx < -2) {
                drivetrain.pivotLeft();
                telemetry.addData("Direction", "Left");

            } else if (tx < 3 && tx > 0) {
                drivetrain.forwardSlow();
                telemetry.addData("Direction", "Forward");

            } else if (tx > -3 && tx < 0) {
                drivetrain.forwardSlow();
                telemetry.addData("Direction", "Forward");

            }else {
                drivetrain.tankTurnLeft();
                telemetry.addData("Direction", "Searching");
            }
        }

            telemetry.addData("Target X Offset", llResult.getTx());
            telemetry.addData("Target Y Offest", llResult.getTy());
            telemetry.addData("Target Area Offest", llResult.getTa());
            //-----------------------------------------------------------------\\




    }
}

