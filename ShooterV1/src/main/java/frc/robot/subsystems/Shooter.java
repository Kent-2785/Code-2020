/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.*;

/**
 * Add your docs here.
 */
public class Shooter extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX aimControl;
  private WPI_TalonSRX shooter1;
  private WPI_TalonSRX shooter2;

  private Encoder aimEncoder; 
  private Encoder shooterEncoder;

  private Ultrasonic toPowerPort;

  private PIDController shooterController;
  private PIDController aimController;

  private SimpleMotorFeedforward shooter_FeedForward;
  private SimpleMotorFeedforward aim_FeedForward;

  private static final double shooterLength = 0.23;

  public Shooter()
  {
    aimControl = new WPI_TalonSRX(Constants.AIM_CONTROL);
    shooter1 = new WPI_TalonSRX(Constants.SHOOTER_1);
    shooter2 = new WPI_TalonSRX(Constants.SHOOTER_2);

    aimEncoder = new Encoder(0,1);
    shooterEncoder = new Encoder(2,3);

    //toPowerPort = new Ultrasonic(pingChannel, echoChannel);

    aimEncoder.setDistancePerPulse(256);

    shooterController = new PIDController(1,0,0);
    aimController = new PIDController(1,0,0);

    shooter_FeedForward = new SimpleMotorFeedforward(Constants.SHOOTER_FEEDFORWARD_kS, Constants.SHOOTER_FEEDFORWARD_kV);
    aim_FeedForward = new SimpleMotorFeedforward(Constants.AIM_FEEDFORWARD_ks, Constants.AIM_FEEDFORWARD_kV);

  }
  
  public void setShooter(double power) // move the shooter WPI_TalonSRXs with degsinated power
  { 
    final double shooterOutput = shooterController.calculate(shooterEncoder.getRate(), power);
    final double feedForward = shooter_FeedForward.calculate(shooterEncoder.getRate());

    shooter1.setVoltage(shooterOutput + feedForward);
    shooter2.follow(shooter1);
  }

  public void collectBall()
  {
    setShooter(-0.5);
  }

  public void shootBall()
  {
    double kP = 0.1;
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    
    setShooter((100-ta)*kP);
  }

  public void changeAngle(double power) // moves the aim motor
  {
      final double aimOutput = aimController.calculate(aimEncoder.getRate(), power);
      final double FeedForward = aim_FeedForward.calculate(aimEncoder.getRate());

      aimControl.setVoltage(aimOutput + FeedForward);
  }

  public void setAngle(double angle) // pass in angle to set to in radians -> check to see if this works
  {

    if(angle > getAngleRadians())
    {
        while(angle != getAngleRadians())
        {
          changeAngle(getAngleRadians()*(2/Math.PI));
        }
    }
    else if(angle < getAngleRadians())
    {
        while(angle != getAngleRadians())
        {
          changeAngle((Math.PI - getAngleRadians())*(2/Math.PI));
        }
    }
  }

  public double getTargetAngle()
  {
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    return 100; // find number

  }

  public double getAngleRadians() // get the angle of the shooter
  {
    return (aimEncoder.getDistance()/shooterLength);
  }
  
  public double getShooterHeight() // get the y component of the shooter
  {
    return shooterLength*Math.sin(getAngleRadians());
  }

  public double getShooterBase() // get the x component of the shooter
  {
    return shooterLength*Math.cos(getAngleRadians());
  }


}
