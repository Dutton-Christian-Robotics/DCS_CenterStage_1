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
    private PropVisionProcessor.PropPosition position;


    public void runOpMode() {
        bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

        bot.stickyPad.gotoGrabPosition();
        bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
        bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_DROP_POSITION);

        while (opModeInInit()) {
            telemetry.addData("Position", bot.vision.getDetectedPosition());
            telemetry.addData("Hue", bot.vision.getDetectedHue());
            telemetry.addData("Alliance", bot.vision.getDetectedAlliance());
            telemetry.update();
            position = bot.vision.getDetectedPosition();
            bot.alliance = bot.vision.getDetectedAlliance();
            bot.leds.setAllianceColors();

        }
        bot.vision.stopStreaming();

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