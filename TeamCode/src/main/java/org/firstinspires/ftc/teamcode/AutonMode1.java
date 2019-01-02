package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.libraries.DrivingLibrary;

@Autonomous
public class AutonMode1 extends LinearOpMode {
    DrivingLibrary drivingLibrary;
    DcMotor latchArm;

    int drivingMode;

    public void runOpMode() throws InterruptedException {
        // initialize the driving library
        drivingLibrary = new DrivingLibrary(this);
        drivingMode = 0;

        drivingLibrary.setSpeed(.5);
        drivingLibrary.setMode(drivingMode);

        // initialize the arm
        latchArm = hardwareMap.get(DcMotor.class, "latchArm");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        boolean run = false;
        waitForStart();

        while (opModeIsActive()) {
            if (run) {
                // land
                latchArm.setPower(-.75);
                sleep(5000);

                // strafe to the side
                drivingLibrary.driveStraight(-.5f, 0);

                // reset landing arm
                latchArm.setPower(.75);

                // knock off yellow jewel - 25?



                // place marker - 15?




                telemetry.addData("auton ran?", "yes");
                telemetry.update();
                run = false;
            }
            sleep(1000);
            telemetry.addData("auton off", "yes");
            telemetry.update();
        }
    }
}
