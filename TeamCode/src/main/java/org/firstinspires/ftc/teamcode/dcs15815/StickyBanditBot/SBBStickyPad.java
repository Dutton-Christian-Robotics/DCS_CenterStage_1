package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderUtilities.DefenderDelayedSequence;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SBBStickyPad extends DefenderBotSystem {

    private Servo leftServo, rightServo;
    public double position;

	public DefenderDelayedSequence rightReleaseSequence, leftReleaseSequence;

    public SBBStickyPad(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   leftServo = hm.servo.get(SBBConfiguration.STICKYPAD_LEFT_SERVO_NAME);
	   rightServo = hm.servo.get(SBBConfiguration.STICKYPAD_RIGHT_SERVO_NAME);

	   leftReleaseSequence = new DefenderDelayedSequence(
		  () -> { leftServo.setPosition(SBBConfiguration.STICKYPAD_POSITION_RELEASE); },
		  () -> { leftServo.setPosition(SBBConfiguration.STICKYPAD_POSITION_GRAB); },
		  2000
	   );
	   rightReleaseSequence = new DefenderDelayedSequence(
		  () -> { rightServo.setPosition(SBBConfiguration.STICKYPAD_POSITION_RELEASE); },
		  () -> { rightServo.setPosition(SBBConfiguration.STICKYPAD_POSITION_GRAB); },
		  2000
	   );

    }

// ----------------------------------------

    public void setPosition(double p) {
	   position = p;
	   leftServo.setPosition(p);
	   rightServo.setPosition(p);
    }

// ----------------------------------------


    public void gotoGrabPosition() {
	   setPosition(SBBConfiguration.STICKYPAD_POSITION_GRAB);
    }

    public void gotoReleasePosition() {
	   setPosition(SBBConfiguration.STICKYPAD_POSITION_RELEASE);
    }

    public void releaseLeft() {
	   leftReleaseSequence.run();
    }

    public void releaseRight() {
	   rightReleaseSequence.run();
    }

    public double getPosition() {
	   return position;
    }


}