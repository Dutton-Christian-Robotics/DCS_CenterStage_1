package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;

@Autonomous(name = "Near 0 Autonomous", group = "Near")
public class AutonomousNear0OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    @Override
    public void whenRedAlliance() {
		bot.navigation.resetAndDriveToPosition(-25, -15, 0, 0.2);
		bot.stickyPad.releaseRight();
		sleep(2000);
    }

    @Override
    public void whenBlueAlliance() {

    }
}