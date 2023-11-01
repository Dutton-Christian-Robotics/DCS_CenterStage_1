package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Rotation Test", group = "Testing")
public class RotationTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;

	   waitForStart();
//		double d = 0;
//	   while (opModeIsActive()) {
//		d += 150;
		bot.drivetrain.drive(0,0,0.1);
		sleep(1000);
		bot.stopDriving();
//	   }
    }
}