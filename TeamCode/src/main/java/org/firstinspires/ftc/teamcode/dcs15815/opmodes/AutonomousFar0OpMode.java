package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;

@Autonomous(name = "Far 0 Autonomous", group = "Far")
public class AutonomousFar0OpMode extends PropDetectingOpMode {

    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    @Override
    public void whenRedAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE) {
		  bot.navigation.driveToPosition(0, -52, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);
		  bot.gotoStartArmPosition();
		  sleep(2000);


	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {
		  bot.drivetrain.drive(0, -0.25, 0);
		  sleep(460);
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.gotoTravelArmPosition();
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);
		  bot.gotoStartArmPosition();
		  sleep(2000);


	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {
		  bot.navigation.driveToPosition(0, -32, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 4000);
		  bot.gotoTravelArmPosition();
		  sleep(1500);
		  bot.stickyPad.releaseRight();
		  sleep(2000);

		  // Move to center field and turn towards backstage
		  bot.navigation.resetAndDriveToPosition(-15, 0, 0, 0.1);
		  bot.gotoStartArmPosition();
		  sleep(2000);

	   }

    }

    @Override
    public void whenBlueAlliance() {

    }
}
