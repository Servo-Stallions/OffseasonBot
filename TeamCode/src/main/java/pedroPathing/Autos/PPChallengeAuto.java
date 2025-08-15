package pedroPathing.Autos;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.pedropathing.pathgen.PathChain;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name = "PPChallengeAuto", group = "Autos")
public class PPChallengeAuto extends OpMode {
    private Follower follower;

    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState;
    private final Pose BotPose0 = new Pose(0,0,Math.toRadians(180));
    private final Pose BotPose1 = new Pose(36, -88, Math.toRadians(313));
    private final Pose BotPose2 = new Pose(118, -73, Math.toRadians(53));
    private final Pose BotPose3 = new Pose(91, 8, Math.toRadians(145));
    private final Pose BotPose4 = new Pose(7, -17, Math.toRadians(245));


    private PathChain Squareification,cubeification,tesseractification,penteractification;
    //private PathChain BotPath1;

    public void buildPaths() {
        Squareification = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BotPose0), new Point(BotPose1)))
                .setLinearHeadingInterpolation(BotPose0.getHeading(),BotPose1.getHeading())
                .setZeroPowerAccelerationMultiplier(6)
                .setPathEndTimeoutConstraint(250)
                .build();
        cubeification = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BotPose1), new Point(BotPose2)))
                .setLinearHeadingInterpolation(BotPose1.getHeading(),BotPose2.getHeading())
                .setZeroPowerAccelerationMultiplier(4)
                .setPathEndTimeoutConstraint(500)
                .build();
        tesseractification = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BotPose2), new Point(BotPose3)))
                .setLinearHeadingInterpolation(BotPose2.getHeading(),BotPose3.getHeading())
                .setZeroPowerAccelerationMultiplier(8)
                .setPathEndTimeoutConstraint(0)
                .build();
        penteractification = follower.pathBuilder()
                .addPath(new BezierLine(new Point(BotPose3), new Point(BotPose4)))
                .setLinearHeadingInterpolation(BotPose3.getHeading(),BotPose4.getHeading())
                .build();

    }
    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0: // Move from start to scoring position
                follower.followPath(Squareification);
                setPathState(1);
                break;


            case 1:
                if (follower.atParametricEnd()) {
                    follower.followPath(cubeification);
                    setPathState(2);
                }
                    break;

            case 2:
                if (follower.atParametricEnd()){
                    follower.followPath(tesseractification);
                    setPathState(3);
                }
                    break;

            case 3:
                if (follower.atParametricEnd()){
                    follower.followPath(penteractification);
                    setPathState(4);
                }
                break;
        }

     }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
    @Override
    public void init() {
        pathTimer = new Timer();
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(BotPose0);
        buildPaths();
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("Position", follower.getPose().toString());
        telemetry.update();
    }
}