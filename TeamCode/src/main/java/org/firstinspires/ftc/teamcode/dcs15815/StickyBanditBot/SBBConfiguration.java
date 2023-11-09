package org.firstinspires.ftc.teamcode.dcs15815.StickyBanditBot;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderBotConfiguration;
import org.firstinspires.ftc.teamcode.dcs15815.DefenderFramework.DefenderBot.DefenderPresets;

public class SBBConfiguration extends DefenderBotConfiguration {

    /* DRIVETRAIN -------------------------------------------------- */

    public static String DRIVETRAIN_BACKLEFT_MOTOR_NAME = "back_left_motor";
    public static String DRIVETRAIN_FRONTLEFT_MOTOR_NAME = "front_left_motor";
    public static String DRIVETRAIN_FRONTRIGHT_MOTOR_NAME = "front_right_motor";
    public static String DRIVETRAIN_BACKRIGHT_MOTOR_NAME = "back_right_motor";
    public static DcMotorSimple.Direction DRIVETRAIN_BACKLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTLEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static DcMotorSimple.Direction DRIVETRAIN_FRONTRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction DRIVETRAIN_BACKRIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;

    public static double DRIVETRAIN_POWER_MAX = 0.35;
    public static int DRIVETRAIN_MAX_TICKS_PER_SECOND = 2800;

    /* LIFT -------------------------------------------------------- */

    public static String LIFT_LEFT_MOTOR_NAME = "lift_left_motor";
    public static String LIFT_RIGHT_MOTOR_NAME = "lift_right_motor";
    public static DcMotorSimple.Direction LIFT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction LIFT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static double LIFT_POWER_MAX = 1;
    public static int LIFT_POSITION_MAX = 2600;
    public static int LIFT_POSITION_GROUND = 0;
    public static int LIFT_POSITION_DELTA = 250;

    public static double LIFT_MOTOR_KP = 0;
    public static double LIFT_MOTOR_KI = 0;
    public static double LIFT_MOTOR_KD = 0;

    /* TILT -------------------------------------------------------- */

    public static String TILT_LEFT_MOTOR_NAME = "tilt_left_motor";
    public static String TILT_RIGHT_MOTOR_NAME = "tilt_right_motor";
    public static DcMotorSimple.Direction TILT_LEFT_MOTOR_DIRECTION = DcMotorSimple.Direction.REVERSE;
    public static DcMotorSimple.Direction TILT_RIGHT_MOTOR_DIRECTION = DcMotorSimple.Direction.FORWARD;
    public static double TILT_POWER_MAX = 0.5;
    public static int TILT_POSITION_MAX = 3080;
    public static int TILT_POSITION_UP = 2200;
    public static int TILT_POSITION_TRAVEL = 400;
    public static int TILT_POSITION_GROUND = 0;
    public static int TILT_POSITION_MIN = -600;
    public static int TILT_POSITION_DELTA = 250;

    public static double TILT_MOTOR_KP = 0;
    public static double TILT_MOTOR_KI = 0;
    public static double TILT_MOTOR_KD = 0;


    /* WRIST -------------------------------------------------------- */

    public static String WRIST_LEFT_SERVO_NAME = "wrist_left_servo";
    public static String WRIST_RIGHT_SERVO_NAME = "wrist_right_servo";
    public static double WRIST_LEFT_SERVO_POSITION_TOP = 0.225;
    public static double WRIST_RIGHT_SERVO_POSITION_TOP = 0.9;
    public static double WRIST_LEFT_SERVO_POSITION_STRAIGHT = 0.45;
    public static double WRIST_RIGHT_SERVO_POSITION_STRAIGHT = 0.55;
    public static double WRIST_LEFT_SERVO_POSITION_BOTTOM = 0.9;
    public static double WRIST_RIGHT_SERVO_POSITION_BOTTOM = 0.225;
    public static double WRIST_RIGHT_SERVO_POSITION_TRAVEL = 0.225;
    public static double WRIST_POSITION_DELTA = 0.1;


    /* STICKYPAD -------------------------------------------------------- */

    public static String STICKYPAD_LEFT_SERVO_NAME = "stickypad_left_servo";
    public static String STICKYPAD_RIGHT_SERVO_NAME = "stickypad_right_servo";
    public static double STICKYPAD_POSITION_GRAB = 1;
    public static double STICKYPAD_POSITION_RELEASE = 0.3;


    /* GAMEPADS -------------------------------------------------------- */

    public static double GAMEPAD2_RIGHT_STICK_CURVE = 2;
    public static double GAMEPAD2_RIGHT_STICK_MAX = 1;

    public static double GAMEPAD1_LEFT_STICK_Y_CURVE = 2.5;
    public static double GAMEPAD1_LEFT_STICK_Y_MAX = 1;

    public static double GAMEPAD1_RIGHT_STICK_X_CURVE = 2;
    public static double GAMEPAD1_RIGHT_STICK_X_MAX = 0.5;


    /* NAVIGATON -------------------------------------------------------- */

    public static String IMU_SENSOR_NAME = "imu";
    public static AxesOrder IMU_AXES_ORDER = AxesOrder.XYZ;

    // These are constants for the homegrown navigation.
    // This is unused since implementing RoadRunner
    public static double NAVIGATION_POWER_DEFAULT = 0.65;
    public static long NAVIGATION_TIMEOUT_DEFAULT = 10000;
    public static double NAVIGATION_TOLERANCE_ROTATION = 0.3;
    public static double NAVIGATION_TOLERANCE_X = 1.0;
    public static double NAVIGATION_TOLERANCE_Y = 1.0;
    public static double NAVIGATION_GEAR_RATIO = 1;
    public static long NAVIGATION_TICKS_PER_ROTATION = 292; // actually 292.04, geared at 3:1 (actually 2.89:1) and 4:1 (3.61)
//    public static long NAVIGATION_TICKS_PER_ROTATION = 280;
    public static double NAVIGATION_WHEEL_RADIUS = 1.88976;
//    public static double NAVIGATION_WHEEL_RADIUS = 2;
    public static double NAVIGATION_INCHES_PER_TICK = (2 * Math.PI * NAVIGATION_GEAR_RATIO * NAVIGATION_WHEEL_RADIUS) / NAVIGATION_TICKS_PER_ROTATION;
    public static double NAVIGATION_ROTATION_KP = 0.05;
    public static double NAVIGATION_ROTATION_KI = 0;
    public static double NAVIGATION_ROTATION_KD = 0;

    // Unused since moving to robot-relative RoadRunner movement instead of absolute
//    public static Pose2d NAVIGATION_START_RED_LEFT = new Pose2d(-65, 36, 0);
//    public static Pose2d NAVIGATION_START_RED_RIGHT = new Pose2d(-65, -36, 0);
//    public static Pose2d NAVIGATION_START_BLUE_LEFT = new Pose2d(65, -36, 0);
//    public static Pose2d NAVIGATION_START_BLUE_RIGHT = new Pose2d(65, 36, 0);

    /* DRONE LAUNCHER -------------------------------------------------------- */

    public static String DRONELAUNCHER_SERVO_NAME = "dronelauncher_servo";
    public static double DRONELAUNCHER_POSITION_HOLD = 1;
    public static double DRONELAUNCHER_POSITION_RELEASE = 0;

    /* VISION -------------------------------------------------------- */


    public static String CAMERA_NAME = "Webcam 1";
    public static double VISION_THRESHOLD_DETECTION = 18;

    /* PRESETS -------------------------------------------------------- */

    public static SBBArmPosition START_POSITION = new SBBArmPosition(
            SBBConfiguration.LIFT_POSITION_GROUND,
            SBBConfiguration.TILT_POSITION_GROUND,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

//    public static SBBArmPosition GRAB_READY_POSITION = new SBBArmPosition(
//            1350,
//            SBBConfiguration.TILT_POSITION_MIN,
//            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

    public static SBBArmPosition GRAB_CONTACT_POSITION = new SBBArmPosition(
            SBBConfiguration.LIFT_POSITION_GROUND,
            SBBConfiguration.TILT_POSITION_MIN,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

    public static SBBArmPosition GRAB_READY_POSITION = new SBBArmPosition(
            SBBConfiguration.LIFT_POSITION_GROUND,
            300,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

    public static SBBArmPosition AUTONOMOUS_DROP_POSITION = new SBBArmPosition(
            200,
            SBBConfiguration.TILT_POSITION_GROUND,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

    public static SBBArmPosition TRAVEL_POSITION = new SBBArmPosition(
            SBBConfiguration.LIFT_POSITION_GROUND,
            SBBConfiguration.TILT_POSITION_TRAVEL,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_TRAVEL);

    public static SBBArmPosition LEAVE_STACK_POSITION = new SBBArmPosition(
            SBBConfiguration.LIFT_POSITION_GROUND,
            300,
            SBBConfiguration.WRIST_RIGHT_SERVO_POSITION_BOTTOM);

    public static SBBArmPosition LOW_FRONT_DELIVERY_POSITION = new SBBArmPosition(
            0,
            635,
            0.425);

    public static SBBArmPosition AUTONOMOUS_FRONT_DELIVERY_POSITION = new SBBArmPosition(
            0,
            406,
            0.425);


    public static SBBArmPosition MID_FRONT_DELIVERY_POSITION = new SBBArmPosition(
            500,
            1000,
            0.425);

    public static SBBArmPosition HIGH_FRONT_DELIVERY_POSITION = new SBBArmPosition(
            0,
            1300,
            0.425);

    public static SBBArmPosition HIGH_BACK_DELIVERY_POSITION = new SBBArmPosition(
            0,
            2037,
            0.85);
    public static SBBArmPosition HANG_POSITION = new SBBArmPosition(
            1998,
            1523,
            .225);
    public static SBBArmPosition HANGING_POSITION = new SBBArmPosition(
            1998,
            31,
            .225);


    public static DefenderPresets<SBBArmPosition> ARM_PRESETS = new DefenderPresets<>(
            SBBConfiguration.START_POSITION,
            SBBConfiguration.TRAVEL_POSITION,
            SBBConfiguration.LOW_FRONT_DELIVERY_POSITION,
            SBBConfiguration.MID_FRONT_DELIVERY_POSITION,
            SBBConfiguration.HIGH_BACK_DELIVERY_POSITION
    );

    /* EFFECTS -------------------------------------------------------- */

    public static String EFFECTS_LEDS_NAME = "effects_leds";


    /* METHODS -------------------------------------------------------- */

    public SBBConfiguration() {
	   super();
    }

}