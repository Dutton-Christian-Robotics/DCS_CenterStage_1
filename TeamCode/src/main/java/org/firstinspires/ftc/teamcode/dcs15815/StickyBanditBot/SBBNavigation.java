package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;
//import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import com.qualcomm.robotcore.util.ElapsedTime;

public class SBBNavigation extends DefenderBotSystem {

    private final GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);
    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;

    private double rotation;

    private MatrixF conversion;

    private DcMotor backLeft, frontLeft, frontRight, backRight;

    private SBBSensors sensors;
    private SBBMecanumDrivetrain drivetrain;

    private DefenderPIDController pidRotate;

    public SBBNavigation(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   float[] data = {1.0f, 1.0f, 1.0f,
			 1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f, 1.0f};
	   conversion = new GeneralMatrixF(3, 3, data);
	   conversion = conversion.inverted();

//	   pidRotate = new DefenderPIDController(.005, .00006, 0);
	   pidRotate = new DefenderPIDController(.0025, 0.00003, 0);


	   sensors = (SBBSensors) ((StickyBanditBot) bot).sensors;

    }

    public void linkDrivetrain(SBBMecanumDrivetrain dt) {
	   drivetrain = dt;

	   backLeft = drivetrain.backLeft;
	   frontLeft = drivetrain.frontLeft;
	   frontRight = drivetrain.frontRight;
	   backRight = drivetrain.backRight;

	   setEncoderOffsets();

    }

    public boolean between(double x, double min, double max) {
	   return (x > min) && (x < max);
    }


    public double[] getDistanceInches() {
	   double[] distances = {0.0, 0.0};

	   encoderMatrix.put(0, 0, (float) ((frontLeft.getCurrentPosition() - frontLeftOffset) * SBBConfiguration.NAVIGATION_INCHES_PER_TICK));
	   encoderMatrix.put(1, 0, (float) ((frontRight.getCurrentPosition() - frontRightOffset) * SBBConfiguration.NAVIGATION_INCHES_PER_TICK));
	   encoderMatrix.put(2, 0, (float) ((backLeft.getCurrentPosition() - backLeftOffset) * SBBConfiguration.NAVIGATION_INCHES_PER_TICK));

	   MatrixF distanceMatrix = conversion.multiplied(encoderMatrix);
	   distances[0] = distanceMatrix.get(0, 0);
	   distances[1] = distanceMatrix.get(1, 0);

	   return distances;
    }

    public double powerDropoff(double target, double current) {
	   return 1 - Math.pow(1 - ((target - (current - 1)) / Math.abs(target - current)), 14);
    }

    public void setEncoderOffsets() {

	   frontRightOffset = frontRight.getCurrentPosition();
	   frontLeftOffset = frontLeft.getCurrentPosition();
	   backLeftOffset = backLeft.getCurrentPosition();
	   backRightOffset = backRight.getCurrentPosition();
    }

    public void driveToPosition(double x, double y, double heading, double maxPower) {

//	   double[] d = getDistanceInches();
	   double[] d = {0, 0, 0};
	   double deltaY, deltaX, deltaH;
	   double pX, pY, pH;
	   double h = sensors.getAngle();
	   double rotation = 0;
	   double averageError = 0;
	   double powerReduction = 1;
	   double totalDistance;
	   double totalPower = 0;

	   do {
		  deltaX = x - d[1];
		  deltaY = y - d[0];
		  deltaH = h - heading;

		  totalDistance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

		  if (totalDistance > 9) {
			 powerReduction = 0.5;
		  } else if (totalDistance > 4) {
			 powerReduction = 0.15;
		  } else {
			 powerReduction = 0.05;
		  }


//		  averageError = (Math.abs(deltaX) + Math.abs(deltaY) + Math.abs(deltaH)) / 3;
		  averageError = (Math.abs(deltaX) + Math.abs(deltaY)) / 2;
		  pX = (deltaX / averageError) * maxPower * powerReduction;
		  pY = (deltaY / averageError) * maxPower * powerReduction;
//		  pH = (deltaH / averageError) * maxPower;
		  pH = 0;
		  totalPower = Math.abs(pX) + Math.abs(pY);

		  bot.telemetry.addData("dX", deltaX);
		  bot.telemetry.addData("dY", deltaY);
		  bot.telemetry.addData("dH", deltaH);
		  bot.telemetry.addData("distance", totalDistance);
		  bot.telemetry.addData("power", totalPower);
		  bot.telemetry.update();


//            bot.drive(powerDropoff(y, d[0]), powerDropoff(x, d[1]), 0);
		  drivetrain.drive(pY, pX, pH);
		  d = getDistanceInches();
		  h = sensors.getAngle();
//	   } while ((Math.abs(y - d[0]) > SBBConfiguration.NAVIGATION_TOLERANCE_Y) || (Math.abs(x - d[1]) > SBBConfiguration.NAVIGATION_TOLERANCE_X) || (Math.abs(heading - h) > SBBConfiguration.NAVIGATION_TOLERANCE_ROTATION)) {
	   } while ((totalPower > 0.05) && (totalDistance > SBBConfiguration.NAVIGATION_TOLERANCE_Y) && !bot.opMode.isStopRequested());
	   bot.stopDriving();

    }

    public void driveToPosition(double x, double y) {
	   driveToPosition(x, y, 0.0, 1);
    }

    public void driveToPosition(DefenderBotPosition position) {
	   driveToPosition(position.getX(), position.getY(), position.getHeading(), 1);
    }


    public void driveToRelativePosition(double dX, double dY, double heading) {
//	   driveToPosition(getCurrentPosition().relativePosition(dX, dY), heading);
    }

    public void resetPositionTracking() {
	   drivetrain.resetEncoders();
    }

    public void resetAndDriveToPosition(double x, double y) {
	   resetPositionTracking();
	   driveToPosition(x, y);
    }

    public void resetAndDriveToPosition(double x, double y, double heading, double maxPower) {
	   resetPositionTracking();
	   driveToPosition(x, y, heading, maxPower);
    }

    public double angleWrap(double degrees) {
	   double radians = Math.toRadians(degrees);
	   while (radians > Math.PI) {
		  degrees -= 2 * Math.PI;
	   }
	   while (radians < -Math.PI) {
		  radians += 2 * Math.PI;
	   }
	   return Math.toDegrees(radians);
    }

    public void comeToRelativeHeading(double degrees, double power, double tolerance, double timeout) {
	   // restart imu angle tracking.
	   sensors.resetAngle();

	   // if degrees > 359 we cap at 359 with same sign as original degrees.
	   if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);

	   // start pid controller. PID controller will monitor the turn angle with respect to the
	   // target angle and reduce power as we approach the target angle. This is to prevent the
	   // robots momentum from overshooting the turn after we turn off the power. The PID controller
	   // reports onTarget() = true when the difference between turn angle and target angle is within
	   // 1% of target (tolerance) which is about 1 degree. This helps prevent overshoot. Overshoot is
	   // dependant on the motor and gearing configuration, starting power, weight of the robot and the
	   // on target tolerance. If the controller overshoots, it will reverse the sign of the output
	   // turning the robot back toward the setpoint value.

	   pidRotate.reset();
	   pidRotate.setSetpoint(degrees);
	   pidRotate.setInputRange(0, degrees);
	   pidRotate.setOutputRange(0, power);
	   pidRotate.setTolerance(1);
	   pidRotate.enable();

	   // getAngle() returns + when rotating counter clockwise (left) and - when rotating
	   // clockwise (right).

	   // rotate until turn is completed.

	   if (degrees < 0) {
		  // On right turn we have to get off zero first.
//		  while (bot.opMode.opModeIsActive() && sensors.getAngle() == 0) {
//			 drivetrain.drive(0, 0, power);
//			 sleep(100);
//		  }

		  do {
			 power = pidRotate.performPID(sensors.getAngle()); // power will be - on right turn.
			 drivetrain.drive(0, 0, -power);
//			 leftMotor.setPower(-power);
//			 rightMotor.setPower(power);
		  } while (bot.opMode.opModeIsActive() && !pidRotate.onTarget());
	   } else    // left turn.
		  do {
			 power = pidRotate.performPID(sensors.getAngle()); // power will be + on left turn.
			 drivetrain.drive(0, 0, -power);

//			 leftMotor.setPower(-power);
//			 rightMotor.setPower(power);
		  } while (bot.opMode.opModeIsActive() && !pidRotate.onTarget());

	   // turn the motors off.
	   drivetrain.stopDriving();
//	   rightMotor.setPower(0);
//	   leftMotor.setPower(0);

	   rotation = sensors.getAngle();

	   // wait for rotation to stop.
	   sleep(500);

	   // reset angle tracking on new heading.
	   sensors.resetAngle();
    }
}
