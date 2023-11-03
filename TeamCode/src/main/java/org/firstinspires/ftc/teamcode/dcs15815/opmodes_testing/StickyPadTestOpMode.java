package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@Disabled
@TeleOp(name = "StickyPad Test", group = "Testing")
public class StickyPadTestOpMode extends LinearOpMode
{
    private Servo leftStickyPadServo, rightStickyPadServo;
    private DefenderDebouncer upDebouncer, downDebouncer;
    double currentLeftPosition = 0;
    double currentRightPosition = 0;
    int selectedServo = 0;
    double resolution = 0.1;



    @Override
    public void runOpMode() {

	   leftStickyPadServo = hardwareMap.servo.get("stickypad_left_servo");
	   rightStickyPadServo = hardwareMap.servo.get("stickypad_right_servo");

	   upDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition < 1)) {
			 currentLeftPosition += resolution;
			 leftStickyPadServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition < 1)) {
			 currentRightPosition += resolution;
			 rightStickyPadServo.setPosition(currentRightPosition);
		  }
	   });

	   downDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition > 0)) {
			 currentLeftPosition -= resolution;
			 leftStickyPadServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition > 0)) {
			 currentRightPosition -= resolution;
			 rightStickyPadServo.setPosition(currentRightPosition);
		  }

	   });


	   leftStickyPadServo.setPosition(1);
	   rightStickyPadServo.setPosition(1);


	   waitForStart();

	   while (!isStopRequested()) {

		  if (gamepad1.dpad_up) {
			 upDebouncer.run();
		  } else if (gamepad1.dpad_down) {
			 downDebouncer.run();
		  } else if (gamepad1.dpad_left) {
			 selectedServo = 0;
		  } else if (gamepad1.dpad_right) {
			 selectedServo = 1;
		  }
		  if (gamepad1.x) {
			 leftStickyPadServo.setPosition(0.3);
			 rightStickyPadServo.setPosition(0.3);
		  } else if (gamepad1.a) {
			 leftStickyPadServo.setPosition(1);
			 rightStickyPadServo.setPosition(1);

		  }

    		  telemetry.addData("left servo position", currentLeftPosition);
		  telemetry.addData("right servo position", currentRightPosition);
		  if (selectedServo == 0) {
			 telemetry.addData("selected", "left");
		  } else {
			 telemetry.addData("selected", "right");
		  }
		  telemetry.update();
	   }

    }
}