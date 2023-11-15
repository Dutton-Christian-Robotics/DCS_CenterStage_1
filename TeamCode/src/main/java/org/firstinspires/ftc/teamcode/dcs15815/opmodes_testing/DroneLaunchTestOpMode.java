package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Drone Launch Test", group = "Testing")
public class DroneLaunchTestOpMode extends LinearOpMode {
    StickyBanditBot bot;


    @Override
    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;

	   waitForStart();

	   while (opModeIsActive()) {
		  if (gamepad1.dpad_up) {
			 bot.droneLauncher.launchDrone();
		  } else if (gamepad1.dpad_down) {
			 bot.droneLauncher.gotoHoldPosition();
		  }
	   }
    }
}