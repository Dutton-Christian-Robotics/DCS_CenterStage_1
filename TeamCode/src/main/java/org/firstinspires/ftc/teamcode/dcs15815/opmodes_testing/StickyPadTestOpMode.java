package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "StickyPad Test", group = "Testing")
public class StickyPadTestOpMode extends LinearOpMode
{
    private Servo leftStickyPadServo, rightStickyPadServo;

    @Override
    public void runOpMode() {

	   leftStickyPadServo = hardwareMap.servo.get("");
	   rightStickyPadServo = hardwareMap.servo.get("");

	   double currentLeftPosition = 0;
	   double currentRightPosition = 0;
	   int selectedServo = 0;
	   double resolution = 0.5;

	   waitForStart();

	   while (!isStopRequested()) {

		  if (gamepad1.dpad_up) {
			 if ((selectedServo == 0) && (currentLeftPosition < 1)) {
				currentLeftPosition += resolution;
				leftStickyPadServo.setPosition(currentLeftPosition);
			 } else if ((selectedServo == 1) && (currentRightPosition < 1)) {
				currentRightPosition += resolution;
				rightStickyPadServo.setPosition(currentRightPosition);
			 }
		  } else if (gamepad1.dpad_down) {
			 if ((selectedServo == 0) && (currentLeftPosition > 0)) {
				currentLeftPosition -= resolution;
				leftStickyPadServo.setPosition(currentLeftPosition);
			 } else if ((selectedServo == 1) && (currentRightPosition > 0)) {
				currentRightPosition -= resolution;
				rightStickyPadServo.setPosition(currentRightPosition);
			 }
		  } else if (gamepad1.dpad_left) {
			 selectedServo = 0;
		  } else if (gamepad1.dpad_right) {
			 selectedServo = 1;
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