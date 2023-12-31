package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class SBBEffectsLeds extends DefenderBotSystem {
    private RevBlinkinLedDriver leds;

    public SBBEffectsLeds(HardwareMap hm, DefenderBot b) {
	   super(hm, b);
	   leds = hm.get(RevBlinkinLedDriver.class, SBBConfiguration.EFFECTS_LEDS_NAME);
    }

    public void setPattern(RevBlinkinLedDriver.BlinkinPattern p) {
	   leds.setPattern(p);
    }

    public void turnLightsOff() {
	   setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
    }

    public void setRedAllianceColors() {
	   setPattern(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED);
    }

    public void setBlueAllianceColors() {
	   setPattern(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE);
    }

    public void setAllianceColors() {
	   if (bot.alliance == DefenderBot.Alliance.BLUE) {
		  setBlueAllianceColors();
	   } else if (bot.alliance == DefenderBot.Alliance.RED) {
		  setRedAllianceColors();
	   } else {
		  setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_GOLD);
	   }
    }

}
