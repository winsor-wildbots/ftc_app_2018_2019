package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by lamanwyner on 1/4/19.
 */

@TeleOp
public class TestDrawerStop extends LinearOpMode {
    Servo drawerStopServo;

    @Override
    public void runOpMode() throws InterruptedException {
        drawerStopServo = hardwareMap.get(Servo.class, "drawerStopServo");
        float pos = 0;

        waitForStart();

        while (opModeIsActive()) {
            drawerStopServo.setPosition(pos);
            if (gamepad1.dpad_up) {
                pos += 0.01;
            } else if (gamepad1.dpad_down) {
                pos -= 0.01;
            }
            telemetry.addData("Drawer Stop Servo Position", pos);
            telemetry.update();
        }
    }
}
