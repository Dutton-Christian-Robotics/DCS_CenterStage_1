package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBArmPosition;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Two Gamepad Driving", group = "Competition")
public class TwoGamepadDrivingOpMode extends LinearOpMode
{
    StickyBanditBot bot;
    private DefenderDebouncer gamepad2DpadUpDebouncer, gamepad2DpadDownDebouncer, gamepad2DpadLeftDebouncer, gamepad2DpadRightDebouncer;
    private DefenderDebouncer gamepad2ADebouncer, gamepad2BDebouncer, gamepad2XDebouncer, gamepad2YDebouncer;
    private DefenderDebouncer gamepad2LeftBumperDebouncer, gamepad2RightBumperDebouncer;
    private DefenderAnalogModifier gamepad2RightStickModifier, gamepad1LeftStickYModifier, gamepad1LeftStickXModifier;

    SBBArmPosition[] armPositions;
    int currentArmPosition = 0;

//    public SBBArmPosition[] reverse(int[] array) {
//	   SBBArmPosition[] newArray = new SBBArmPosition[array.length];
//
//	   for (int i = 0; i < array.length; i++) {
//		  newArray[array.length - 1 - i] = array[i];
//	   }
//
//	   return newArray;
//    }
    public void gotoNextPosition() {
	   currentArmPosition += 1;
	   if (currentArmPosition >= armPositions.length) {
		  currentArmPosition = armPositions.length - 1;
	   }
	   bot.gotoArmPosition(armPositions[currentArmPosition]);
    }

    public void gotoPreviousPosition() {
	   currentArmPosition -= 1;
	   if (currentArmPosition < 0) {
		  currentArmPosition = 0;
	   }
	   bot.gotoArmPosition(armPositions[currentArmPosition]);
    }
    @Override
    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

	   SBBArmPosition startPosition = new SBBArmPosition(SBBConfiguration.LIFT_POSITION_GROUND,
			 SBBConfiguration.TILT_POSITION_GROUND,
			 SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

	   SBBArmPosition grabPosition = new SBBArmPosition(SBBConfiguration.LIFT_POSITION_GROUND,
			 SBBConfiguration.TILT_POSITION_MIN,
			 SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

	   SBBArmPosition travelPosition = new SBBArmPosition(SBBConfiguration.LIFT_POSITION_GROUND,
			 SBBConfiguration.TILT_POSITION_TRAVEL,
			 SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TRAVEL);

	   SBBArmPosition lowFrontDeliveryPosition = new SBBArmPosition(0, 635, 0.425);

	   SBBArmPosition midFrontDeliveryPosition = new SBBArmPosition(500, 1000, 0.425);


	   SBBArmPosition highBackDeliveryPosition = new SBBArmPosition(0, 2851, .85);

	   SBBArmPosition hangPosition = new SBBArmPosition(2601, 1523, .225);


	   armPositions = new SBBArmPosition[]{
			 startPosition,
			 lowFrontDeliveryPosition,
			 midFrontDeliveryPosition,
			 highBackDeliveryPosition
	   };

	   gamepad1LeftStickYModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_CURVE,
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_MAX
	   );
	   gamepad1LeftStickXModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_X_CURVE,
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_X_MAX
	   );

//	   gamepad2RightStickModifier = new DefenderAnalogModifier(
//			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_CURVE,
//			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_MAX
//	   );

	   gamepad2DpadUpDebouncer = new DefenderDebouncer(500, () -> {
		  gotoNextPosition();
//		  bot.lift.setRelativePosition(SBBConfiguration.LIFT_POSITION_DELTA);
	   });
	   gamepad2DpadDownDebouncer = new DefenderDebouncer(500, () -> {
		  gotoPreviousPosition();
//		  bot.lift.setRelativePosition(-1 * SBBConfiguration.LIFT_POSITION_DELTA);
	   });
	   gamepad2DpadLeftDebouncer = new DefenderDebouncer(500, () -> {
//		  bot.tilt.setRelativePosition(-1 * SBBConfiguration.TILT_POSITION_DELTA);
	   });
	   gamepad2DpadRightDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoArmPosition(hangPosition);

//		  bot.tilt.setRelativePosition(SBBConfiguration.TILT_POSITION_DELTA);
	   });
	   gamepad2ADebouncer = new DefenderDebouncer(500, () -> {
		  bot.wrist.setRelativePosition(-1 * SBBConfiguration.WRIST_POSITION_DELTA);

	   });
	   gamepad2BDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoArmPosition(grabPosition);
//		  bot.lift.setPosition(0);
//		  bot.tilt.setPosition(2851);
//		  bot.wrist.setPosition(0.85);

	   });
	   gamepad2XDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoArmPosition(travelPosition);
	   });
	   gamepad2YDebouncer = new DefenderDebouncer(500, () -> {
		  bot.wrist.setRelativePosition(SBBConfiguration.WRIST_POSITION_DELTA);
	   });
	   gamepad2LeftBumperDebouncer = new DefenderDebouncer(500, () -> {
		  bot.stickyPad.releaseLeft();
	   });
	   gamepad2RightBumperDebouncer = new DefenderDebouncer(500, () -> {
		  bot.stickyPad.releaseRight();
	   });

	   waitForStart();
	   bot.stickyPad.gotoGrabPosition();
	   bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);


	   while (opModeIsActive()) {
		  bot.drivetrain.drive(
				gamepad1LeftStickYModifier.modify(-1 * gamepad1.left_stick_y),
				(gamepad1.right_trigger - gamepad1.left_trigger),
				gamepad1.right_stick_x);

		  if (gamepad2.dpad_left) {
			 gamepad2DpadLeftDebouncer.run();
		  } else if (gamepad2.dpad_right) {
			 gamepad2DpadRightDebouncer.run();
		  }

		  if (gamepad2.left_stick_y < 0) {
			 bot.lift.setRelativePosition(SBBConfiguration.LIFT_POSITION_DELTA);
		  } else if (gamepad2.left_stick_y > 0) {
			 bot.lift.setRelativePosition(-1 * SBBConfiguration.LIFT_POSITION_DELTA);
		  }

		  if (gamepad2.right_stick_y < 0) {
			 bot.tilt.setRelativePosition(SBBConfiguration.TILT_POSITION_DELTA);
		  } else if (gamepad2.right_stick_y > 0) {
			 bot.tilt.setRelativePosition(-1 * SBBConfiguration.TILT_POSITION_DELTA);
		  }

		  if (gamepad2.dpad_up) {
			 gamepad2DpadUpDebouncer.run();
		  } else if (gamepad2.dpad_down) {
			 gamepad2DpadDownDebouncer.run();
		  }
		  if (gamepad2.a) {
			 gamepad2ADebouncer.run();
		  }
		  if (gamepad2.b) {
			 gamepad2BDebouncer.run();
		  }
		  if (gamepad2.x) {
			 gamepad2XDebouncer.run();
		  }
		  if (gamepad2.y) {
			 gamepad2YDebouncer.run();
		  }
		  if (gamepad2.left_bumper) {
			 gamepad2LeftBumperDebouncer.run();
		  }
		  if (gamepad2.right_bumper) {
			 gamepad2RightBumperDebouncer.run();
		  }

//		  if (gamepad2.right_stick_y > 0) {
//			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
//		  } else if (gamepad2.right_stick_y < 0) {
//			 bot.lift.setPower(gamepad2RightStickModifier.modify(-1 * gamepad2.right_stick_y));
//		  } else if (gamepad2.dpad_up) {
//			 liftUpDebouncer.run();
//		  } else if (gamepad2.dpad_down) {
//			 liftDownDebouncer.run();
//		  } else if (gamepad2.x) {
//			 liftGroundDebouncer.run();
//		  }
//		  if (gamepad2.b) {
//			 bot.lift.stop();
//		  }
//		  if (gamepad2.right_bumper) {
//			 clawDebouncer.run();
//		  }

		  telemetry.addData("lift", bot.lift.getPosition());
		  telemetry.addData("tilt", bot.tilt.getPosition());
		  telemetry.addData("wrist", bot.wrist.getPosition());
		  telemetry.addData("stickypad", bot.stickyPad.getPosition());
		  telemetry.update();

	   }
	   bot.gotoArmPosition(startPosition);
    }
}