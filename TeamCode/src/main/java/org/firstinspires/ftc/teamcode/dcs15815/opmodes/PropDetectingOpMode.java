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
	   boolean overridePosition = false;
	   boolean detectAlliance = bot.alliance == DefenderBot.Alliance.NONE;
//	   boolean detectAlliance = false;

	   while (opModeInInit()) {
		  telemetry.addData("Detected Position", bot.vision.getDetectedPosition());
		  telemetry.addData("Hue", bot.vision.getDetectedHue());

		  if (!overridePosition) {
			 position = bot.vision.getDetectedPosition();
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
			 telemetry.addData("Detected Alliance", bot.vision.getDetectedAlliance());

		  } else {
			 telemetry.addData("Alliance", bot.alliance);

		  }

		  telemetry.update();

//		  bot.leds.setAllianceColors();
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
