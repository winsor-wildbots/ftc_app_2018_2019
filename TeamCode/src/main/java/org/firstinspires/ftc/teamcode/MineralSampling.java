package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.enums.DrivingMode;
import org.firstinspires.ftc.libraries.DrivingLibrary;

/**
 * Created by lamanwyner on 2/9/19.
 */

@Autonomous(name = "Simple Mineral Sampling", group = "In Progress")
public class MineralSampling extends LinearOpMode {
    DrivingLibrary drivingLibrary;

    DcMotor latchArm;

    @Override
    public void runOpMode() throws InterruptedException {
        drivingLibrary = new DrivingLibrary(this);
        drivingLibrary.setMode(DrivingMode.BRAKE_STOP);
        drivingLibrary.setSpeed(1);

        latchArm = hardwareMap.get(DcMotor.class, "latchArm");

        waitForStart();

        while (opModeIsActive()) {
            latchArm.setPower(1);
            sleep(23000);

            
        }
    }
}
