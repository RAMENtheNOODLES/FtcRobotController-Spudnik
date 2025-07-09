package spudnik.team24288.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.SequentialAction;

public class OwenMeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(7.00, -70.00, Math.toRadians(90)))
                                .lineToY(-40)
                                .strafeTo(new Vector2d(20,-35))


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
                */
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
                .waitSeconds(1).build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();


    }
}