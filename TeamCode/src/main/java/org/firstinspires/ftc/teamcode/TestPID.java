package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * Created by lamanwyner on 1/25/19.
 */

@TeleOp(name = "Test PID Constants for Mineral Sampling", group = "Test")
public class TestPID extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";

    private static final String VUFORIA_KEY = "AYOTCgT/////AAABmewkUXExAUVzskAoLuha7w9lfk/CuSoaWXjaMtTsIKpGAyXJ+18HzPsnSewFBAtMzZABHnGc3ojJimkfSVONSkh59LkxPebzR34qCnDr+m1ybQeVjTRobnNZts+W/tgDDMAnbCEsOpI8nQuCjPwUBBTm6SkS8ApJ4eTrXLlsSKVwQ8y7X1LNCS1rA2U9PevNBiiu5sI76rLIijmZ72iifKgHnPjDLWHJqPI+a1dOcx9L0+2L4KqTC+iX3W1Y31D5IXtSJU9bSIAnA0SaWqRDfiRaSre8PU7GW14cfeXj/YnHz18mM2KIaytZbmiXx2s9GNTd+6DAwhxE081eYEN0ecggbEh2TvoZT/BtmCqvPq35";

    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private DrivingLibrary drivingLibrary;

    @Override
    public void runOpMode() throws InterruptedException {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        drivingLibrary = new DrivingLibrary(this);

        double P = 1;

        waitForStart();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int nGoldMinerals = 0;
                        double goldX = 0;

                        for (Recognition recognition : updatedRecognitions) {
                            if (recognition.getLabel() == LABEL_GOLD_MINERAL) {
                                nGoldMinerals++;
                            }
                        }

                        if (nGoldMinerals == 0) {
                            telemetry.addLine("No Gold Minerals Detected");
                        } else if (nGoldMinerals > 1) {
                            telemetry.addLine("Too Many Gold Minerals Detected");
                        } else {
                            Recognition mineral = updatedRecognitions.get(0);
                            goldX = (mineral.getLeft() + mineral.getRight()) / 2;

                            drivingLibrary.turn();
                        }
                        telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL);
    }
}
