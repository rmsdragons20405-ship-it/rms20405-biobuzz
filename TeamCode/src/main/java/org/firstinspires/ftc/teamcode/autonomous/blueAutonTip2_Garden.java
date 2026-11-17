package org.firstinspires.ftc.teamcode.autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.mechanisms.Transfer;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class blueAutonTip2_Garden extends OpMode {

    private Follower follower;

    private Launcher launcherMotor;

    private Transfer transferMotor;

    private Intake intakeMotor;

    private Timer pathTimer, opModeTimer;

    public enum PathState {
        SHOOT_PRELOAD,
        DRIVE_START_TO_GARDEN,
        INTAKE_GARDEN,
        DRIVE_GARDEN_TO_SHOOT,
        SHOOT_1,
        DRIVE_SHOOT_TO_FLOWER,
        INTAKE_FLOWER,
        SHOOT_2,
        PARK_FROM_SHOOT

    }
    PathState pathState;

    private final Pose startPose = new Pose(83, 133, Math.toRadians(270));
    private final Pose nearGarden = new Pose(133.06, 117.13, Math.toRadians(90));
    private final Pose intakeGarden = new Pose(133.42, 133.55, Math.toRadians(90));
    private final Pose controlPose = new Pose(125.03, 54.45, Math.toRadians(90));
    private final Pose shootPose = new Pose(83.54, 22.28, Math.toRadians(90));
    private final Pose nearFlower = new Pose(94.13, 22.28, Math.toRadians(270));
    private final Pose intakeFlower = new Pose(94.13, 13.13, Math.toRadians(270));
    private final Pose parkPose = new Pose(132.68, 23.88, Math.toRadians(90));

    private PathChain driveStart_Garden, driveGarden, driveGarden_Shoot, driveShoot_Flower, driveFlower, driveFlower_Shoot, driveShoot_Park;

    public void buildPaths() {
        driveStart_Garden = follower.pathBuilder()
                .addPath(new BezierLine(startPose, nearGarden))
                .setLinearHeadingInterpolation(startPose.getHeading(), nearGarden.getHeading())
                .build();
        driveGarden = follower.pathBuilder()
                .addPath(new BezierLine(nearGarden, intakeGarden))
                .setLinearHeadingInterpolation(nearGarden.getHeading(), intakeGarden.getHeading())
                .build();
        driveGarden_Shoot = follower.pathBuilder()
                .addPath(new BezierCurve(intakeGarden, controlPose, shootPose))
                .setLinearHeadingInterpolation(intakeGarden.getHeading(), controlPose.getHeading(), shootPose.getHeading())
                .build();
        driveShoot_Flower = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, nearFlower))
                .setLinearHeadingInterpolation(shootPose.getHeading(), nearFlower.getHeading())
                .build();
        driveFlower = follower.pathBuilder()
                .addPath(new BezierLine(nearFlower, intakeFlower))
                .setLinearHeadingInterpolation(nearFlower.getHeading(), intakeFlower.getHeading())
                .build();
        driveFlower_Shoot = follower.pathBuilder()
                .addPath(new BezierLine(intakeFlower, shootPose))
                .setLinearHeadingInterpolation(intakeFlower.getHeading(), shootPose.getHeading())
                .build();
        driveShoot_Park = follower.pathBuilder()
                .addPath(new BezierLine(shootPose, parkPose))
                .setLinearHeadingInterpolation(shootPose.getHeading(), parkPose.getHeading())
                .build();

    }
    public void statePathUpdate() {
        switch(pathState) {
            case SHOOT_PRELOAD:
                //TODO ADD MECHANISMS TO SHOOT PRELOADS HERE
                intakeMotor.intakeOn();
                transferMotor.transferOn();
                launcherMotor.launcherOn();

                pathState = PathState.DRIVE_START_TO_GARDEN;
                break;
            case DRIVE_START_TO_GARDEN:
                if(!follower.isBusy()) {
                    follower.followPath(driveStart_Garden, true);
                    pathState = PathState.INTAKE_GARDEN;
                    break;
                }
            case INTAKE_GARDEN:
                if(!follower.isBusy()) {
                    follower.followPath(driveGarden, true);
                    pathState = PathState.DRIVE_GARDEN_TO_SHOOT;
                    break;
                }
            case DRIVE_GARDEN_TO_SHOOT:
                if(!follower.isBusy()) {
                    follower.followPath(driveGarden_Shoot);
                    pathState = PathState.SHOOT_1;
                    break;
                }
            case SHOOT_1:
                //TODO ADD MECHANISM CODE HERE
                pathState = PathState.DRIVE_SHOOT_TO_FLOWER;
                break;
            case DRIVE_SHOOT_TO_FLOWER:
                if (!follower.isBusy()) {
                    follower.followPath(driveShoot_Flower);
                    pathState = PathState.INTAKE_FLOWER;
                    break;
                }
            case INTAKE_FLOWER:
                if (!follower.isBusy()) {
                    follower.followPath(driveFlower);
                    pathState = PathState.SHOOT_2;
                    break;
                }
            case SHOOT_2:
                //TODO MECHANISM CODE HERE
                pathState = PathState.PARK_FROM_SHOOT;
                break;
            case PARK_FROM_SHOOT:
                if (!follower.isBusy()) {
                    follower.followPath(driveShoot_Park);
                    break;
                }



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
        follower.update(); //THIS IS THE MOST IMPORTANT LINE OF THE CODE! IT MAKES EVERYTHING RUN!!!!!
        statePathUpdate();
    }
}
