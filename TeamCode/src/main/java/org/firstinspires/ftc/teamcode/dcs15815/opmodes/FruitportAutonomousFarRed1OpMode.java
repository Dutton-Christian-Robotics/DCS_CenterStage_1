package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Autonomous(name = "Red Far 1 Autonomous", group = "Far")
public class FruitportAutonomousFarRed1OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   super.setupRobot();
	   bot.alliance = DefenderBot.Alliance.RED;
    }

    @Override
    public void whenRedAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {

		  // Drive backwards away from wall
		  bot.navigation.resetAndDriveToPosition(0, -47, 0, 0.1);


		  // Release the purple pixel
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);

		  // Drive backwards from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -10, 0, 0.2);

		  // Turn 90 degrees CCW
		  bot.navigation.comeToRelativeHeading(90, 0.5, 1, 5000);


		  // Drive forward under the stage door to the backstage
		  bot.navigation.resetAndDriveToPosition(0, 90, 0, 0.1);

		  // Get arm into delivery position
		  bot.gotoTravelArmPosition();


		  // Release the yellow pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);


		  // Back away from the pixel
		  bot.navigation.resetAndDriveToPosition(0, -8, 0, 0.2);

		  // reset the arm
		  bot.gotoStartArmPosition();

	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {
		  // Drive backwards away from wall
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);

		  // Turn 90 CCW
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Backup to delivery position
		  bot.navigation.resetAndDriveToPosition(0, -14, 0, 0.2);

		  // Release the purple pixel
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);

		  // Drive backward away from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -8, 0, 0.2);

		  // Strafe to the left for crossfield transit
		  bot.navigation.resetAndDriveToPosition(-35, 0, 0, 0.3);

		  // Drive forward under the stage door to the backstage
		  bot.navigation.resetAndDriveToPosition(0, 110, 0, 0.1);


		  // Get arm into delivery position
		  bot.gotoTravelArmPosition();

		  // Strafe right slightly for better pixel delivery
		  // bot.navigation.resetAndDriveToPosition(11, 0, 0, 0.4);

		  // Release the yellow pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);


		  // Back away from the pixel
		  bot.navigation.resetAndDriveToPosition(0, -8, 0, 0.2);

		  // reset the arm
		  bot.gotoStartArmPosition();



	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {

		  // Drive backward away from wall
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);

		  // Turn 90 CCW
		  bot.navigation.comeToRelativeHeading(90, 1, 1, 5000);

		  // Approach the stripe
		  bot.navigation.resetAndDriveToPosition(0, 8, 0, 0.2);


		  // Release the purple pixel
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);

		  // Drive backward away from the purple pixel
		  bot.navigation.resetAndDriveToPosition(0, -15, 0, 0.1);


		  // Strafe to the left for crossfield transit
		  bot.navigation.resetAndDriveToPosition(-28, 0, 0, 0.3);

		  // Drive forward under the stage door to the backstage
		  bot.navigation.resetAndDriveToPosition(0, 104, 0, 0.1);

		  // Strafe right slightly for better pixel delivery
		  bot.navigation.resetAndDriveToPosition(11, 0, 0, 0.4);


		  // Get arm into delivery position
		  bot.gotoTravelArmPosition();

		  // Release the yellow pixel
		  bot.stickyPad.releaseLeft();
		  sleep(3000);
		  bot.gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
		  sleep(2500);


		  // Back away from the pixel
		  bot.navigation.resetAndDriveToPosition(0, -8, 0, 0.2);

		  // reset the arm
		  bot.gotoStartArmPosition();



	   }

    }
}