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

  private Spark shooter1;
  private Spark shooter2;

  private Encoder shooter1Encoder;
  private Encoder shooter2Encoder;

  private PIDController shooterController;

  private SimpleMotorFeedforward shooter1_FeedForward;
  private SimpleMotorFeedforward shooter2_FeedForward;


  private static final double shooterLength = 0.23;

  public Shooter()
  {
    shooter1 = new Spark(Constants.SHOOTER_1);
    shooter2 = new Spark(Constants.SHOOTER_2);

    shooter1Encoder = new Encoder(10,11);
    shooter2Encoder = new Encoder(12,13);

    shooterController = new PIDController(1,0,0);

    shooter1_FeedForward = new SimpleMotorFeedforward(Constants.SHOOTER_FEEDFORWARD_kS, Constants.SHOOTER_FEEDFORWARD_kV);
    shooter2_FeedForward = new SimpleMotorFeedforward(Constants.SHOOTER_FEEDFORWARD_kS, Constants.SHOOTER_FEEDFORWARD_kV);
  }
  
  public void setShooter(double power) // move the shooter WPI_TalonSRXs with degsinated power
  { 
    final double shooter1Output = shooterController.calculate(shooter1Encoder.getRate(), power);
    final double feedForward1 = shooter_FeedForward.calculate(shooter1Encoder.getRate());
    
    final double shooter2Output = shooterController.calculate(shooter2Encoder.getRate(), power);
    final double feedForward2 = shooter_FeedForward.calculate(shooter2Encoder.getRate());

    shooter1.setVoltage(shooter1Output + feedForward1);
    shooter2.setVoltage(shooter2Output + feedForward2);
  }

  public void shootBall()
  {
    double kP = 0.1;
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    
    setShooter((100-ta)*kP);
  }
  
  public void readyToShoot()
  {
  
  }
  
  public void resetEncoder()
  {
    shooter1Encoder.reset();
    shooter2Encoder.reset();
  }

}
