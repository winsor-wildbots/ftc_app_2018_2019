package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestWinchServo extends LinearOpMode {
    Servo winchServo;

    public void runOpMode() throws InterruptedException {
        winchServo = hardwareMap.get(Servo.class, "winchServo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        boolean forward = true;

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.a)  winchServo.setPosition(.7);
            if (gamepad1.b) winchServo.setPosition(.2);

            telemetry.addData("Status", "Running");
            telemetry.addData("winch pos", winchServo.getPosition());

            telemetry.update();
        }
    }
}
