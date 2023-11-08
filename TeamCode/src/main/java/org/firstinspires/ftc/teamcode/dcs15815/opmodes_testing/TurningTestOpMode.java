package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Turning Test", group = "Testing")
public class TurningTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;
	   telemetry.addData("Heading", bot.sensors.currentHeading());
	   telemetry.update();

	   waitForStart();

//	   while (opModeIsActive()) {
		bot.navigation.comeToRelativeHeading(45, 0.25, 1, 10000);
		telemetry.addData("Heading", bot.sensors.currentHeading());
		telemetry.update();
		sleep(5000);
	   bot.navigation.comeToRelativeHeading(45, 0.25, 1, 10000);
	   telemetry.addData("Heading", bot.sensors.currentHeading());
	   telemetry.update();
	   sleep(5000);
//	   }
    }
}