package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.util.ArrayUtils;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Two Gamepad Driving", group = "Competition")
public class TwoGamepadDrivingOpMode extends LinearOpMode
{
    StickyBanditBot bot;
    private DefenderDebouncer liftUpDebouncer, liftDownDebouncer, liftGroundDebouncer, stickyPadDebouncer;
    private DefenderAnalogModifier gamepad2RightStickModifier, gamepad1LeftStickYModifier, gamepad1LeftStickXModifier;

    @Override
    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

	   gamepad1LeftStickYModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_CURVE,
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_MAX
	   );
	   gamepad1LeftStickXModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_X_CURVE,
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_X_MAX
	   );

	   gamepad2RightStickModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_CURVE,
			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_MAX
	   );

	   liftUpDebouncer = new DefenderDebouncer(500, () -> {
//		  gotoNextPosition();
//		  if (currentLiftPositionIndex < (liftPositions.length - 1)) {
//			 currentLiftPositionIndex++;
//		  }
//		  bot.lift.setPosition(liftPositions[currentLiftPositionIndex]);
	   });
	   liftDownDebouncer = new DefenderDebouncer(500, () -> {
//		  gotoPreviousPosition();
//		  if (currentLiftPositionIndex > 0) {
//			 currentLiftPositionIndex--;
//		  }
//		  bot.lift.setPosition(liftPositions[currentLiftPositionIndex]);
	   });
	   liftGroundDebouncer = new DefenderDebouncer(500, () -> {
//		  bot.lift.setPosition(0);
//		  currentLiftPositionIndex = 0;
	   });
	   clawDebouncer = new DefenderDebouncer(500, () -> {
//		  if (isClawOpen) {
//			 bot.claw.close();
//		  } else {
//			 bot.claw.open();
//		  }
//		  isClawOpen = !isClawOpen;
	   });

	   waitForStart();
	   bot.stickyPad.gotoGrabPosition();


	   while (opModeIsActive()) {
//		  telemetry.addData("lift-left", bot.lift.leftMotor.getCurrentPosition());
//		  telemetry.addData("lift-right", bot.lift.rightMotor.getCurrentPosition());
		  bot.drivetrain.drive(gamepad1LeftStickYModifier.modify(-1 * gamepad1.left_stick_y), (gamepad1.right_trigger - gamepad1.left_trigger), gamepad1LeftStickXModifier.modify(gamepad1.left_stick_x));

		  if (gamepad2.right_stick_y > 0) {
			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
		  } else if (gamepad2.right_stick_y < 0) {
			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
		  } else if (gamepad2.dpad_up) {
			 liftUpDebouncer.run();
		  } else if (gamepad2.dpad_down) {
			 liftDownDebouncer.run();
		  } else if (gamepad2.x) {
			 liftGroundDebouncer.run();
		  }
		  if (gamepad2.b) {
			 bot.lift.stop();
		  }
		  if (gamepad2.right_bumper) {
			 clawDebouncer.run();
		  }
//		  telemetry.addData("lift-target", liftPositions[currentLiftPositionIndex]);
		  telemetry.update();

	   }
    }
}