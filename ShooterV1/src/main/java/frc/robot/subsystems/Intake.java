/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class Intake extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark IntakeMotor;
  private Solenoid IntakePneumatics1;
  private Solenoid IntakePneumatics2;

  private SimpleMotorFeedforward IntakeFeedForward;
  private edu.wpi.first.wpilibj.controller.PIDController IntakeController;


  public Intake()
  {
    IntakeMotor  = new Spark(Constants.INTAKE_MOTOR);
    IntakePneumatics1 = new Solenoid(0);
    IntakePneumatics2 = new Solenoid(1);

    IntakeController = new PIDController(1, 0, 0);
    IntakeFeedForward = new SimpleMotorFeedforward(1, 1);
  }

  public void collectBall(double power)
  {
    output = IntakeController.calculate(IntakeMotor.getSpeed(), power) + IntakeFeedForward.calculate(IntakeMotor.getSpeed());
    IntakeMotor.setVoltage(output);
  }
  
  public void extendIntake()
  {
    IntakePneumatics1.set(true);
    IntakePneumatics2.set(true);
  }
  
  public void retractIntake()
  {
    IntakePneumatics1.set(true);
    IntakePneumatics2.set(true);
  }

}
