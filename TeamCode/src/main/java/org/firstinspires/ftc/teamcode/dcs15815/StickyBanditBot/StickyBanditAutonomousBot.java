package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;

public class StickyBanditAutonomousBot extends DefenderBot {

    public SBBStickyPad claw;
    public SBBVision vision;
    public SBBSensors sensors;
    public SBBNavigation navigation;
    public SBBMecanumDrivetrain drivetrain;
    public SBBLift lift;


    public StickyBanditAutonomousBot(HardwareMap hm, Class configClass, Telemetry t) {
	   super(hm, configClass, t);

	   drivetrain = addSystem(SBBMecanumDrivetrain.class);
	   lift = addSystem(SBBLift.class);
	   claw = addSystem(SBBStickyPad.class);
	   sensors = addSystem(SBBSensors.class);
	   navigation = addSystem(SBBNavigation.class);
	   vision = addSystem(SBBVision.class); // is something happening here in vision that is causing invisible crashes?
   }
}