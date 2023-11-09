package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.PropVisionProcessor;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

abstract public class PropDetectingOpMode extends LinearOpMode {
    public StickyBanditBot bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
    public PropVisionProcessor.PropPosition position;


    public void initRobot() {
	   bot.opMode = this;
    }

    public void detectPropUntilStart() {
	   boolean overridePosition = false;
	   boolean detectAlliance = bot.alliance == DefenderBot.Alliance.NONE;

	   while (opModeInInit()) {
		  telemetry.addData("Detected Position", bot.vision.getDetectedPosition());
		  telemetry.addData("Hue", bot.vision.getDetectedHue());
		  telemetry.addData("Detected Alliance", bot.vision.getDetectedAlliance());

		  if (!overridePosition) {
			 position = bot.vision.getDetectedPosition();
			 bot.alliance = bot.vision.getDetectedAlliance();
		  } else if (gamepad1.dpad_left) {
			 overridePosition = true;
			 position = PropVisionProcessor.PropPosition.LEFT;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_right) {
			 overridePosition = true;
			 position = PropVisionProcessor.PropPosition.RIGHT;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_up) {
			 overridePosition = true;
			 position = PropVisionProcessor.PropPosition.MIDDLE;
			 telemetry.addData("Override", "ACTIVE");
		  } else if (gamepad1.dpad_down) {
			 overridePosition = false;
		  }

		  if (detectAlliance) {
			 bot.alliance = bot.vision.getDetectedAlliance();
		  }

		  telemetry.update();

		  bot.leds.setAllianceColors();
	   }
	   bot.vision.stopStreaming();
    }


    public void setupRobot() {
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   bot.gotoAutonomousDropArmPosition();
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


    public void whenRedAlliance() {

    }

    public void whenBlueAlliance() {

    }

    public void whenNoAlliance() {

    }
}
