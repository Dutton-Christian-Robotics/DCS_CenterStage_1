package org.firstinspires.ftc.teamcode.dcs15815.opmodes_testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Simple Motor Test", group = "Testing")
public class SimpleMotorTestOpMode extends LinearOpMode {
    private DcMotor motor1, motor2;

    @Override
    public void runOpMode() {
	   motor1 = hardwareMap.dcMotor.get("tilt_right_motor");
	   motor1.setDirection(DcMotorSimple.Direction.FORWARD);

	   motor2 = hardwareMap.dcMotor.get("tilt_left_motor");
	   motor2.setDirection(DcMotorSimple.Direction.FORWARD);

	   waitForStart();

	   while (!isStopRequested()) {
		  motor1.setPower(1);
		  motor2.setPower(1);
	   }
    }
}