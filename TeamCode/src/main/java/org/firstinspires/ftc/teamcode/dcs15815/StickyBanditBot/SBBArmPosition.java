package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class SBBArmPosition {
    public int liftPosition;
    public int tiltPosition;
    public double wristPosition;

    public SBBArmPosition(int lift, int tilt, double wrist) {
        liftPosition = lift;
        tiltPosition = tilt;
        wristPosition = wrist;
    }



}
