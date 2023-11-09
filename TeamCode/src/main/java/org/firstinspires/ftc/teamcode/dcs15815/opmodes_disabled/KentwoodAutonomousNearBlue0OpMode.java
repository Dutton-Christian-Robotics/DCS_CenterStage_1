package org.firstinspires.ftc.teamcode.dcs15815.opmodes_disabled;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.opmodes.PropDetectingOpMode;

@Disabled
@Autonomous(name = "Near Blue 0 Autonomous", group = "Near")
public class KentwoodAutonomousNearBlue0OpMode extends PropDetectingOpMode {

    @Override
    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
    }

    public void driving() {
	   bot.navigation.resetAndDriveToPosition(-60, -10, 0, 0.4);
	   bot.stickyPad.releaseRight();
	   sleep(3000);
	   bot.stickyPad.releaseLeft();
	   sleep(3000);
	   bot.navigation.resetAndDriveToPosition(0, -6, 0, 0.4);
	   bot.gotoStartArmPosition();

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