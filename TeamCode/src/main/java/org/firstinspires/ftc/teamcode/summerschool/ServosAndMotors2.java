package org.firstinspires.ftc.teamcode.summerschool;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "summerschool2")
public class ServosAndMotors2 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // Prepare "motor" motor
        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

        // While game is running...
        waitForStart();
        while (opModeIsActive()) {

            // Set "motor" power to left joystick x divided by two
            motor.setPower(gamepad1.left_stick_x / 2);

        }
    }

}
