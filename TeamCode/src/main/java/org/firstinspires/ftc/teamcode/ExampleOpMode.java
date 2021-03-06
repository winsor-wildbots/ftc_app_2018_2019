package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lamanwyner on 4/26/19.
 */

@TeleOp
public class ExampleOpMode extends LinearOpMode {
    DcMotor left;
    DcMotor right;
    Servo servo;

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        servo = hardwareMap.get(Servo.class, "servo");

        left.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            left.setPower(-gamepad1.left_stick_y);

            if (gamepad1.a) {
                servo.setPosition(0);
            }
            if (gamepad1.b) {
                servo.setPosition(1);
            }
        }
    }
}
