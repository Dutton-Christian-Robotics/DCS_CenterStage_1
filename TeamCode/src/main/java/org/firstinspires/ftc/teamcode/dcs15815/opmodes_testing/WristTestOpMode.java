package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Wrist Test", group = "Testing")
public class WristTestOpMode extends LinearOpMode
{
    private Servo leftWristServo, rightWristServo;
    private DefenderDebouncer upDebouncer, downDebouncer, xDebouncer, yDebouncer;
    double currentLeftPosition = 0;
    double currentRightPosition = 0;
    int selectedServo = 0;
    double resolution = 0.025;
    double sharedPosition = -1;



    @Override
    public void runOpMode() {

	   leftWristServo = hardwareMap.servo.get("wrist_left_servo");
	   rightWristServo = hardwareMap.servo.get("wrist_right_servo");


	   upDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition < 1)) {
			 currentLeftPosition += resolution;
			 leftWristServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition < 1)) {
			 currentRightPosition += resolution;
			 rightWristServo.setPosition(currentRightPosition);
		  }
		  sharedPosition = -1;
	   });

	   downDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition > 0)) {
			 currentLeftPosition -= resolution;
			 leftWristServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition > 0)) {
			 currentRightPosition -= resolution;
			 rightWristServo.setPosition(currentRightPosition);
		  }
		  sharedPosition = -1;

	   });

	   yDebouncer = new DefenderDebouncer(500, () -> {
		  if (sharedPosition == -1) {
			 currentLeftPosition = 0.5;
			 currentRightPosition = 0.5;
			 sharedPosition = 0.5;
			 leftWristServo.setPosition(currentLeftPosition);
			 rightWristServo.setPosition(currentRightPosition);
		  }
		  currentLeftPosition -= resolution;
		  currentRightPosition += resolution;
		  leftWristServo.setPosition(currentLeftPosition);
		  rightWristServo.setPosition(currentRightPosition);
	   });

	   xDebouncer = new DefenderDebouncer(500, () -> {
		  if (sharedPosition == -1) {
			 currentLeftPosition = 0.5;
			 currentRightPosition = 0.5;
			 sharedPosition = 0.5;
			 leftWristServo.setPosition(currentLeftPosition);
			 rightWristServo.setPosition(currentRightPosition);
		  }
		  currentLeftPosition += resolution;
		  currentRightPosition -= resolution;
		  leftWristServo.setPosition(currentLeftPosition);
		  rightWristServo.setPosition(currentRightPosition);
	   });





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
			 xDebouncer.run();
		  } else if (gamepad1.y) {
			 yDebouncer.run();

		  }

		  telemetry.addData("left servo position", currentLeftPosition);
		  telemetry.addData("right servo position", currentRightPosition);
		  if (sharedPosition > -1) {
			 telemetry.addData("selected", "coordinated");
		  } else if (selectedServo == 0) {
			 telemetry.addData("selected", "left");
		  } else {
			 telemetry.addData("selected", "right");
		  }
		  telemetry.update();
	   }

    }
}