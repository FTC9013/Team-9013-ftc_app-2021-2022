package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Primary Tele-Op", group="Linear Opmode")
//@Disabled
public class
TeleOpPrimary extends LinearOpMode
{
  // Declare OpMode members.
  private MecanumDriveChassis driveChassis;
  private ManipulatorPlatform manipulatorPlatform;
  private LEDs leds;
  
  private final double highSpeed = 1.0;
  private final double lowSpeed = 0.5;
  
  private final int wobbleUp = 315;
  private final int wobbleDown = 0;
  private final int wobbleRelease = -315;
  
  private final boolean wobbleExtend = true;
  private final boolean wobbleRetract = false;
  
  private final boolean forkExtend = true;
  private final boolean forkRetract = false;
  
  private final boolean shooterExtend = true;
  private final boolean shooterRetract = false;
  
  private final boolean leftgathererExtend = true;
  private final boolean leftgathererRetract = false;
  
  private final boolean rightgathererExtend = true;
  private final boolean rightgathererRetract = false;

//  private final double shooterSpeedFull = 0.73;
//  private final double shooterSpeedStop = 0;
  
  private final double shooterSpeedFull = 130;
  private final double shooterSpeedTolerance = 20;
  private final double shooterSpeedStop = 0;
  
  private final int gathererDown = 260;
  private final int gathererUp = 25;
  private final int gathererPartialUp = 50;
  
  private Servo leftgathererServo = null;
  private Servo rightgathererServo = null;
  
  private final boolean gathererExtend = true;
  private final boolean gathererRetract = false;
  
  // private ElapsedTime runtime = new ElapsedTime();
  // a timer for the various automation activities.
  private ElapsedTime eventTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

  
  private final double colorSpeed = 10; // change colors every 10 seconds
  private double LEDTimeoutTime = 0;

  @Override
  public void runOpMode()
  {
    telemetry.addData("Status", "Initialized");
    telemetry.update();
    

    driveChassis = new MecanumDriveChassis(hardwareMap);
    manipulatorPlatform = new ManipulatorPlatform(hardwareMap);
    leds = new LEDs(hardwareMap);
    leds.goOff();

    // set dead zone to minimize unwanted stick input.
    gamepad1.setJoystickDeadzone((float)0.05);
    manipulatorPlatform.wobbleExtend(wobbleRetract);
    boolean goingFast = false;

    // Wait for the game to start (driver presses PLAY)
    waitForStart();
    // runtime.reset();
    leds.goConfetti();  // start the LEDs in confetti

    // run until the end of the match (driver presses STOP)
    while (opModeIsActive())
    {
      //set up counter
      // steps through color sequences every 'colorSpeed' seconds.
      if ( eventTimeOut( LEDTimeoutTime ) )
      {
        LEDTimeoutTime = eventTimer.time() + colorSpeed;  // load for next cycle
        leds.goChangeColor();
      }

      // send joystick inputs to the drive chassis
      driveChassis.drive(gamepad1.left_stick_y, gamepad1.left_stick_x,
                         gamepad1.right_stick_x, telemetry);
      
      manipulatorPlatform.setShooterRPM(shooterSpeedFull);
      
      // *** Driver controls (game pad 1)
      // provide a throttle capability to run the bot at one of two speeds.
      if (gamepad1.right_trigger > 0.5 && goingFast )  // Go fast
      {
        driveChassis.setSpeedScale(highSpeed);
        goingFast = false;
      }
      else if (gamepad1.right_trigger < 0.5 && !goingFast)
      {
        driveChassis.setSpeedScale(lowSpeed);
        goingFast = true;
      }
  
      if (gamepad1.right_bumper)
      {
        manipulatorPlatform.shooterExtend(shooterExtend);
        eventTimer.reset();
        while (opModeIsActive() && eventTimer.time() < 0.5)
        {
          driveChassis.autoDrive(telemetry);
        }
        manipulatorPlatform.shooterExtend(shooterRetract);
      }
      
      
      // ***************************
      // Second seat...  controls (game pad 2)

// D‐PAD – Controls
      
      if (gamepad2.dpad_up)
      {
        manipulatorPlatform.setGathererPosition(gathererPartialUp);
        eventTimer.reset();
        while (opModeIsActive() && eventTimer.time() < 0.5);
        manipulatorPlatform.setGathererPosition(gathererUp);
      }
  
      if (gamepad2.dpad_down)
      {
        manipulatorPlatform.setGathererPosition(gathererDown);
        manipulatorPlatform.leftgathererExtend(leftgathererExtend);
        manipulatorPlatform.rightgathererExtend(rightgathererExtend);
      }
  
      if (gamepad2.dpad_left)
      {
        manipulatorPlatform.leftgathererExtend(leftgathererExtend);
        manipulatorPlatform.rightgathererExtend(rightgathererExtend);
      }
  
      if (gamepad2.dpad_right)
      {
        manipulatorPlatform.leftgathererExtend(leftgathererRetract);
        manipulatorPlatform.rightgathererExtend(rightgathererRetract);
      }
      
      
      if (gamepad2.y)
      {
        manipulatorPlatform.wobbleExtend(wobbleExtend);
      }
      
      if (gamepad2.left_bumper)
      {
        manipulatorPlatform.wobbleExtend(wobbleRetract);
      }
  
      if (gamepad2.right_bumper)
      {
        manipulatorPlatform.forkExtend(forkRetract);
      }
  
      if (gamepad2.right_trigger > 0.5)
      {
        manipulatorPlatform.forkExtend(forkExtend);
      }

      // X – Pushes the fork mechanism down
      if (gamepad2.b)
      {
        manipulatorPlatform.setWobblePosition(wobbleRelease);
      }
  
      if (gamepad2.a)
      {
        manipulatorPlatform.setWobblePosition(wobbleDown);
      }

      if (gamepad2.x)
      {
        manipulatorPlatform.setWobblePosition(wobbleUp);
      }


/*      // provide a
      if (gamepad1.right_trigger > 0.5 && gamepad1.left_trigger > 0.5  )  //
      {
        // Do something when both triggers are pressed.
      }
      else if (gamepad1.right_trigger < 0.5 || gamepad1.left_trigger < 0.5 )
      {
        // Do the other thing you do when both triggers are not held down.
      }*/

    }
  }

  // test the event time
  private boolean eventTimeOut ( double eventTime)
  {
    return eventTimer.time() > eventTime;
  }
}



