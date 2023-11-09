package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderPresets;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

public class StickyBanditBot extends DefenderBot {

    public SBBMecanumDrivetrain drivetrain;
    public SBBNavigation navigation;
    public SBBSensors sensors;
    public SBBVision vision;
    public SBBEffectsLeds leds;
    public SBBLift lift;
    public SBBTilt tilt;
    public SBBWrist wrist;
    public SBBStickyPad stickyPad;
    public SBBDroneLauncher droneLauncher;

    public DefenderPresets<SBBArmPosition> armPresets;
    public DefenderDelayedSequence grabPixelSequence;

    private PropVisionProcessor.PropPosition position;



    public StickyBanditBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(SBBMecanumDrivetrain.class);
	   vision = addSystem(SBBVision.class);
	   sensors = addSystem(SBBSensors.class);
	   navigation = addSystem(SBBNavigation.class);
	   navigation.linkDrivetrain(drivetrain);
	   lift = addSystem(SBBLift.class);
	   tilt = addSystem(SBBTilt.class);
	   wrist = addSystem(SBBWrist.class);
	   stickyPad = addSystem(SBBStickyPad.class);
	   leds = addSystem(SBBEffectsLeds.class);
//	   droneLauncher = addSystem(SBBDroneLauncher.class);

	   armPresets = SBBConfiguration.ARM_PRESETS;

	   grabPixelSequence = new DefenderDelayedSequence(
		  () -> {
			 gotoGrabReadyArmPosition();
			 gotoGrabContactArmPosition();
		  },
		  () -> {
			 gotoLeaveStackArmPosition();
		  },
		  1000
	   );

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

    public void gotoGrabContactArmPosition() {
	   gotoArmPosition(SBBConfiguration.GRAB_CONTACT_POSITION);
    }
    public void gotoGrabReadyArmPosition() {
	   gotoArmPosition(SBBConfiguration.GRAB_READY_POSITION);
    }

    public void runGrabPixelsMacro() {
	   grabPixelSequence.run();
    }


    public void gotoHangArmPosition() {
	   gotoArmPosition(SBBConfiguration.HANG_POSITION);
    }
    public void gotoHangingArmPosition() {
	   gotoArmPosition(SBBConfiguration.HANGING_POSITION);
    }

    public void gotoTravelArmPosition() {
	   gotoArmPosition(SBBConfiguration.TRAVEL_POSITION);
    }

    public void gotoLeaveStackArmPosition() {
	   gotoArmPosition(SBBConfiguration.LEAVE_STACK_POSITION);
    }

    public void gotoAutonomousDropArmPosition() {
	   gotoArmPosition(SBBConfiguration.AUTONOMOUS_DROP_POSITION);
    }

    public void gotoAutonomousFrontDeliveryArmPosition() {
	   gotoArmPosition(SBBConfiguration.AUTONOMOUS_FRONT_DELIVERY_POSITION);
    }

    public void gotoMidFrontDeliveryPosition() {
	   gotoArmPosition(SBBConfiguration.MID_FRONT_DELIVERY_POSITION);
    }

    public void gotoHighFrontDeliveryPosition() {
	   gotoArmPosition(SBBConfiguration.HIGH_FRONT_DELIVERY_POSITION);
    }


    public void gotoHighBackDeliveryPosition() {
	   gotoArmPosition(SBBConfiguration.HIGH_BACK_DELIVERY_POSITION);
    }

    public void gotoStartArmPosition() {
	   gotoArmPosition(SBBConfiguration.START_POSITION);
    }

}