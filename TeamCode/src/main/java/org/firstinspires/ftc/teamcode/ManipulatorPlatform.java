package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;

public class ManipulatorPlatform
{
  private DcMotorEx wobbleMotor = null;
  private DcMotorEx shooterMotor = null;
  
  private Servo wobbleServo = null;
  private Servo forkServo = null;
  private Servo shooterServo = null;

//  private RevTouchSensor wobbleZeroSensor = null;
  //private RevTouchSensor extenderRetractedSensor = null;
  
  
  static final double wobbleP = 40;
  static final double wobbleI = 0;
  static final double wobbleD = 0;
  static final double wobbleF = 0;
  
  
  static final double shooterP = 5;
  static final double shooterI = 0;
  static final double shooterD = 0;
  static final double shooterF = 0;
  
  
  private final int extenderRetracted = 0;
  private final int extenderExtended = 700;
  
  ManipulatorPlatform(HardwareMap hardwareMap)
  {
    // Initialize the hardware variables. Note that the strings used here as parameters
    // to 'get' must correspond to the names assigned during the robot configuration
    // step (using the FTC Robot Controller app on the phone).
  
    wobbleServo = hardwareMap.get(Servo.class, "wobbleServo");
    forkServo = hardwareMap.get(Servo.class, "forkServo");
    shooterServo = hardwareMap.get(Servo.class, "shooterServo");
  
    //    wobbleZeroSensor = hardwareMap.get(RevTouchSensor.class,"wobbleSensor");
    
    wobbleMotor = (DcMotorEx) hardwareMap.get(DcMotor.class, "wobbleMotor");
    wobbleMotor.setDirection(DcMotor.Direction.FORWARD);
    PIDFCoefficients wobblePIDNew = new PIDFCoefficients(wobbleP, wobbleI, wobbleD, wobbleF);
    wobbleMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION, wobblePIDNew);
    wobbleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    wobbleMotor.setTargetPosition(0);
    wobbleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    wobbleMotor.setPower(1);

  
  
    shooterMotor = (DcMotorEx) hardwareMap.get(DcMotor.class, "shooterMotor");
    shooterMotor.setDirection(DcMotor.Direction.REVERSE);
    PIDFCoefficients shooterPIDNew = new PIDFCoefficients( shooterP, shooterI, shooterD, shooterF );
    shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,shooterPIDNew);
    shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    // shooterMotor.setPower(1);
    shooterMotor.setVelocity(0, DEGREES);
 
  }
  
  void setWobblePosition(int count)
  {
    wobbleMotor.setTargetPosition(count);
  }

//  void homeWobblePosition()
//  {
//    wobbleMotor.setPower(0.5);
//    wobbleMotor.setTargetPosition(5000);  // go far
//    while(!wobbleZeroSensor.isPressed())
//    {
//
//    }
//
//  }


//  void setShooterRPM(double RPMs)
//  {
//    shooterMotor.setPower(RPMs);
//  }
//
//  double getShooterRPM()
//  {
//   return shooterMotor.getVelocity(DEGREES) / 6; // degrees/second converted to RPM
//  }
  
  void setShooterRPM(double RPMs)
  {
    shooterMotor.setVelocity((RPMs * 6), DEGREES); // needs to be passed as degrees/second
  }
  
  double getShooterRPM()
  {
    return shooterMotor.getVelocity(DEGREES) / 6; // degrees/second converted to RPM
  }



  void wobbleExtend(boolean position)
  {
    if(position) // Closed (1)
    {
      wobbleServo.setPosition(1);
    }
    else
    {
      wobbleServo.setPosition(0);
    }
  }
  
  
  void forkExtend(boolean position)
  {
    if(position) // Closed (1)
    {
      forkServo.setPosition(1);
    }
    else
    {
      forkServo.setPosition(0);
    }
  }

  void shooterExtend(boolean position)
  {
    if(position) // Closed (1)
    {
      shooterServo.setPosition(1);
    }
    else
    {
      shooterServo.setPosition(0);
    }
  }
  
  

  /*  void resetExtender()
  {
    boolean calFlag = true;
    extenderPosition(-200);
    while( calFlag )
    {
      // wait till it hits the switch or stops moving
      if (extenderRetractedSensor.isPressed() || Math.abs(extenderMotor.getCurrentPosition()) > 150 )
      {
        calFlag = false;
      }
    }
    extenderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    extenderMotor.setTargetPosition(0);
    extenderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    extenderMotor.setPower(1);
  }*/

}
