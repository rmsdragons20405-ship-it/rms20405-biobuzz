package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "AdvancedBlobTracker", group = "Summer wood robot")
public class AdvancedBlobTracker extends OpMode {
    private Limelight3A limelight3A;
    private DcMotor frontLeft, backLeft, frontRight, backRight, intakeMotor;

    private final double TURN_KP = 1; //Placeholder number, need to tune once robot is done.
    private final double DRIVE_KP = 1; //Placeholder number, need to tune once robot is done.
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        limelight3A = hardwareMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(2); //uses neural networks to detect pollen.
    }

    @Override
    public void start() {
        limelight3A.start();
        super.start();
    }

    @Override
    public void loop() {
        //intakeMotor.setPower(-1);
        LLResult llResult = limelight3A.getLatestResult();
        double tx = llResult.getTx();
        double ty = llResult.getTy();
        double turnError = tx;
        double driveError = -ty; //Negative to drive toward target, not away.

        double turnPower =  turnError * TURN_KP;
        double drivePower = driveError * DRIVE_KP;

        double leftPower = drivePower + turnPower;
        double rightPower = drivePower - turnPower;

        frontLeft.setPower(leftPower);
        backLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);

        telemetry.addData("tx", tx);
        telemetry.addData("ty", ty);
        telemetry.addData("Left power", leftPower);
        telemetry.addData("Right power", rightPower);

    }
}
