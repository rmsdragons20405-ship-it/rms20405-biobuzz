package org.firstinspires.ftc.teamcode.autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.util.Timer;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class blueAutonTip1 extends OpMode {
    private Follower follower;

    private Launcher launcherMotor;

    private Transfer transferMotor;

    private Intake intakeMotor;

    private Timer pathTimer, opModeTimer;
    public enum PathState {
        SHOOT_PRELOAD,
        DRIVE_TO_PARK
    }

    PathState pathState;

    private final Pose startPose = new Pose(85.84013605442178, 133.1360544217687, Math.toRadians(270));
    private final Pose parkPose = new Pose(132.81519274376421, 50.15419501133788, Math.toRadians(270));

    private PathChain driveStart_Park;

    public void buildPaths() {
        driveStart_Park = follower.pathBuilder()
                .addPath(new BezierLine(startPose, parkPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), parkPose.getHeading())
                .build();
    }

    public void statePathUpdate() {
        switch(pathState) {
            case SHOOT_PRELOAD:
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 3) {
                    //TODO ADD MECHANISMS LOGIC HERE
                }
                setPathState(PathState.DRIVE_TO_PARK);
                break;
            case DRIVE_TO_PARK:
                if (!follower.isBusy() && pathTimer.getElapsedTimeSeconds() > 5) {
                    follower.followPath(driveStart_Park, true);
                }
                if (!follower.isBusy()) {
                    telemetry.addLine("Done with auton! :)");
                }
                break;
            default:
                telemetry.addLine("No State Running");
                break;
        }
    }
    public void setPathState(PathState newState) {
        pathState = newState;
        pathTimer.resetTimer();
    }

    @Override
    public void init() {
        pathState = PathState.SHOOT_PRELOAD;
        pathTimer = new Timer(); //These timers are used for flywheel logic, like turning on the intake on time.
        opModeTimer = new Timer();
        follower = Constants.createFollower(hardwareMap);
        intakeMotor = new Intake(hardwareMap);
        transferMotor = new Transfer(hardwareMap);
        launcherMotor = new Launcher(hardwareMap);
        // TODO add in any other mechanisms like launcher, limelight, etc.
        buildPaths();
        follower.setPose(startPose);
    }

    @Override
    public void start() {
        opModeTimer.resetTimer();
        setPathState(pathState);
        super.start();
    }

    @Override
    public void loop() {
        //TODO TURN ON LAUNCHER HERE
        follower.update(); //THIS IS THE MOST IMPORTANT LINE OF THE CODE! IT MAKES EVERYTHING RUN!!!!!
        statePathUpdate();

        telemetry.addData("path state", pathState.toString());
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("timer", pathTimer.getElapsedTimeSeconds());

    }
}