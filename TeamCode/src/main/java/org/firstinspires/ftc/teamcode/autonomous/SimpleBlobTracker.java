package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "LimeLightBlobs", group = "Summer wood robot")
public class SimpleBlobTracker extends OpMode {
    private Limelight3A limelight3A;
    private DcMotorEx frontLeft, backLeft, frontRight, backRight, intakeMotor;


    @Override
    public void init() {
        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(9); //detects yellow pollen.
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");
    }

    @Override
    public void start() {
        limelight3A.start();
    }

    @Override
    public void loop() {
        intakeMotor.setPower(1);
        LLResult llResult = limelight3A.getLatestResult();
        double tx = llResult.getTx();
        double ty = llResult.getTy();
        double ta = llResult.getTa();
        if (llResult != null && llResult.isValid()) {
            if (tx > 2) {
                frontLeft.setPower(0.5);
                backLeft.setPower(0.5);
                frontRight.setPower(0);
                backRight.setPower(0);
            } else if (tx < -2) {
                frontRight.setPower(0.5);
                backRight.setPower(0.5);
                frontLeft.setPower(0);
                backLeft.setPower(0);
            } else if (tx < 3 && tx > 0) {
                frontLeft.setPower(0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(0.5);
            } else if (tx > -3 && tx < 0) {
                frontLeft.setPower(0.5);
                frontRight.setPower(0.5);
                backLeft.setPower(0.5);
                backRight.setPower(0.5);
            }
        }

            telemetry.addData("Target X Offset", llResult.getTx());
            telemetry.addData("Target Y Offest", llResult.getTy());
            telemetry.addData("Target Area Offest", llResult.getTa());
            //-----------------------------------------------------------------\\




    }
}

