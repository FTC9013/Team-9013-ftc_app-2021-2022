package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.RADIANS;

public class ManipulatorPlatform
{
  private DcMotorEx wobbleMotor = null;

  private Servo wobbleServo = null;

  //private RevTouchSensor stonePresentSensor = null;
  //private RevTouchSensor extenderRetractedSensor = null;


  static final double wobbleP = 40;
  static final double wobbleI = 0;
  static final double wobbleD = 0;
  static final double wobbleF = 0;


  private final int extenderRetracted  = 0;
  private final int extenderExtended  = 700;

  ManipulatorPlatform(HardwareMap hardwareMap)
  {
    // Initialize the hardware variables. Note that the strings used here as parameters
    // to 'get' must correspond to the names assigned during the robot configuration
    // step (using the FTC Robot Controller app on the phone).

    wobbleServo = hardwareMap.get(Servo.class, "wobbleServo");

    wobbleMotor = (DcMotorEx)hardwareMap.get(DcMotor.class, "wobbleMotor");
    wobbleMotor.setDirection(DcMotor.Direction.REVERSE);
    PIDFCoefficients wobblePIDNew = new PIDFCoefficients( wobbleP, wobbleI, wobbleD, wobbleF );
    wobbleMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,wobblePIDNew);
    wobbleMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    wobbleMotor.setTargetPosition(0);
    wobbleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    wobbleMotor.setPower(1);

  }


  void setWobblePosition(int count)
  {
    wobbleMotor.setTargetPosition(count);
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
