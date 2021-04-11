package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.util.LinkedList;
import java.util.Queue;

@Autonomous(name = "Gatherer Test", group = "Linear Opmode")

//@Disabled
public class GathererTest extends LinearOpMode
{
  
  // Declare OpMode members.
  private MecanumDriveChassis driveChassis;
  private ManipulatorPlatform manipulatorPlatform;
  private LEDs leds;
  
  private ElapsedTime manipulateTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);
  
  // save for later
  // private static final float mmPerInch = 25.4f;
  // private static final float mmFTCFieldWidth = (12 * 6) * mmPerInch;       // the width of the FTC field (from the center point to the outer panels)
  // private static final float mmTargetHeight = (6) * mmPerInch; // the height of the center of the target image above the floor
  
  private boolean extendedFlag = false;
  
  private final int extenderRetracted  = 0;
  private final int extenderExtended  = 700;
  
  private final float searchTime = 5;  // sets the time (Seconds) to search for rings before declaring NONE.
  
  private final boolean forkExtend = true;
  private final boolean forkRetract = false;
  
  private final boolean shooterExtend = true;
  private final boolean shooterRetract = false;

//  private final double shooterSpeedFull = 0.73;
//  private final double shooterSpeedStop = 0;
  
  private final double shooterSpeedFull = 128;
  private final double shooterSpeedTolerance = 20;
  private final double shooterSpeedStop = 0;
  
  private final int gathererDown = 500;
  private final int gathererUp = 0;
  
  private Servo leftgathererServo = null;
  private Servo rightgathererServo = null;
  
  private final boolean leftgathererExtend = true;
  private final boolean leftgathererRetract = false;
  
  private final boolean rightgathererExtend = true;
  private final boolean rightgathererRetract = false;
  
  WebcamName webcamName;
  
  // All of the following will need to take less than the time allotted for autonomous...
  @Override
  public void runOpMode()
  {
    driveChassis = new MecanumDriveChassis(hardwareMap);
    manipulatorPlatform = new ManipulatorPlatform(hardwareMap);
  

    
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
    

    
    waitForStart();
    manipulatorPlatform.setGathererPosition(gathererDown);
    manipulateTimer.reset();
    while (opModeIsActive() && manipulateTimer.time()< 3.0);
    manipulatorPlatform.setGathererPosition(gathererUp);
  
    manipulateTimer.reset();
    while (opModeIsActive() && manipulateTimer.time()< 3.0);
    manipulatorPlatform.leftgathererExtend(leftgathererExtend);
    manipulateTimer.reset();
    while (opModeIsActive() && manipulateTimer.time()< 3.0);
    manipulatorPlatform.leftgathererExtend(leftgathererRetract);
    manipulatorPlatform.rightgathererExtend(rightgathererExtend);
    manipulateTimer.reset();
    while (opModeIsActive() && manipulateTimer.time()< 3.0);
    manipulatorPlatform.rightgathererExtend(rightgathererRetract);
  }
}