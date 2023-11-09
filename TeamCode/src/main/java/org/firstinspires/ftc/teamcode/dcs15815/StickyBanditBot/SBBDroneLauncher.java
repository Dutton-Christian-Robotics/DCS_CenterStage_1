package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SBBDroneLauncher extends DefenderBotSystem {

    private Servo servo;
    public double position;

    public SBBDroneLauncher(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   servo = hm.servo.get(SBBConfiguration.DRONELAUNCHER_SERVO_NAME);

    }

// ----------------------------------------

    public void setPosition(double p) {
	   position = p;
	   servo.setPosition(p);
    }

// ----------------------------------------

    public void gotoHoldPosition() {
	   setPosition(SBBConfiguration.DRONELAUNCHER_POSITION_HOLD);
    }

    public void gotoLaunchPosition() {
	   setPosition(SBBConfiguration.DRONELAUNCHER_POSITION_RELEASE);
    }

    public void launchDrone() {
	   gotoLaunchPosition();
    }

}