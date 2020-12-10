package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.LinkedList;
import java.util.Queue;

@Autonomous(name = "Auto Primary", group = "Linear Opmode")

//@Disabled
public class AutonomousPrimary extends LinearOpMode
{

    // Declare OpMode members.
    private MecanumDriveChassis driveChassis;
    private ManipulatorPlatform manipulatorPlatform;
    private LEDs leds;

    private ElapsedTime manipulateimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

    // save for later
    // private static final float mmPerInch = 25.4f;
    // private static final float mmFTCFieldWidth = (12 * 6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
    // private static final float mmTargetHeight = (6) * mmPerInch; // the height of the center of the target image above the floor

    private boolean extendedFlag = false;

    private final int extenderRetracted  = 0;
    private final int extenderExtended  = 700;

    // All of the following will need to take less than the time allotted for autonomous...
    @Override
    public void runOpMode() {
        driveChassis = new MecanumDriveChassis(hardwareMap);
        manipulatorPlatform = new ManipulatorPlatform(hardwareMap);
        leds = new LEDs(hardwareMap);
        leds.goOff();

        // build all the drive plans for drive by distance (time in seconds)
        //
        // Each leg of the trip is added to the queue in this code block.
        // As the opmode runs, the queue sent to the drive base for execution.
        //
        // mode:     {FORWARD, BACKWARDS, LEFT (strafe), RIGHT (strafe), TURN}
        // speed:    Ignored in TURN mode: the drive speed from 0-100%  (Slower speeds for longer
        //           times will be more precise)
        // angle:    Ignored in all but TURN mode:
        //           the desired angle of travel relative to the ZERO orientation in DEGREES
        //           ZERO is where the bot was facing when the IMU calibrated.
        //           desired angle in degrees 0 to 360 CCW
        // distance: Only used for FORWARD, BACKWARD, LEFT, RIGHT, modes:  the TIME in seconds to run
        //           the motors.

        // This is just a test routine to test all driving modes.
        Queue<Leg> TestAllFunctions = new LinkedList<>();
        TestAllFunctions.add(new Leg(Leg.Mode.LEFT, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.RIGHT, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.FORWARD, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.TURN, 50, 90, 0));
        TestAllFunctions.add(new Leg(Leg.Mode.FORWARD, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.TURN, 50, 180, 0));
        TestAllFunctions.add(new Leg(Leg.Mode.FORWARD, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.TURN, 50, 270, 0));
        TestAllFunctions.add(new Leg(Leg.Mode.FORWARD, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.TURN, 50, 0, 0));
        TestAllFunctions.add(new Leg(Leg.Mode.LEFT, 50, 0, 1));
        TestAllFunctions.add(new Leg(Leg.Mode.RIGHT, 50, 0, 1));


        // These are the working paths for the OpMode
        Queue<Leg> PathPt1 = new LinkedList<>();
        PathPt1.add(new Leg(Leg.Mode.FORWARD,35, 0,6.0));
        PathPt1.add(new Leg(Leg.Mode.BACKWARDS,35, 0,1.5));

        // Path down here.
        Queue<Leg> PathPt2 = new LinkedList<>();
        PathPt2.add(new Leg(Leg.Mode.TURN, 50, 10, 0));
        PathPt2.add(new Leg(Leg.Mode.BACKWARDS,35, 0,1.5));
        PathPt2.add(new Leg(Leg.Mode.TURN, 50, 0, 0));
        PathPt2.add(new Leg(Leg.Mode.BACKWARDS,35, 0,1.0));
        PathPt2.add(new Leg(Leg.Mode.FORWARD,35, 0,0.3));

        // Path up here.
        Queue<Leg> PathPt3 = new LinkedList<>();
        PathPt3.add(new Leg(Leg.Mode.BACKWARDS,35, 0,0.2));
        PathPt3.add(new Leg(Leg.Mode.LEFT, 50, 0, 2.25));
        PathPt3.add(new Leg(Leg.Mode.FORWARD,35, 0,0.5));
        PathPt3.add(new Leg(Leg.Mode.LEFT, 50, 0, 1.3));
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // for each piece of the drive & manipulate plan you will need to load a plan and then put
        // a while loop, like the following example, that repeatedly calls the autoDrive method
        // until the driving is done.
        // Put manipulator movements between the driving loops.
        // If you need more driving load another plan and make another loop.

        // potentially do manipulation here.  Make sure it is done before moving on.

        driveChassis.startPlan(PathPt1);
        while (opModeIsActive() && driveChassis.isDriving())
        {
            // Process the drive chassis
            driveChassis.autoDrive(telemetry);
        }

        // After driving do your manipulation.  You may need a timer based state machine but simple
        // actions can just be done inline.

        // Do some manipulation...

        // spin for a second to allow whatever to finish (example of wait timer).
//        manipulateimer.reset();
//        while (opModeIsActive() && manipulateimer.time()< 1.0);
//
//        // do second part of drive plan.
//        driveChassis.startPlan(PathPt2);
//        while (opModeIsActive() && driveChassis.isDriving())
//        {
//            // Process the drive chassis
//            driveChassis.autoDrive(telemetry);
//        }

        // After driving do your manipulation.  You may need a timer based state machine but simple
        // actions can just be done inline.

        // Do some manipulation...

        // spin for a second to allow whatever to finish (example of wait timer).
//        manipulateimer.reset();
//        while (opModeIsActive() && manipulateimer.time()< 1.0);
//
//        // do third part of drive plan.
//        driveChassis.startPlan(PathPt3);
//        while (opModeIsActive() && driveChassis.isDriving())
//        {
//            // Process the drive chassis
//            driveChassis.autoDrive(telemetry);
//        }

        // potentially do manipulation here.  Make sure it is done before moving on because the OpMode will end.

    }
}