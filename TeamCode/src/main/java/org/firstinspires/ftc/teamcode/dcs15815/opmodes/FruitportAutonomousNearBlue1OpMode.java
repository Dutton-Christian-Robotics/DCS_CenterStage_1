package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Autonomous(name = "Blue Near 1 Autonomous", group = "Near")
public class FruitportAutonomousNearBlue1OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   super.setupRobot();
	   bot.alliance = DefenderBot.Alliance.BLUE;
    }

    @Override
    public void whenBlueAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {

		  // Drive backwards away from wall
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);
//		  bot.gotoTravelArmPosition();

		  // Turn 180 CCW in two increments
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Release the purple pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);

		  // Drive backwards from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -11, 0, 0.2);

		  // Turn 90 degrees CCW
		  bot.navigation.comeToRelativeHeading(90, 0.5, 1, 5000);

		  // Get arm into delivery position
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);

		  // Drive forward to the board
		  bot.navigation.resetAndDriveToPosition(0, 20, 0, 0.1);

		  // Strafe right slightly for better pixel delivery
		  bot.navigation.resetAndDriveToPosition(14, 0, 0, 0.4);

		  // Drive forward to the board
		  bot.navigation.resetAndDriveToPosition(0, 26, 0, 0.1);

		  // Release the yellow pixel and tilt wrist up
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_RELEASE_POSITION);


		  // Back away from the board
		  bot.navigation.resetAndDriveToPosition(0, -8, 0, 0.2);

		  // Strafe left to get out of the way and reset the arm
		  bot.navigation.resetAndDriveToPosition(-26, 0, 0, 0.4);
		  bot.gotoStartArmPosition();

	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {

		  // Drive backwards away from wall
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);

		  // Turn 90 CCW
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Drive backward away from the stripe
		  bot.navigation.resetAndDriveToPosition(0, -21, 0, 0.1);

		  // Release the purple pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);

		  // Drive backward away from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -16, 0, 0.1);

		  // Turn 180 CCW in two increments
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Get arm into delivery position
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);

		  // Strafe left slightly for better pixel delivery
		  bot.navigation.resetAndDriveToPosition(-14, 0, 0, 0.45);

		  // Drive forward to the board
		  bot.navigation.resetAndDriveToPosition(0, 14, 0, 0.1);

		  // Release the yellow pixel and tilt wrist up
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_RELEASE_POSITION);


		  // Back away from the board
		  bot.navigation.resetAndDriveToPosition(0, -10, 0, 0.2);

		  // Strafe left to get out of the way and reset the arm
		  bot.navigation.resetAndDriveToPosition(-20, 0, 0, 0.4);
		  bot.gotoStartArmPosition();


	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {

		  // Drive backwards away from wall
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);

		  // Turn 90 CCW
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Approach the stripe
		  bot.navigation.resetAndDriveToPosition(0, 8, 0, 0.2);

		  // Release the purple pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);

		  // Drive backward away from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -30, 0, 0.1);

		  // Turn 180 CCW in two increments
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Get arm into delivery position
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);

		  // Strafe right slightly for better pixel delivery
		  bot.navigation.resetAndDriveToPosition(6.5, 0, 0, 0.4);

		  // Drive forward to the board
		  bot.navigation.resetAndDriveToPosition(0, 20, 0, 0.1);

		  // Release the yellow pixel and tilt wrist up
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_RELEASE_POSITION);


		  // Back away from the board
		  bot.navigation.resetAndDriveToPosition(0, -10, 0, 0.2);

		  // Strafe left to get out of the way and reset the arm
		  bot.navigation.resetAndDriveToPosition(-32, 0, 0, 0.4);
		  bot.gotoStartArmPosition();


	   }

    }
}