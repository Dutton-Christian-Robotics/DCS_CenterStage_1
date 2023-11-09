package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor.PropPosition;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Disabled
@Autonomous(name = "Near 1 Autonomous", group = "Near")
public class KentwoodAutonomousNear1OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    @Override
    public void whenRedAlliance() {

	   if (position == PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {
		  bot.navigation.driveToPosition(0, -52, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -12, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 4, 4000);
		  bot.navigation.resetAndDriveToPosition(0, 55, 0, 0.1);

		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -9, 0, 0.2);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   } else if (position == PropPosition.LEFT) {
		  bot.navigation.driveToPosition(0, -32, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 4000);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.gotoTravelArmPosition();
		  sleep(1500);
		  bot.navigation.resetAndDriveToPosition(0, -43, 0, 0.15);
		  bot.gotoHighBackDeliveryPosition();
		  sleep(2000);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.gotoStartArmPosition();
		  sleep(2000);


	   } else if (position == PropPosition.RIGHT) {
		  bot.navigation.driveToPosition(-20, 0, 0, 0.3);
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.gotoTravelArmPosition();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -6, 0, 0.2);

		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 4000);

		  bot.navigation.resetAndDriveToPosition(0, 37, 0, 0.2);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -9, 0, 0.2);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   }
    }

    @Override
    public void whenBlueAlliance() {
	   if (position == PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {
		  bot.navigation.driveToPosition(0, -52, 0, 0.1);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -16, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(-90, 0.3, 4, 4000);
		  bot.navigation.resetAndDriveToPosition(0, 55, 0, 0.1);

		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -9, 0, 0.2);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   } else if (position == PropPosition.LEFT) {
		  bot.navigation.driveToPosition(22, 0, 0, 0.3);
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.gotoTravelArmPosition();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -10, 0, 0.2);

		  bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 4000);

		  bot.navigation.resetAndDriveToPosition(0, 35, 0, 0.2);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -9, 0, 0.2);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   } else if (position == PropPosition.RIGHT) {
		  bot.navigation.driveToPosition(0, -32, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 4000);
		  bot.gotoTravelArmPosition();
		  sleep(1500);
		  bot.navigation.resetAndDriveToPosition(0, 5, 0, 0.4);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, -42, 0, 0.2);
		  bot.gotoHighBackDeliveryPosition();
		  sleep(2000);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.navigation.resetAndDriveToPosition(0, 9, 0, 0.2);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   }

    }

}