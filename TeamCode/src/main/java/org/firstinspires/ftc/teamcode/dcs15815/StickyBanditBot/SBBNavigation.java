package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.matrices.GeneralMatrixF;
import org.firstinspires.ftc.robotcore.external.matrices.MatrixF;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotPosition;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderPIDController;

import com.qualcomm.robotcore.util.ElapsedTime;

public class SBBNavigation extends DefenderBotSystem {

    private final GeneralMatrixF encoderMatrix = new GeneralMatrixF(3, 1);
    private int frontLeftOffset;
    private int frontRightOffset;
    private int backRightOffset;
    private int backLeftOffset;

    private MatrixF conversion;

    private DcMotor backLeft, frontLeft, frontRight, backRight;

    private SBBSensors sensors;
    private SBBMecanumDrivetrain drivetrain;

    public SBBNavigation(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   float[] data = {1.0f, 1.0f, 1.0f,
			 1.0f, -1.0f, -1.0f,
			 1.0f, -1.0f, 1.0f};
	   conversion = new GeneralMatrixF(3, 3, data);
	   conversion = conversion.inverted();


	   sensors = (SBBSensors)((StickyBanditBot)bot).sensors;

    }

//    public void linkSensors(SBBSensors s) {
//	   sensors = s;
//    }
    public void linkDrivetrain(SBBMecanumDrivetrain dt) {
	   drivetrain = dt;
//	   System.out.println("FLAG");
//	   System.out.println(drivetrain == null ? "null" : "exists");
//	   System.out.println(drivetrain.getClass().getName());

	   backLeft = drivetrain.backLeft;
	   frontLeft = drivetrain.frontLeft;
	   frontRight = drivetrain.frontRight;
	   backRight = drivetrain.backRight;

	   setEncoderOffsets();

//	   backLeft = ((ProductionBotMecanumDrivetrain) bot.drivetrain).backLeft;
//	   frontLeft = ((ProductionBotMecanumDrivetrain) bot.drivetrain).frontLeft;
//	   frontRight = ((ProductionBotMecanumDrivetrain) bot.drivetrain).frontRight;
//	   backRight = ((ProductionBotMecanumDrivetrain) bot.drivetrain).backRight;

    }

    public boolean between(double x, double min, double max) {
	   return (x > min) && (x < max);
    }

    public void comeToRelativeHeading(double angle, double maxPower, double tolerance, double timeout) {
	   double startingAngle = Math.floor(sensors.currentHeading());
	   angle = startingAngle + angle;

	   double difference, absDifference, currentAngle;
	   boolean keepTurning = true;
	   ElapsedTime timer = new ElapsedTime();
	   long sleepLength = 10;
	   double powerCutoffThreshold = 0.01;
	   double targetAngle = angleWrap(angle);

	   angle = targetAngle;

	   double power;
	   do {
		  currentAngle = Math.floor(sensors.currentHeading());
		  difference = currentAngle - angle;
		  absDifference = Math.abs(difference);
		  if ((absDifference < tolerance) || (timer.milliseconds() >= timeout)) {
			 keepTurning = false;
			 power = 0;
		  } else if (absDifference > 90) {
			 power = 1;
		  } else if (absDifference > 30) {
			 power = 0.5;
		  } else {
//		  } else if (absDifference > 10) {
			 power = 0.25;
//		  } else {
//			 power = 0.1;
		  }
		  if (difference < 0) {
			 power *= -1;
		  }
		  bot.telemetry.addData("Target", angle);
		  bot.telemetry.addData("Current", currentAngle);
		  bot.telemetry.addData("Difference", difference);
		  bot.telemetry.addData("Power", power);
		  bot.telemetry.addData("Set Power", power * maxPower);
		  bot.telemetry.update();


		  drivetrain.drive(0, 0, power * maxPower);
		  sleep(sleepLength);

	   } while (keepTurning && !bot.opMode.isStopRequested());

    }

    public void comeToHeading(double angle, double maxPower, double tolerance, double timeout) {
	   double startingAngle = Math.floor(sensors.currentHeading());

	   double difference, absDifference, currentAngle;
	   boolean keepTurning = true;
	   ElapsedTime timer = new ElapsedTime();
	   long sleepLength = 10;
	   double powerCutoffThreshold = 0.01;
	   double targetAngle = angleWrap(angle);

	   angle = targetAngle;

	   double power;
	   do {
		  currentAngle = Math.floor(sensors.currentHeading());
		  difference = currentAngle - angle;
		  absDifference = Math.abs(difference);
		  if ((absDifference < tolerance) || (timer.milliseconds() >= timeout)) {
			 keepTurning = false;
			 power = 0;
		  } else if (absDifference > 90) {
			 power = 1;
		  } else if (absDifference > 30) {
			 power = 0.5;
		  } else {
//		  } else if (absDifference > 10) {
			 power = 0.25;
//		  } else {
//			 power = 0.1;
		  }
		  if (difference < 0) {
			 power *= -1;
		  }
		  bot.telemetry.addData("Target", angle);
		  bot.telemetry.addData("Current", currentAngle);
		  bot.telemetry.addData("Difference", difference);
		  bot.telemetry.addData("Power", power);
		  bot.telemetry.addData("Set Power", power * maxPower);
		  bot.telemetry.update();


		  drivetrain.drive(0, 0, power * maxPower);
		  sleep(sleepLength);

	   } while (keepTurning && !bot.opMode.isStopRequested());

    }

    public void comeToHeading(double angle, double powerRatio) {
	   comeToHeading(angle, powerRatio * SBBConfiguration.NAVIGATION_POWER_DEFAULT, SBBConfiguration.NAVIGATION_TOLERANCE_ROTATION, SBBConfiguration.NAVIGATION_TIMEOUT_DEFAULT);
    }

    public void comeToHeading(double angle) {
	   comeToHeading(angle, SBBConfiguration.NAVIGATION_POWER_DEFAULT, SBBConfiguration.NAVIGATION_TOLERANCE_ROTATION, SBBConfiguration.NAVIGATION_TIMEOUT_DEFAULT);
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
	   double h = sensors.getIntegratedHeading();
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
		  h = sensors.getIntegratedHeading();
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

//    public DefenderBotPosition getCurrentPosition() {
//	   double[] d = getDistanceInches();
//	   return new DefenderBotPosition(d[1], d[0], sensors.getIntegratedHeading());
//    }

//    public void driveToRelativePosition(double dX, double dY) {
//	   driveToPosition(getCurrentPosition().relativePosition(dX, dY));
//    }

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


}