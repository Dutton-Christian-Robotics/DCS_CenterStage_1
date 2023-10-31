package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name = "Detection Vision Test", group = "Testing")
public class DetectionVisionTestOpMode extends LinearOpMode {
    private VisionPortal visionPortal;
    private PropVisionProcessor detectionProcessor;
    @Override
    public void runOpMode() {
	   WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
	   detectionProcessor = new PropVisionProcessor();
	   visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, detectionProcessor);

	   while (opModeInInit()) {
//		  List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
//		  StringBuilder idsFound = new StringBuilder();
//		  for (AprilTagDetection detection : currentDetections) {
//			 idsFound.append(detection.id);
//			 idsFound.append(" ");
//		  }
//		  telemetry.addData("April Tags", idsFound);
//		  telemetry.update();

		  telemetry.addData("Identified", detectionProcessor.getPosition());
		  telemetry.addData("Hue", detectionProcessor.getDetectedHue());
		  telemetry.addData("Alliance", detectionProcessor.getAlliance());
		  telemetry.update();

	   }
	   visionPortal.stopStreaming();
//        waitForStart();

	   while (!isStopRequested()) {
		  telemetry.addData("Identified", detectionProcessor.getPosition());
		  telemetry.update();
	   }



    }
}