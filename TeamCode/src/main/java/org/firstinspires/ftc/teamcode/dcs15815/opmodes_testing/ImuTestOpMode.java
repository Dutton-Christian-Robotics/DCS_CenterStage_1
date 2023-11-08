package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "IMU Test", group = "Testing")
public class ImuTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

	   waitForStart();
	   while (opModeIsActive()) {
		  telemetry.addData("Heading", bot.sensors.currentHeading());
		  telemetry.addData("Integrated", bot.sensors.getIntegratedHeading());
		  telemetry.update();
	   }
    }

}
