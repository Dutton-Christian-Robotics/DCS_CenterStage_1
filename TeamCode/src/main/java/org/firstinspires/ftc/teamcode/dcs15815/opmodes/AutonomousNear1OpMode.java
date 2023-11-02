package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
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
        bot.opMode = this;

        bot.stickyPad.gotoGrabPosition();
        bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
        bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_DROP_POSITION);

        while (opModeInInit()) {
            telemetry.addData("Position", bot.vision.getDetectedPosition());
            telemetry.addData("Hue", bot.vision.getDetectedHue());
            telemetry.addData("Alliance", bot.vision.getDetectedAlliance());
            telemetry.update();
//            position = bot.vision.getDetectedPosition();
//            bot.alliance = bot.vision.getDetectedAlliance();

            bot.alliance = DefenderBot.Alliance.RED;
            if (gamepad1.dpad_left) {
                position = PropPosition.LEFT;
            } else if (gamepad1.dpad_right) {
                position = PropPosition.RIGHT;
            } else if (gamepad1.dpad_up) {
                position = PropPosition.MIDDLE;
            }



            bot.leds.setAllianceColors();
        }
        bot.vision.stopStreaming();


        if (bot.alliance == DefenderBot.Alliance.RED) {

            if (position == PropPosition.MIDDLE) {
                bot.navigation.driveToPosition(0, -52, 0, 0.1);
                bot.stickyPad.releaseRight();
                sleep(2000);
                bot.gotoTravelArmPosition();
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, -20, 0, 0.1);
                sleep(1500);
                bot.navigation.comeToRelativeHeading(40, 0.3, 4, 4000);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, 55, 0, 0.1);
//                bot.drivetrain.drive(0.1, 0, 0);
//                sleep(2700);
                bot.stopDriving();
                bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);
                sleep(2000);
                bot.navigation.comeToRelativeHeading(55, 0.3, 4, 4000);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, 10, 0, 0.3);

                bot.stickyPad.releaseLeft();
                sleep(2000);
                bot.gotoArmPosition(SBBConfiguration.HIGH_FRONT_DELIVERY_POSITION);
                sleep(2000);

            } else if (position == PropPosition.LEFT) {
                bot.navigation.driveToPosition(0, -32, 0, 0.1);
                bot.stopDriving();
                bot.sleep(1000);
                bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 4000);
                sleep(800);
                bot.stopDriving();
                bot.gotoTravelArmPosition();
                sleep(1500);
                bot.stickyPad.releaseRight();
                sleep(2000);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, -40, 0, 0.1);
                bot.stopDriving();
                bot.gotoArmPosition(SBBConfiguration.HIGH_BACK_DELIVERY_POSITION);
                sleep(2000);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, -6, 0, 0.1);
                bot.stopDriving();
                bot.stickyPad.releaseLeft();
                sleep(4000);
                bot.gotoTravelArmPosition();
                sleep(2000);


            } else if (position == PropPosition.RIGHT) {
                bot.drivetrain.drive(0, -0.25, 0);
                sleep(460);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, -47, 0, 0.1);
                sleep(1000);
//                bot.navigation.driveToPosition(-10, -52, 0, 0.1);
                bot.stickyPad.releaseRight();
                sleep(2000);
                bot.gotoTravelArmPosition();
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, -15, 0, 0.1);
                sleep(600);

//                bot.drivetrain.drive(0, -0.25, 0);
//                sleep(800);

                bot.navigation.comeToRelativeHeading(35, 0.5, 1, 4000);
                bot.drivetrain.drive(0.1, 0, 0);
                sleep(2500);
                bot.drivetrain.stopDriving();
                bot.gotoArmPosition(SBBConfiguration.MID_FRONT_DELIVERY_POSITION);
                sleep(1000);
                bot.navigation.resetPositionTracking();
                bot.navigation.driveToPosition(0, 40, 0, 0.1);
                bot.navigation.comeToRelativeHeading(65, 0.35, 1, 4000);
                sleep(300);
                bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);
                sleep(1500);
                bot.drivetrain.stopDriving();
                bot.stickyPad.releaseLeft();
                sleep(2000);
                bot.gotoArmPosition(SBBConfiguration.MID_FRONT_DELIVERY_POSITION);
                sleep(2000);

            }
        } else if (bot.alliance == DefenderBot.Alliance.BLUE) {
            if (position == PropPosition.MIDDLE) {
            } else if (position == PropPosition.LEFT) {
            } else if (position == PropPosition.RIGHT) {
            }
        }

    }
}