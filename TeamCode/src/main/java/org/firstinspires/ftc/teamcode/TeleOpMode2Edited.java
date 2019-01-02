package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;

@TeleOp
public class TeleOpMode2Edited extends LinearOpMode {
    // initialize all our individual variables
    DrivingLibrary drivingLibrary;
    int drivingMode;
    DcMotor latchArm;
    CRServo intakeServo;
    Servo intakeFlipServo;
    Servo intakeExtendArm;
    DcMotor intakeRotateArm;

    public void runOpMode() throws InterruptedException {
        // set up our driving library
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);
        drivingMode = 0;
        drivingLibrary.setMode(drivingMode);

        // intake cr servo
        intakeServo = hardwareMap.get(CRServo.class, "intakeServo");

        // intake flip servo
        intakeFlipServo = hardwareMap.get(Servo.class, "intakeFlipServo");

        // intake arm extending winch servo
        intakeExtendArm = hardwareMap.get(Servo.class, "winchServo");

        // intake arm rotational dc motor
        intakeRotateArm = hardwareMap.get(DcMotor.class, "intakeRotateArm");

        // latch motor
        latchArm = hardwareMap.get(DcMotor.class, "latchArm");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // game pad 1: driving and latch

            // switch driving modes manually
            if (gamepad1.b) {
                drivingMode++;
                drivingMode %= DrivingMode.values().length;
                drivingLibrary.setMode(drivingMode);
            }

            // drive straight
            drivingLibrary.driveStraight(gamepad1.left_stick_x, -gamepad1.left_stick_y);
            drivingLibrary.turn(gamepad1.right_stick_x, -gamepad1.right_stick_y);

            // control latch arm
            if (gamepad1.dpad_up) {
                latchArm.setPower(.75);
            } else if (gamepad1.dpad_down) {
                latchArm.setPower(-.75);
            } else {
                latchArm.setPower(0);
            }

            // gamepad 2: intake and intake arm

            // spinning intake servo
            if (gamepad2.a) {
                intakeServo.setPower(1);
            } else if (gamepad2.b) {
                intakeServo.setPower(-1);
            } else {
                intakeServo.setPower(0);
            }

            // extend intake arm (winch servo)
            if (gamepad2.dpad_right) {
                intakeExtendArm.setPosition(.7);
            } else if (gamepad2.dpad_left) {
                intakeExtendArm.setPosition(.2);
            }

            // rotate intake arm (motor)
            if (gamepad2.dpad_up) {
                intakeRotateArm.setPower(.5);
            } else if (gamepad2.dpad_down) {
                intakeRotateArm.setPower(-.5);
            } else {
                intakeRotateArm.setPower(0);
            }

            // flip servo
            /* TODO: write code */

            telemetry.addData("Status", "Running");
            telemetry.addData("Brake Mode", drivingLibrary.getMode());

            telemetry.update();
        }
    }
}
