package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor.PropPosition;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;

@Autonomous(name = "Near Blue 1 Autonomous", group = "Near")
public class AutonomousNearBlue1OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    public void driving() {
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

    @Override
    public void whenRedAlliance() {
	   driving();

    }

    @Override
    public void whenBlueAlliance() {
	  driving();

    }

    @Override
    public void whenNoAlliance() {
	   driving();
    }

}