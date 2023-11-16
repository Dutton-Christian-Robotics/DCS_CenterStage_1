package org.firstinspires.ftc.teamcode.dcs15815.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderAnalogModifier;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.SBBConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot.StickyBanditBot;

@TeleOp(name = "Two Gamepad Driving", group = "Competition")
public class TwoGamepadDrivingOpMode extends LinearOpMode
{
    StickyBanditBot bot;
    private DefenderDebouncer gamepad1StartDebouncer, gamepad1XDebouncer, gamepad1YDebouncer;
    private DefenderDebouncer gamepad2DpadUpDebouncer, gamepad2DpadDownDebouncer, gamepad2DpadLeftDebouncer, gamepad2DpadRightDebouncer;
    private DefenderDebouncer gamepad2ADebouncer, gamepad2BDebouncer, gamepad2XDebouncer, gamepad2YDebouncer;
    private DefenderDebouncer gamepad2LeftBumperDebouncer, gamepad2RightBumperDebouncer;
    private DefenderDebouncer gamepad2StartDebouncer;
    private DefenderDebouncer releaseBothDebouncer;
    private DefenderAnalogModifier gamepad2RightStickModifier, gamepad1LeftStickYModifier, gamepad1RightStickXModifier;

    private boolean readyToLaunchDrone = false;
	private boolean allowManualLiftControl = false;
    public boolean isReadyToHang = false;

     @Override
    public void runOpMode() {
	   bot = new StickyBanditBot(hardwareMap, SBBConfiguration.class, telemetry);

	   // ——— SETUP STICK MODIFIERS —————————————————————————————————

	   gamepad1LeftStickYModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_CURVE,
			 SBBConfiguration.GAMEPAD1_LEFT_STICK_Y_MAX
	   );
	   gamepad1RightStickXModifier = new DefenderAnalogModifier(
			 SBBConfiguration.GAMEPAD1_RIGHT_STICK_X_CURVE,
			 SBBConfiguration.GAMEPAD1_RIGHT_STICK_X_MAX
	   );

//	   gamepad2RightStickModifier = new DefenderAnalogModifier(
//			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_CURVE,
//			 SBBConfiguration.GAMEPAD2_RIGHT_STICK_MAX
//	   );

	    // ——— SETUP GAMEPAD 1 DEBOUNCERS —————————————————————————————————

	    gamepad1StartDebouncer = new DefenderDebouncer(500, () -> {
		   readyToLaunchDrone = !readyToLaunchDrone;
	    });

	    gamepad1XDebouncer = new DefenderDebouncer(500, () -> {
		   if (readyToLaunchDrone) {
			  bot.droneLauncher.launchDrone();
		   }
	    });
	    gamepad1YDebouncer = new DefenderDebouncer(500, () -> {
		   readyToLaunchDrone = false;
		   bot.droneLauncher.gotoHoldPosition();
	    });

	    // ——— SETUP GAMEPAD 2 DEBOUNCERS —————————————————————————————————

	    releaseBothDebouncer = new DefenderDebouncer(1500, () -> {
		   bot.stickyPad.releaseBoth();
	    });

	    gamepad2StartDebouncer = new DefenderDebouncer(500, () -> {
		   allowManualLiftControl = !allowManualLiftControl;
	    });

	    gamepad2DpadUpDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoNextArmPosition();

	   });
	   gamepad2DpadDownDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoPreviousArmPosition();
	   });
	   gamepad2DpadLeftDebouncer = new DefenderDebouncer(500, () -> {
		  bot.stickyPad.gotoGrabPosition();
	   });
	   gamepad2DpadRightDebouncer = new DefenderDebouncer(500, () -> {
		  if (!isReadyToHang) {
			 bot.gotoHangArmPosition();
			 isReadyToHang = true;
		  } else {
			 bot.gotoHangingArmPosition();
			 isReadyToHang = false;
		  }
	   });
	   gamepad2ADebouncer = new DefenderDebouncer(500, () -> {
		  bot.wrist.setRelativePosition(-1 * SBBConfiguration.WRIST_POSITION_DELTA);

	   });
	   gamepad2BDebouncer = new DefenderDebouncer(500, () -> {
		  bot.runGrabPixelsMacro();
	   });
	   gamepad2XDebouncer = new DefenderDebouncer(500, () -> {
		  bot.gotoTravelArmPosition();
		  bot.armPresets.select(1);
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



	    bot.stickyPad.gotoGrabPosition();
	    bot.wrist.setPosition(SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
	   waitForStart();


	   while (opModeIsActive()) {

	   // ——— GAMEPAD 1 FUNCTIONS  —————————————————————————————————

		  bot.drivetrain.drive(
				-1 * gamepad1LeftStickYModifier.modify(gamepad1.left_stick_y),
				(gamepad1.right_trigger - gamepad1.left_trigger),
				gamepad1RightStickXModifier.modify(gamepad1.right_stick_x));

		  if (gamepad1.start) {
			 gamepad1StartDebouncer.run();
		  }
		  if (gamepad1.x) {
			 gamepad1XDebouncer.run();
		  }
		  if (gamepad1.y) {
			 gamepad1YDebouncer.run();
		  }

	   // ——— GAMEPAD 2 FUNCTIONS  —————————————————————————————————

		  if (allowManualLiftControl) {
			 if (gamepad2.left_stick_y < 0) {
				bot.lift.setRelativePosition(SBBConfiguration.LIFT_POSITION_DELTA);
			 } else if (gamepad2.left_stick_y > 0) {
				bot.lift.setRelativePosition(-1 * SBBConfiguration.LIFT_POSITION_DELTA);
			 }
		  }

		  if (gamepad2.right_stick_y < 0) {
			 bot.tilt.setRelativePosition(SBBConfiguration.TILT_POSITION_DELTA);
		  } else if (gamepad2.right_stick_y > 0) {
			 bot.tilt.setRelativePosition(-1 * SBBConfiguration.TILT_POSITION_DELTA);
		  }

		  if (gamepad2.dpad_left) {
			 gamepad2DpadLeftDebouncer.run();
		  } else if (gamepad2.dpad_right) {
			 gamepad2DpadRightDebouncer.run();
		  }


		  if (gamepad2.dpad_up) {
			 gamepad2DpadUpDebouncer.run();
		  } else if (gamepad2.dpad_down) {
			 gamepad2DpadDownDebouncer.run();
		  }

		  if (gamepad2.start) {
			 gamepad2StartDebouncer.run();
		  }

		  if (gamepad2.right_trigger > 0) {
			 releaseBothDebouncer.run();
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

	   // ——— TELEMETRY  —————————————————————————————————

		  if (!readyToLaunchDrone) {
			 telemetry.addData("Drone", "safety on");
		  } else {
			 telemetry.addData("Drone", "READY TO LAUNCH!!!!");
		  }
		  if (!allowManualLiftControl) {
			 telemetry.addData("Manual Lift", "disabled");
		  } else {
			 telemetry.addData("Manual Lift", "ENABLED");
		  }
		  telemetry.addData("Lift", bot.lift.getPosition());
		  telemetry.addData("Tilt", bot.tilt.getPosition());
//		  telemetry.addData("Wrist L", bot.wrist.leftServo.getPosition());
//		  telemetry.addData("Wrist R", bot.wrist.rightServo.getPosition());
		  telemetry.addData("Stickypad", bot.stickyPad.getPosition());
		  telemetry.update();

	   }

	// ——— AFTER OPMODE ENDS —————————————————————————————————

	    bot.gotoStartArmPosition();
    }
}