package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDebouncer;

@TeleOp(name = "Lift Test", group = "Testing")
public class LiftTestOpMode extends LinearOpMode {
    private DcMotor liftLeftMotor, liftRightMotor;
    private DefenderDebouncer upDebouncer, downDebouncer;
    int currentPosition = 0;
    double resolution = 100;

    @Override
    public void runOpMode() {

	   liftLeftMotor = hardwareMap.dcMotor.get("lift_left_motor");
	   liftRightMotor = hardwareMap.dcMotor.get("lift_right_motor");

	   liftLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
	   liftRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

	   liftLeftMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
	   liftRightMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

	   liftLeftMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
	   liftRightMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

	   liftLeftMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
	   liftRightMotor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

	   waitForStart();

//	   liftRightMotor.setPower(0);
//	   liftRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
//	   liftRightMotor.setTargetPosition(500);
//	   liftRightMotor.setPower(1);


	   upDebouncer = new DefenderDebouncer(500, () -> {
		  currentPosition += resolution;
		  liftLeftMotor.setPower(0);
		  liftLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  liftLeftMotor.setTargetPosition(currentPosition);
		  liftLeftMotor.setPower(1);

		  liftRightMotor.setPower(0);
		  liftRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  liftRightMotor.setTargetPosition(currentPosition);
		  liftRightMotor.setPower(1);
	   });

	   downDebouncer = new DefenderDebouncer(500, () -> {
		  currentPosition -= resolution;
		  liftLeftMotor.setPower(0);
		  liftLeftMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  liftLeftMotor.setTargetPosition(currentPosition);
		  liftLeftMotor.setPower(1);

		  liftRightMotor.setPower(0);
		  liftRightMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
		  liftRightMotor.setTargetPosition(currentPosition);
		  liftRightMotor.setPower(1);
	   });

	   while (!isStopRequested()) {

		  if (gamepad1.dpad_up) {
			 upDebouncer.run();
		  } else if (gamepad1.dpad_down) {
			 downDebouncer.run();
		  }

		  telemetry.addData("planned", currentPosition);
		  telemetry.addData("left", liftLeftMotor.getCurrentPosition());
		  telemetry.addData("right", liftRightMotor.getCurrentPosition());
		  telemetry.update();
	   }

    }

}