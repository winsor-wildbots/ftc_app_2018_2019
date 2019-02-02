package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.libraries.DrivingLibrary;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "Simple Mineral Sampling", group = "In Progress")
public class SimpleMineralSampling extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";

    private static final String VUFORIA_KEY = "AYOTCgT/////AAABmewkUXExAUVzskAoLuha7w9lfk/CuSoaWXjaMtTsIKpGAyXJ+18HzPsnSewFBAtMzZABHnGc3ojJimkfSVONSkh59LkxPebzR34qCnDr+m1ybQeVjTRobnNZts+W/tgDDMAnbCEsOpI8nQuCjPwUBBTm6SkS8ApJ4eTrXLlsSKVwQ8y7X1LNCS1rA2U9PevNBiiu5sI76rLIijmZ72iifKgHnPjDLWHJqPI+a1dOcx9L0+2L4KqTC+iX3W1Y31D5IXtSJU9bSIAnA0SaWqRDfiRaSre8PU7GW14cfeXj/YnHz18mM2KIaytZbmiXx2s9GNTd+6DAwhxE081eYEN0ecggbEh2TvoZT/BtmCqvPq35";

    VuforiaLocalizer vuforia;
    TFObjectDetector tfod;
    DrivingLibrary drivingLibrary;

    @Override
    public void runOpMode() throws InterruptedException {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        }
        else {
            stop();
        }

        drivingLibrary = new DrivingLibrary(this);

        waitForStart();

        if (opModeIsActive()) {
            tfod.activate();

            while(opModeIsActive()) {
                List<Recognition> recognitions = tfod.getUpdatedRecognitions();
            }
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
