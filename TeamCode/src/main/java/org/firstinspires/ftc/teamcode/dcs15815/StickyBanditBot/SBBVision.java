package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import org.firstinspires.ftc.vision.VisionPortal;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;


public class SBBVision extends DefenderBotSystem {
    private VisionPortal visionPortal;
    private WebcamName webcamName;
    private PropVisionProcessor detectionProcessor;
    private PropVisionProcessor.PropPosition position;
    private DefenderBot.Alliance alliance;

    public SBBVision(HardwareMap hm, DefenderBot b) {
	   super(hm, b);
	   webcamName = hm.get(WebcamName.class, SBBConfiguration.CAMERA_NAME);
	   detectionProcessor = new PropVisionProcessor();
	   visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, detectionProcessor);

    }

    public PropVisionProcessor.PropPosition getDetectedPosition() {
	   return position;
    }

    public DefenderBot.Alliance getDetectedAlliance() {
	   return alliance;
    }

    public double getDetectedHue() {
	   return detectionProcessor.getDetectedHue();
    }

    public void stopStreaming() {
	   visionPortal.stopStreaming();
    }



}