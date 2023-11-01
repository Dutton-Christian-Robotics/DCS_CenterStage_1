package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "LEDs Test", group = "Testing")
public class LedsTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

	   bot.leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.CONFETTI);
	   waitForStart();
	   while (opModeIsActive()) {
		  bot.leds.setRedAllianceColors();
		  sleep(3000);
		  bot.leds.setBlueAllianceColors();
		  sleep(3000);
	   }

    }
}
