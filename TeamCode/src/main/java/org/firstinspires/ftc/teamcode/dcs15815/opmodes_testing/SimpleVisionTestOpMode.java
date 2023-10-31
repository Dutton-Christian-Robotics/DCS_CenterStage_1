package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp(name = "Simple Vision Test", group = "Testing")
public class SimpleVisionTestOpMode extends LinearOpMode {
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTagProcessor;
    @Override
    public void runOpMode() {
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, aprilTagProcessor);

        while (opModeInInit()) {
            List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
            StringBuilder idsFound = new StringBuilder();
            for (AprilTagDetection detection : currentDetections) {
                idsFound.append(detection.id);
                idsFound.append(" ");
            }
            telemetry.addData("April Tags", idsFound);
            telemetry.update();
        }
        visionPortal.stopStreaming();
//        waitForStart();

        while (!isStopRequested()) {

        }



    }
}