package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor.PropPosition;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;

@Autonomous(name = "Near 2 Autonomous", group = "Near")
public class AutonomousNear2OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
        bot.stickyPad.gotoGrabPosition();
        bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
        bot.gotoAutonomousDropArmPosition();
    }

    @Override
    public void whenRedAlliance() {

        if (position == PropPosition.MIDDLE) {
            bot.navigation.driveToPosition(0, -52, 0, 0.1);
            bot.stickyPad.releaseRight();
            sleep(2000);
            bot.gotoTravelArmPosition();
            bot.navigation.resetAndDriveToPosition(0, -20, 0, 0.1);
            bot.navigation.comeToRelativeHeading(40, 0.3, 4, 4000);
            bot.navigation.resetAndDriveToPosition(0, 55, 0, 0.1);
//                bot.drivetrain.drive(0.1, 0, 0);
//                sleep(2700);
            bot.gotoAutonomousFrontDeliveryArmPosition();
            sleep(2000);
            bot.navigation.comeToRelativeHeading(55, 0.3, 4, 4000);
            bot.navigation.resetAndDriveToPosition(0, 10, 0, 0.3);

            bot.stickyPad.releaseLeft();
            sleep(2000);
            bot.gotoHighFrontDeliveryPosition();
            sleep(2000);

        } else if (position == PropPosition.LEFT) {
            bot.navigation.driveToPosition(0, -32, 0, 0.1);
            bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 4000);
            bot.gotoTravelArmPosition();
            sleep(1500);
            bot.stickyPad.releaseRight();
            sleep(2000);
            bot.navigation.resetAndDriveToPosition(0, -40, 0, 0.1);
            bot.gotoHighBackDeliveryPosition();
            sleep(2000);
            bot.navigation.resetAndDriveToPosition(0, -6, 0, 0.1);
            bot.stickyPad.releaseLeft();
            sleep(2000);
            bot.gotoTravelArmPosition();
            sleep(2000);


        } else if (position == PropPosition.RIGHT) {
            bot.drivetrain.drive(0, -0.25, 0);
            sleep(460);
            bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
            bot.stickyPad.releaseRight();
            sleep(2000);
            bot.gotoTravelArmPosition();
            bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);

//                bot.drivetrain.drive(0, -0.25, 0);
//                sleep(800);

            bot.navigation.comeToRelativeHeading(35, 0.5, 1, 4000);
            bot.drivetrain.drive(0.1, 0, 0);
            sleep(2500);
            bot.drivetrain.stopDriving();
            bot.gotoMidFrontDeliveryPosition();
            sleep(1000);
            bot.navigation.resetAndDriveToPosition(0, 40, 0, 0.1);
            bot.navigation.comeToRelativeHeading(65, 0.35, 1, 4000);
            bot.gotoAutonomousFrontDeliveryArmPosition();
            sleep(1500);
            bot.stickyPad.releaseLeft();
            sleep(2000);
            bot.gotoMidFrontDeliveryPosition();
            sleep(2000);

        }
    }

    @Override
    public void whenBlueAlliance() {
        if (position == PropPosition.MIDDLE) {
        } else if (position == PropPosition.LEFT) {
        } else if (position == PropPosition.RIGHT) {
        }

    }

}