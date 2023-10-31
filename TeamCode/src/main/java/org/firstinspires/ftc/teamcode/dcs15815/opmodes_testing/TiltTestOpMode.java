package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Tilt Test", group = "Testing")
public class TiltTestOpMode extends LinearOpMode {
    private DcMotor tiltLeftMotor, tiltRightMotor;
    private DefenderDebouncer upDebouncer, downDebouncer;
    int currentPosition = 0;
    double resolution = 100;

    @Override
    public void runOpMode() {

	   tiltLeftMotor = hardwareMap.dcMotor.get("tilt_left_motor");
	   tiltRightMotor = hardwareMap.dcMotor.get("tilt_right_motor");

	   tiltLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
	   tiltRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

	   tiltLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
	   tiltRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

	   tiltLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   tiltRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

	   tiltLeftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   tiltRightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

	   waitForStart();

//	   tiltRightMotor.setPower(0);
//	   tiltRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//	   tiltRightMotor.setTargetPosition(0);
//	   tiltRightMotor.setPower(1);


	   upDebouncer = new DefenderDebouncer(500, () -> {
		  currentPosition += resolution;
		  tiltLeftMotor.setPower(0);
		  tiltLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  tiltLeftMotor.setTargetPosition(currentPosition);
		  tiltLeftMotor.setPower(1);

		  tiltRightMotor.setPower(0);
		  tiltRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  tiltRightMotor.setTargetPosition(currentPosition);
		  tiltRightMotor.setPower(1);
	   });

	   downDebouncer = new DefenderDebouncer(500, () -> {
			 currentPosition -= resolution;
		  tiltLeftMotor.setPower(0);
		  tiltLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  tiltLeftMotor.setTargetPosition(currentPosition);
		  tiltLeftMotor.setPower(1);

		  tiltRightMotor.setPower(0);
			 tiltRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
			 tiltRightMotor.setTargetPosition(currentPosition);
			 tiltRightMotor.setPower(1);
	   });

	   while (!isStopRequested()) {

		  if (gamepad1.dpad_up) {
			 upDebouncer.run();
		  } else if (gamepad1.dpad_down) {
			 downDebouncer.run();
		  }

		  telemetry.addData("planned", currentPosition);
		  telemetry.addData("left", tiltLeftMotor.getCurrentPosition());
		  telemetry.addData("right", tiltRightMotor.getCurrentPosition());
		  telemetry.update();
	   }

    }

}