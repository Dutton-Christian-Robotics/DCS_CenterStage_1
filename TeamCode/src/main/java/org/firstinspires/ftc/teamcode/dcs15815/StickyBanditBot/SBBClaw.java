package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBot;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotSystem;

public class SBBClaw extends DefenderBotSystem {

    private Servo clawServo;

    public SBBClaw(HardwareMap hm, DefenderBot b) {
	   super(hm, b);

	   clawServo = hm.servo.get(SBBConfiguration.CLAW_SERVO_NAME);
    }

// ----------------------------------------

    public void setClawPosition(double p) {
	   clawServo.setPosition(p);
    }

// ----------------------------------------


    public void open() {
	   setClawPosition(SBBConfiguration.CLAW_POSITION_OPEN);
    }

    public void close() {
	   setClawPosition(SBBConfiguration.CLAW_POSITION_CLOSED);

    }


}