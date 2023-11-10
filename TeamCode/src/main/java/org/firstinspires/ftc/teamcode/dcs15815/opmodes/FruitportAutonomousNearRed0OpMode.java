package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Autonomous(name = "Red Near 0 Autonomous", group = "Near")
public class FruitportAutonomousNearRed0OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   super.setupRobot();
	   bot.alliance = DefenderBot.Alliance.RED;
    }

    @Override
    public void whenRedAlliance() {
	   bot.navigation.comeToRelativeHeading(90, 0.5, 1, 5000);
	   bot.navigation.resetAndDriveToPosition(0, 37, 0, 0.1);
	   bot.stickyPad.releaseBoth();
	   sleep(3000);
//	   bot.navigation.resetAndDriveToPosition(0, -6, 0, 0.4);
	   bot.gotoStartArmPosition();

    }
}