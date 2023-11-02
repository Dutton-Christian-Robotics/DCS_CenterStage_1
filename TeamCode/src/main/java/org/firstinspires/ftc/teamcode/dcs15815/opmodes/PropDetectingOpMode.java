package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

abstract public class PropDetectingOpMode extends LinearOpMode {
    public StickyBanditBot bot;
    public PropVisionProcessor.PropPosition position;


    public void initRobot() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;
    }

    public void detectPropUntilStart() {
	   boolean override = false;

	   while (opModeInInit()) {
		  telemetry.addData("Position", bot.vision.getDetectedPosition());
		  telemetry.addData("Hue", bot.vision.getDetectedHue());
		  telemetry.addData("Alliance", bot.vision.getDetectedAlliance());

		  if (!override) {
			 position = bot.vision.getDetectedPosition();
			 bot.alliance = bot.vision.getDetectedAlliance();
		  } else if (gamepad1.dpad_left) {
			 override = true;
			 position = PropVisionProcessor.PropPosition.LEFT;
			 bot.alliance = DefenderBot.Alliance.RED;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_right) {
			 override = true;
			 position = PropVisionProcessor.PropPosition.RIGHT;
			 bot.alliance = DefenderBot.Alliance.RED;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_up) {
			 override = true;
			 position = PropVisionProcessor.PropPosition.MIDDLE;
			 bot.alliance = DefenderBot.Alliance.RED;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_down) {
			 override = false;
		  }

		  telemetry.update();

		  bot.leds.setAllianceColors();
	   }
	   bot.vision.stopStreaming();
    }


    public void setupRobot() {
    }

    @Override
    public void runOpMode() {
	   initRobot();
	   setupRobot();

	   detectPropUntilStart();

	   if (bot.alliance == DefenderBot.Alliance.RED) {
		  whenRedAlliance();
	   } else if (bot.alliance == DefenderBot.Alliance.BLUE) {
		  whenBlueAlliance();
	   } else {
		  whenNoAlliance();
	   }
    }


    public abstract void whenRedAlliance();

    public abstract void whenBlueAlliance();

    public void whenNoAlliance() {

    }
}
