package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestIntake extends LinearOpMode {
    DcMotor testIntake;

    public void runOpMode () throws InterruptedException {

        testIntake = hardwareMap.get(DcMotor.class, "testIntake");

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                testIntake.setPower(-.5);
            }
            if (gamepad1.b){
                testIntake.setPower(0);
            }
        }

    }
}
