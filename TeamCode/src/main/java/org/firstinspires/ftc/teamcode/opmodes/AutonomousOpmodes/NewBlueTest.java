package org.firstinspires.ftc.teamcode.opmodes.AutonomousOpmodes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.util.AutoTrajectories;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "OwenSummerTest", group = "Autonomous")
public class NewBlueTest extends LinearOpMode {
    int visionOutputPosition = 0;

    public class Lift {
        private DcMotorEx lift;

        public Lift(HardwareMap hardwareMap) {
            lift = hardwareMap.get(DcMotorEx.class, "uppies");
            lift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
            lift.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        public class LiftUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift.setPower(-0.8);
                    initialized = true;
                }

                double pos = lift.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > -2000.0) {
                    return true;
                } else {
                    lift.setPower(0);
                    return false;
                }
            }
        }
        public Action liftUp() {
            return new LiftUp();
        }

        public class LiftDown implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if (!initialized) {
                    lift.setPower(0.8);
                    initialized = true;
                }

                double pos = lift.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos < -100.0) {
                    return true;
                } else {
                    lift.setPower(0);
                    return false;
                }
            }
        }
        public Action liftDown(){
            return new LiftDown();
        }
    }
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d initialPose = new Pose2d(7.00, -70.00, Math.toRadians(90.00));
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        Lift lift = new Lift(hardwareMap);

        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Position during Init", position);
            telemetry.update();
            if (isStopRequested()) return;
        }

        int startPosition = visionOutputPosition;
        telemetry.addData("Starting Position", startPosition);
        telemetry.update();
        waitForStart();

        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)

                .lineToY(-40)
                .strafeTo(new Vector2d(20,-35))

                .strafeToLinearHeading(new Vector2d(0,-60), Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(25,15),Math.toRadians(-90))
               /*
                .waitSeconds(0)
                .lineToY(-35) // (7, -25) 90 deg
                // Put Specimen on rung
                .waitSeconds(0.5)
                .lineToY(-40)
                .strafeTo(new Vector2d(35,-35))
                .strafeTo(new Vector2d(38,-10))
                .strafeTo(new Vector2d(45,-10))
                .strafeTo(new Vector2d(45,-55))
                //Sample 1 in zone
                .lineToY(-10)
                .strafeTo(new Vector2d(54,-10))
                .strafeTo(new Vector2d(54,-55))
                //Sample 2 in zone
                .lineToY(-10)
                .strafeTo(new Vector2d(60.5,-10))
                .strafeTo(new Vector2d(60.5,-55))
                //Sample 3 in zone
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(-90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(55,-60))
                //Spec 2 on wall
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(10,-35), Math.toRadians(90))
                //Place Spec 2
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(-90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(55,-60))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(5,-35), Math.toRadians(90))
                //Place Spec 3
                /*
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(-90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(55,-60))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(2,-35), Math.toRadians(90))
                //Place Spec 4
                .strafeToLinearHeading(new Vector2d(55,-50),Math.toRadians(-90))
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(55,-60))
                .waitSeconds(0.5)
                .strafeToLinearHeading(new Vector2d(0,-35), Math.toRadians(90))
                //Place Spec 5
                */
                .waitSeconds(1);

        // Trajectories

        // AutonomousActions.EmergencyArm emergencyArm = new AutonomousActions.EmergencyArm(hardwareMap, telemetry);

        SequentialAction main = new AutoTrajectories.CompAutoTrajectorySequence(drive, hardwareMap).build();

        Actions.runBlocking(new SequentialAction(
                lift.liftUp(),
                tab1.build(),
                lift.liftDown()
        ));
    }
}
