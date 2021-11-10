package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Primary Tele-Op", group="Linear Opmode")
//@Disabled
public class TeleOpPrimary extends LinearOpMode
{
  // Declare OpMode members.
  private MecanumDriveChassis driveChassis;
  private ManipulatorPlatform manipulatorPlatform;
  //private LEDs leds;
  
  private final double highSpeed = 1.0;
  private final double lowSpeed = 0.5;
  
  private final int armGather = 0;
  private final int armLow = 1000;
  private final int armMid = 1600;
  private final int armMax = 2400;
  
  private final double spinnerSpeedFull = 0.75;
  private final double spinnerSpeedStop = 0;
  /*
  private final double shooterSpeedFull = 130;
  private final double shooterSpeedTolerance = 20;
  private final double shooterSpeedStop = 0;
  */
  
  private ElapsedTime runtime = new ElapsedTime();
  // a timer for the various automation activities.
  private ElapsedTime eventTimer = new ElapsedTime(ElapsedTime.Resolution.SECONDS);

  
  //private final double colorSpeed = 10; // change colors every 10 seconds
  //private double LEDTimeoutTime = 0;

  @Override
  public void runOpMode()
  {
    telemetry.addData("Status", "Initialized");
    telemetry.update();
    

    driveChassis = new MecanumDriveChassis(hardwareMap);
    manipulatorPlatform = new ManipulatorPlatform(hardwareMap);
    //leds = new LEDs(hardwareMap);
    //leds.goOff();

    // set dead zone to minimize unwanted stick input.
    //gamepad1.setJoystickDeadzone((float)0.05);
    manipulatorPlatform.setArmPosition(armGather);
    boolean goingFast = false;

    // Wait for the game to start (driver presses PLAY)
    waitForStart();
    runtime.reset();
    //leds.goConfetti();  // start the LEDs in confetti

    // run until the end of the match (driver presses STOP)
    while (opModeIsActive())
    {
      //set up counter
      // steps through color sequences every 'colorSpeed' seconds.
      /*
      if ( eventTimeOut( LEDTimeoutTime ) )
      {
        LEDTimeoutTime = eventTimer.time() + colorSpeed;  // load for next cycle
        //leds.goChangeColor();
      }
      */
      // send joystick inputs to the drive chassis
      driveChassis.drive(gamepad1.left_stick_y, gamepad1.left_stick_x,
                         gamepad1.right_stick_x, telemetry);
      
      // *** Driver controls (game pad 1)
      // provide a throttle capability to run the bot at one of two speeds.
        if (gamepad1.right_bumper && !goingFast)  // Go fast
        {
          driveChassis.setSpeedScale(highSpeed);
          goingFast = true;
        }
        if (gamepad1.right_bumper && goingFast)
        {
          driveChassis.setSpeedScale(lowSpeed);
          goingFast = false;
        }
    
        if(gamepad1.right_trigger > 0.1)
        {
          manipulatorPlatform.setSpinnerRPM(gamepad1.right_trigger);
        }
        if(gamepad1.left_trigger > 0.1)
        {
          manipulatorPlatform.setSpinnerRPM(gamepad1.left_trigger);
        }
        /*
        eventTimer.reset();
        while (opModeIsActive() && eventTimer.time() < 0.5)
        {
          driveChassis.autoDrive(telemetry);
        }
        //manipulatorPlatform.shooterExtend(shooterRetract);
        */
      
      
      // ***************************
      // Second seat...  controls (game pad 2)

// D‐PAD – Controls
      if (gamepad2.dpad_down)
      {
        manipulatorPlatform.setArmPosition(armGather);
      }
      if (gamepad2.dpad_right)
      {
        manipulatorPlatform.setArmPosition(armLow);
      }
      if (gamepad2.dpad_left)
      {
        manipulatorPlatform.setArmPosition(armMid);
      }
      if (gamepad2.dpad_up)
      {
        manipulatorPlatform.setArmPosition(armMax);
      }
      
  
      
      if(gamepad2.a)
      {
        manipulatorPlatform.setGatherPower(0.25);
      }
      if(gamepad2.y)
      {
        manipulatorPlatform.setGatherPower(-0.125);
      }
      if(gamepad2.b)
      {
        manipulatorPlatform.setGatherPower(0);
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