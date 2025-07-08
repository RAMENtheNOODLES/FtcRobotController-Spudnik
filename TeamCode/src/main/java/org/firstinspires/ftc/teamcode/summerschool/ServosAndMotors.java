package org.firstinspires.ftc.teamcode.summerschool;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class ServosAndMotors extends OpMode {

    private DcMotor motor = null;
    private Servo rtpServo = null;
    private CRServo crServo = null;
    private float rtpServoPos = 0.0f;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "motor");
        rtpServo = hardwareMap.get(Servo.class, "rtpservo");
        crServo = hardwareMap.get(CRServo.class, "crservo");
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_x) > 0.1) {
            motor.setPower(gamepad1.left_stick_x);
        }
        else {
            motor.setPower(0.0f);
        }

        // You can see that the crServo is run exactly like a motor
        if (Math.abs(gamepad1.right_stick_x) > 0.1) {
            crServo.setPower(gamepad1.left_stick_x);
        }
        else {
            crServo.setPower(0.0f);
        }

        if (gamepad1.a) {
            rtpServoPos -= 0.1f;
        }
        else if (gamepad1.y) {
            rtpServoPos += 0.1f;
        }

        if (rtpServoPos > 1) rtpServoPos = 1;
        if (rtpServoPos < 0) rtpServoPos = 0;

        rtpServo.setPosition(rtpServoPos);
    }
}
