package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;


public class SBBSensors extends DefenderBotSystem {

//    private BNO055IMU imu;
    private IMU imu;
    private double lastAngle = 0;
    private double previousHeading = 0;
    private double integratedHeading = 0;


    public SBBSensors(HardwareMap hm, DefenderBot b) {
	   super(hm, b);
	   imu = hm.get(IMU.class, "imu");
//	   BNO055IMU.Parameters params = new BNO055IMU.Parameters();
	   imu.initialize(
			 new IMU.Parameters(
				    new RevHubOrientationOnRobot(
						  RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
						  RevHubOrientationOnRobot.UsbFacingDirection.UP
				    )
			 )
	   );
    }

//    public double currentHeading() {
//        Orientation orientation = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
//        return orientation.thirdAngle;
//    }

//    public double getHeading() {
//        Orientation orientation;
//        orientation = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZXY, AngleUnit.DEGREES);
//        double roundedAngle = Math.round(100 * orientation.firstAngle) / 100;
//        lastAngle = roundedAngle;
//        return roundedAngle;
//    }

    public double getIntegratedHeading() {
	   Orientation myRobotOrientation;
	   myRobotOrientation = imu.getRobotOrientation(
			 AxesReference.EXTRINSIC,
			 AxesOrder.XYZ,
			 AngleUnit.DEGREES
	   );
	   double currentHeading = myRobotOrientation.thirdAngle;


//	   double currentHeading = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
	   double deltaHeading = currentHeading - previousHeading;

	   if (deltaHeading < -180) {
		  deltaHeading += 360;
	   } else if (deltaHeading >= 180) {
		  deltaHeading -= 360;
	   }

	   integratedHeading += deltaHeading;
	   previousHeading = currentHeading;

	   return integratedHeading;
    }
}