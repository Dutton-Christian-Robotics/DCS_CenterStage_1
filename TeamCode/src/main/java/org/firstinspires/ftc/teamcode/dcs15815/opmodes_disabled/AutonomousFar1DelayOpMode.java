package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Disabled
@Autonomous(name = "Far 1 Delay Autonomous", group = "Far")
public class AutonomousFar1DelayOpMode extends PropDetectingOpMode {

    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    @Override
    public void whenRedAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {
		  bot.navigation.driveToPosition(0, -52, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);

		  sleep(10000);


		  bot.navigation.resetAndDriveToPosition(0, 86, 0, 0.1);
		  bot.navigation.resetAndDriveToPosition(25, 0, 0, 0.35);
		  bot.navigation.resetAndDriveToPosition(0, 7, 0, 0.2);

		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  bot.gotoStartArmPosition();
		  sleep(2000);



	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {
		  bot.drivetrain.drive(0, 0.25, 0);
		  sleep(460);
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);

		  sleep(10000);

		  bot.navigation.resetAndDriveToPosition(0, -75, 0, 0.15);
		  bot.navigation.resetAndDriveToPosition(18, 0, 0, 0.5);
		  bot.gotoHighBackDeliveryPosition();
		  sleep(2500);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.gotoStartArmPosition();
		  sleep(2000);



	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {
		  bot.navigation.resetAndDriveToPosition(0, -30, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 4000);
		  bot.navigation.resetAndDriveToPosition(0, -32, 0, 0.1);
		  bot.gotoTravelArmPosition();
		  sleep(1500);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  sleep(10000);


		  // Drive across field
		  bot.navigation.resetAndDriveToPosition(0, 95, 0, 0.1);
		  bot.navigation.resetAndDriveToPosition(25, 0, 0, 0.35);
		  bot.navigation.resetAndDriveToPosition(0, 10, 0, 0.2);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  bot.gotoStartArmPosition();
		  sleep(2000);


	   }
    }

    @Override
    public void whenBlueAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {
		  bot.navigation.driveToPosition(0, -52, 0, 0.1);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(-90, 0.3, 1, 5000);


		  sleep(10000);

		  // Drive across field
		  bot.navigation.resetAndDriveToPosition(0, 86, 0, 0.1);
		  bot.navigation.resetAndDriveToPosition(-25, 0, 0, 0.35);
		  bot.navigation.resetAndDriveToPosition(0, 7, 0, 0.2);

		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  bot.gotoStartArmPosition();
		  sleep(2000);



	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {

		  bot.navigation.resetAndDriveToPosition(0, -33, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 4000);
		  bot.navigation.resetAndDriveToPosition(0, -28, 0, 0.1);
		  bot.gotoTravelArmPosition();
		  sleep(1500);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);

		  sleep(10000);


		  bot.navigation.resetAndDriveToPosition(0, -75, 0, 0.15);
		  bot.navigation.resetAndDriveToPosition(-18, 0, 0, 0.5);
		  bot.gotoHighBackDeliveryPosition();
		  sleep(2500);
		  bot.stickyPad.releaseRight();
		  sleep(2000);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {
		  bot.drivetrain.drive(0, -0.25, 0);
		  sleep(460);
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
		  bot.stickyPad.releaseLeft();
		  sleep(2000);



		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -20, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(-95, 0.3, 1, 5000);

		  sleep(10000);

		  // Drive across field
		  bot.navigation.resetAndDriveToPosition(0, 95, 0, 0.1);
		  bot.navigation.resetAndDriveToPosition(-25, 0, 0, 0.35);
		  bot.navigation.resetAndDriveToPosition(0, 10, 0, 0.2);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  bot.gotoStartArmPosition();
		  sleep(2000);



	   }
    }
}
