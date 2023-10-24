package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderPresets;

public class StickyBanditBot extends DefenderBot {

//    public SBBVision vision;
//    public SBBSensors sensors;
//    public SBBNavigation navigation;
    public SBBMecanumDrivetrain drivetrain;
    public SBBLift lift;
    public SBBTilt tilt;
    public SBBWrist wrist;
    public SBBStickyPad stickyPad;

    public DefenderPresets<SBBArmPosition> armPresets;


    public StickyBanditBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(SBBMecanumDrivetrain.class);
	   lift = addSystem(SBBLift.class);
	   tilt = addSystem(SBBTilt.class);
	   wrist = addSystem(SBBWrist.class);
	   stickyPad = addSystem(SBBStickyPad.class);

	   armPresets = SBBConfiguration.ARM_PRESETS;


	   //	   sensors = addSystem(SBBSensors.class);
//	   navigation = addSystem(SBBNavigation.class);
//	   navigation = new ProductionBotNavigation(hm, configuration, this);
//	   navigation.linkDrivetrain(drivetrain);
//	   vision = addSystem(SBBVision.class); // is something happening here in vision that is causing invisible crashes?



    }

    public void gotoArmPosition(SBBArmPosition p) {
	   lift.setPosition(p.liftPosition);
	   tilt.setPosition(p.tiltPosition);
	   wrist.setPosition(p.wristPosition);
    }

    public void gotoNextArmPosition() {
	   armPresets.selectNext();
	   gotoArmPosition(armPresets.selected());
    }

    public void gotoPreviousArmPosition() {
	   armPresets.selectPrevious();
	   gotoArmPosition(armPresets.selected());
    }

    public void gotoGrabArmPosition() {
	   gotoArmPosition(SBBConfiguration.GRAB_POSITION);
    }

    public void gotoHangArmPosition() {
	   gotoArmPosition(SBBConfiguration.HANG_POSITION);
    }

    public void gotoTravelArmPosition() {
	   gotoArmPosition(SBBConfiguration.TRAVEL_POSITION);
    }

    public void gotoStartArmPosition() {
	   gotoArmPosition(SBBConfiguration.START_POSITION);
    }

}