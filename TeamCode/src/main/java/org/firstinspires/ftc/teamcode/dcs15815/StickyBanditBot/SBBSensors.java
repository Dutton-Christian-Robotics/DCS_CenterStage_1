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
    private double globalAngle;

    Orientation lastAngles = new Orientation();

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

public double currentHeading() {
	   return getAngle();
}
    public double getAngle()
    {
	   Orientation angles = imu.getRobotOrientation(
			 AxesReference.EXTRINSIC,
			 AxesOrder.XYZ,
			 AngleUnit.DEGREES
	   );

	   double deltaAngle = angles.thirdAngle - lastAngles.thirdAngle;

	   if (deltaAngle < -180)
		  deltaAngle += 360;
	   else if (deltaAngle > 180)
		  deltaAngle -= 360;

	   globalAngle += deltaAngle;

	   lastAngles = angles;

	   return globalAngle;
    }

    public void resetAngle() {
	   lastAngles = imu.getRobotOrientation(
			 AxesReference.EXTRINSIC,
			 AxesOrder.XYZ,
			 AngleUnit.DEGREES
	   );

	   globalAngle = 0;
    }

}