package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TestIntakeArm extends LinearOpMode {
    CRServo intakeSpinServo;
    Servo intakeFlipServo;
    Servo intakeExtendArm;
    DcMotor intakeRotateArm;

    public void runOpMode() throws InterruptedException {
        intakeExtendArm = hardwareMap.get(Servo.class, "intakeExtendArm");
        intakeSpinServo = hardwareMap.get(CRServo.class, "intakeSpinServo");
        intakeFlipServo = hardwareMap.get(Servo.class, "intakeFlipServo");
        intakeRotateArm = hardwareMap.get(DcMotor.class, "intakeRotateArm");

        intakeRotateArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // test the intake arm
            // extend, rotate arm, flip box, rotate intake hand

            // spinning intake servo
            if (gamepad2.a) {
                intakeSpinServo.setPower(1);
            } else if (gamepad2.b) {
                intakeSpinServo.setPower(-1);
            } else {
                intakeSpinServo.setPower(0);
            }

            // extend intake arm (winch servo)
            if (gamepad2.dpad_right) {
                intakeExtendArm.setPosition(.7);
            } else if (gamepad2.dpad_left) {
                intakeExtendArm.setPosition(.2);
            }

            // rotate intake arm (motor)
            if (gamepad2.dpad_up) {
                intakeRotateArm.setPower(1);
            } else if (gamepad2.dpad_down) {
                intakeRotateArm.setPower(-1);
            } else {
                intakeRotateArm.setPower(0);
            }

            // flip servo
            if (gamepad2.x) {
                intakeFlipServo.setPosition(.6);
            } else if (gamepad2.y) {
                intakeFlipServo.setPosition(.3);
            }

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}