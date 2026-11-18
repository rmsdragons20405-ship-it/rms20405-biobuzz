package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.List;

@Autonomous(name="BioAutonV1", group="Linear OpMode")// called BioAutonV1
public class AutoAlignAprilTag extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor intakeMotor;
    private DcMotor viperSlides;
    private Limelight3A limelight;

    @Override
    public void runOpMode() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        viperSlides = hardwareMap.get(DcMotor.class, "viperSlides");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.FORWARD);

        limelight.pipelineSwitch(0);//pipeline that has ID 24
        limelight.start();

        waitForStart();
        runIntake(1, 10000);//goes forward with intake on and intakes balls

        driveForward(0.47, 1500);//could be 1 but the robot will turn because of drift, causing ball to not go in intake.
         
        stopRobot(500);

        while (opModeIsActive() && !isTag24Visible()) {// turns right to try and locate april tag
            turnRight(.45, 3200);
            goBackward(.45, 1700);
            frontLeft.setPower(0.5);
            frontRight.setPower(-0.5);
            backLeft.setPower(0.5);
            backRight.setPower(-0.5);

        }

        stopRobot(300);

        while (opModeIsActive() && isTag24Visible()) {// goes forward towards april tag until it is lost(too close to see)
            frontLeft.setPower(0.47);//could be 1 but the robot will turn because of drift, causing april tag to be lost.
            frontRight.setPower(0.47);
            backLeft.setPower(0.47);
            backRight.setPower(0.47);
        }
        driveForward(0.47, 700);

        runSlides(0.7,800);

        stopRobot(500); 
    }

    private boolean isTag24Visible() {//tell limelight to get the results
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            List<com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
            for (com.qualcomm.hardware.limelightvision.LLResultTypes.FiducialResult f : fiducials) {
                if (f.getFiducialId() == 24) {
                    return true;
                }
            }
        }
        return false;
    }

    public void driveForward(double power, int timeMS) {// create driveForward function
        frontLeft.setPower(0.6);
        frontRight.setPower(0.6);
        backLeft.setPower(0.65);//change to be the same once motor is fixed, currently running slow:(
        backRight.setPower(0.6);
        sleep(timeMS);
    }

    public void turnRight(double power, int timeMS) {// create turnRight function
        frontLeft.setPower(0.5);
        frontRight.setPower(-0.5);
        backLeft.setPower(0.55);//change to be the same once motor is fixed, currently running slow:(
        backRight.setPower(-0.5);

    }

    public void runIntake(double power, int timeMs) {// create runIntake function
        intakeMotor.setPower(-1);
    }

    public void runSlides(double power, int timeMS) {//create runSlides funcion
        viperSlides.setPower(1);
    }

    public void goBackward(double power, int timeMS) {//create function to go backwards
        frontLeft.setPower(-0.5);
        backLeft.setPower(-0.55);//change to be the same once motor is fixed, currently running slow:(
        frontRight.setPower(-0.5);
        backRight.setPower(-0.5);
    }

    public void stopRobot(int pauseMS) {// create stopRobot function
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        sleep(pauseMS);
    }
}