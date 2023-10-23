package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SBBWrist extends DefenderBotSystem {

    private Servo leftServo, rightServo;
    public double position;

    static ExecutorService es = Executors.newSingleThreadExecutor();


    public SBBWrist(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   leftServo = hm.servo.get(SBBConfiguration.WRIST_LEFT_SERVO_NAME);
	   rightServo = hm.servo.get(SBBConfiguration.WRIST_RIGHT_SERVO_NAME);
    }

// ----------------------------------------

    public void setPosition(double l, double r) {
	   if ((r <= SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TOP) && (r >= SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM)) {
		  position = r;
		  leftServo.setPosition(l);
		  rightServo.setPosition(r);
	   }
    }

    public void setPosition(double p) {
	   if ((p <= SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TOP) && (p >= SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM)) {
		  setPosition(1 - p, p);
	   }
    }

// ----------------------------------------


    public void gotoTopPosition() {
	   setPosition(SBBConfiguration.WRIST_LEFT_SERVO_POSITION_TOP, SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TOP);
    }

    public void gotoStraightPosition() {
	   setPosition(SBBConfiguration.WRIST_LEFT_SERVO_POSITION_STRAIGHT, SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_STRAIGHT);
    }

    public void gotoBottomPosition() {
	   setPosition(SBBConfiguration.WRIST_LEFT_SERVO_POSITION_BOTTOM, SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);
    }

    public double getPosition() {
	   return position;
    }


}