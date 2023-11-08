package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@Disabled
@TeleOp(name = "Wrist Servo Test", group = "Testing")
public class WristServoTestOpMode extends LinearOpMode
{
    private Servo leftServo, rightServo;
    private DefenderDebouncer upDebouncer, downDebouncer;
    double currentLeftPosition = 0;
    double currentRightPosition = 0;
    int selectedServo = 0;
    double resolution = 0.1;



    @Override
    public void runOpMode() {

	   leftServo = hardwareMap.servo.get("wrist_left_servo");
	   rightServo = hardwareMap.servo.get("wrist_right_servo");

	   upDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition < 1)) {
			 currentLeftPosition += resolution;
			 leftServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition < 1)) {
			 currentRightPosition += resolution;
			 rightServo.setPosition(currentRightPosition);
		  }
	   });

	   downDebouncer = new DefenderDebouncer(500, () -> {
		  if ((selectedServo == 0) && (currentLeftPosition > 0)) {
			 currentLeftPosition -= resolution;
			 leftServo.setPosition(currentLeftPosition);
		  } else if ((selectedServo == 1) && (currentRightPosition > 0)) {
			 currentRightPosition -= resolution;
			 rightServo.setPosition(currentRightPosition);
		  }

	   });


	   leftServo.setPosition(1);
	   rightServo.setPosition(1);


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
			 leftServo.setPosition(0.3);
			 rightServo.setPosition(0.3);
		  } else if (gamepad1.a) {
			 leftServo.setPosition(1);
			 rightServo.setPosition(1);

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