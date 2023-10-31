package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor.PropPosition;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;
import org.firstinspires.ftc.vision.VisionPortal;

@Autonomous(name = "Near 1 Autonomous", group = "Near")
public class AutonomousNear1OpMode extends LinearOpMode {
    StickyBanditBot bot;
    private VisionPortal visionPortal;
    private PropVisionProcessor detectionProcessor;
    private PropVisionProcessor.PropPosition position;


    public void runOpMode() {
        bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
        detectionProcessor = new PropVisionProcessor();
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");
        visionPortal = VisionPortal.easyCreateWithDefaults(webcamName, detectionProcessor);

        bot.stickyPad.gotoGrabPosition();
        bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
        bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_DROP_POSITION);

        while (opModeInInit()) {
            telemetry.addData("Position", detectionProcessor.getPosition());
            telemetry.addData("Hue", detectionProcessor.getDetectedHue());
            telemetry.addData("Alliance", detectionProcessor.getAlliance());
            telemetry.update();
            position = detectionProcessor.getPosition();

        }
        visionPortal.stopStreaming();

        if (position == PropPosition.MIDDLE) {
            bot.navigation.driveToPosition(0, -14);
//            bot.drivetrain.drive(-0.35, 0, 0);
//            sleep(1200);
//            bot.stopDriving();

        } else if (position == PropPosition.LEFT) {


        } else if (position == PropPosition.RIGHT) {

        }

//          sleep(1700);
//        bot.stopDriving();
    }
}