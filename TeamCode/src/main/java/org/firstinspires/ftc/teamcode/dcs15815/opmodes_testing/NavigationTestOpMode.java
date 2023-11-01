package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Navigation Test", group = "Testing")
public class NavigationTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;

	   waitForStart();

	   bot.navigation.driveToPosition(0, -55, 0, 0.1);
	   bot.navigation.resetPositionTracking();
	   sleep(5000);
	   bot.navigation.driveToPosition(0, -20, 0, 0.1);

//	   while (opModeIsActive()) {
//		  bot.drivetrain.drive(
//				-1 * gamepad1.left_stick_y,
//				(gamepad1.right_trigger - gamepad1.left_trigger),
//				gamepad1.right_stick_x);
//		  double[] distance = bot.navigation.getDistanceInches();
//		  telemetry.addData("x", distance[1]);
//		  telemetry.addData("y", distance[0]);
//		  telemetry.update();
//
//	   }
    }
}