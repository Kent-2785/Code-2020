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

  private WPI_TalonSRX shooter1;
  private WPI_TalonSRX shooter2;

  private Encoder shooterEncoder;

  private PIDController shooterController;

  private SimpleMotorFeedforward shooter_FeedForward;

  private static final double shooterLength = 0.23;

  public Shooter()
  {
    aimControl = new WPI_TalonSRX(Constants.AIM_CONTROL);
    shooter1 = new WPI_TalonSRX(Constants.SHOOTER_1);
    shooter2 = new WPI_TalonSRX(Constants.SHOOTER_2);

    shooterEncoder = new Encoder(0,1);
    shooterEncoder.setDistancePerPulse(256);

    shooterController = new PIDController(1,0,0);

    shooter_FeedForward = new SimpleMotorFeedforward(Constants.SHOOTER_FEEDFORWARD_kS, Constants.SHOOTER_FEEDFORWARD_kV);
  }
  
  public void setShooter(double power) // move the shooter WPI_TalonSRXs with degsinated power
  { 
    final double shooterOutput = shooterController.calculate(shooterEncoder.getRate(), power);
    final double feedForward = shooter_FeedForward.calculate(shooterEncoder.getRate());

    shooter1.setVoltage(shooterOutput + feedForward);
    shooter2.follow(shooter1);
  }

  public void shootBall()
  {
    double kP = 0.1;
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    
    setShooter((100-ta)*kP);
  }

  

}
