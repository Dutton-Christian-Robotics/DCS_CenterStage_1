package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Autonomous(name = "Blue Near 0 Autonomous", group = "Near")
public class FruitportAutonomousNearBlue0OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   super.setupRobot();
	   bot.alliance = DefenderBot.Alliance.BLUE;
    }

    @Override
    public void whenBlueAlliance() {
	   bot.navigation.resetAndDriveToPosition(0, -10, 0, 0.3);
	   bot.gotoTravelArmPosition();
	   bot.navigation.comeToRelativeHeading(-90, 1, 1, 5000);
	   bot.navigation.resetAndDriveToPosition(0, 39, 0, 0.1);
	   bot.stickyPad.releaseBoth();
	   sleep(3000);
	   bot.navigation.resetAndDriveToPosition(0, -6, 0, 0.4);
	   bot.gotoStartArmPosition();
    }
}