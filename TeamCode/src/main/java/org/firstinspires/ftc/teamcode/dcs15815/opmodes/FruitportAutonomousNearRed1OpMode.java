package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Autonomous(name = "Red Near 1 Autonomous", group = "Near")
public class FruitportAutonomousNearRed1OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   super.setupRobot();
	   bot.alliance = DefenderBot.Alliance.RED;
    }

    @Override
    public void whenRedAlliance() {
	   if (position == PropVisionProcessor.PropPosition.MIDDLE || position == PropVisionProcessor.PropPosition.NONE) {
		  bot.navigation.resetAndDriveToPosition(0, -29, 0, 0.1);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);
		  bot.navigation.comeToRelativeHeading(90, 0.3, 1, 5000);
		  bot.stickyPad.releaseRight();
		  sleep(3000);
		  bot.navigation.comeToRelativeHeading(-90, 0.5, 1, 5000);
		  bot.navigation.resetAndDriveToPosition(0, 37, 0, 0.1);
		  bot.stickyPad.releaseLeft();
		  sleep(3000);
		  bot.gotoStartArmPosition();

	   } else if (position == PropVisionProcessor.PropPosition.LEFT) {
	   } else if (position == PropVisionProcessor.PropPosition.RIGHT) {
	   }

    }
}