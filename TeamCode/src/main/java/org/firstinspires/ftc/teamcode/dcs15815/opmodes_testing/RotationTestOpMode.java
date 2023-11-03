package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@Disabled
@TeleOp(name = "Rotation Test", group = "Testing")
public class RotationTestOpMode extends LinearOpMode {
    StickyBanditBot bot;

    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);
	   bot.opMode = this;

	   waitForStart();
//		double d = 0;
	   double currentAngle;
//	   while (opModeIsActive()) {
//		  currentAngle = bot.sensors.getIntegratedHeading();
//		telemetry.addData("Current", currentAngle);
//		telemetry.update();

		  bot.navigation.comeToRelativeHeading(-45, 0.3, 1, 60000);
		  telemetry.addData("Done", "Yeah!");
		  telemetry.update();

//	   }
//		d += 150;
//		bot.drivetrain.drive(0,0,0.1);
//		sleep(1000);
//		bot.stopDriving();
//	   }
    }

    public void simulateComeToHeading(double angle, double maxPower, double tolerance, double timeout) {
	   double difference, absDifference, currentAngle;
	   boolean keepTurning = true;
	   ElapsedTime timer = new ElapsedTime();
	   long sleepLength = 10;
	   double powerCutoffThreshold = 0.01;
	   double targetAngle = angleWrap(angle);

	   angle = targetAngle;

	   double power;
	   do {
		  currentAngle = Math.floor(bot.sensors.currentHeading());
//		  currentAngle = sensors.currentHeading();
		  difference = currentAngle - angle;
		  absDifference = Math.abs(difference);
		  if ((absDifference < tolerance) || (timer.milliseconds() >= timeout)) {
			 keepTurning = false;
			 power = 0;
		  } else if (absDifference > 90) {
			 power = 1;
		  } else if (absDifference > 30) {
			 power = 0.5;
		  } else {
//		  } else if (absDifference > 10) {
			 power = 0.25;
//		  } else {
//			 power = 0.1;
		  }
		  if (difference < 0) {
			 power *= -1;
		  }

		  telemetry.addData("Target", angle);
		  telemetry.addData("Current", currentAngle);
		  telemetry.addData("Difference", difference);
		  telemetry.addData("Power", power);
		  telemetry.addData("Set Power", power * maxPower);
		  telemetry.update();


	   } while (keepTurning && !bot.opMode.isStopRequested());

    }
    public double angleWrap(double degrees) {
	   double radians = Math.toRadians(degrees);
	   while (radians > Math.PI) {
		  degrees -= 2 * Math.PI;
	   }
	   while (radians < -Math.PI) {
		  radians += 2 * Math.PI;
	   }
	   return Math.toDegrees(radians);
    }

}