package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.libraries.DrivingLibrary;

@Autonomous
public class AutonLand extends LinearOpMode {

    DrivingLibrary drivingLibrary;
    int drivingMode;

    // latch arm
    DcMotor latchArm;

    // intake arm
    CRServo intakeSpinServo;
    Servo intakeFlipServo;
    Servo intakeExtendArm;
    DcMotor intakeRotateArm;

    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setSpeed(1);
        drivingMode = 0;
        drivingLibrary.setMode(drivingMode);

        // intake cr servo: rev hub 1 servo port 0
        intakeSpinServo = hardwareMap.get(CRServo.class, "intakeSpinServo");

        // intake flip servo: rev hub 1 servo port 1
        intakeFlipServo = hardwareMap.get(Servo.class, "intakeFlipServo");

        // intake arm extending winch servo: rev hub 1 servo port 2
        intakeExtendArm = hardwareMap.get(Servo.class, "intakeExtendArm");

        // intake arm rotational dc motor: rev hub 1 motor port 1
        intakeRotateArm = hardwareMap.get(DcMotor.class, "intakeRotateArm");

        // latch motor: rev hub 1 motor port 0
        latchArm = hardwareMap.get(DcMotor.class, "latchArm");

        latchArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRotateArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        boolean ranOnce = false;

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        while (opModeIsActive()){
            if (!ranOnce){
                //land
                latchArm.setPower(.75);
                sleep(7000);
                latchArm.setPower(0);

                //reset arm
                latchArm.setPower(-.75);
                sleep(5000);


            }
            ranOnce = true;
        }
    }

}
