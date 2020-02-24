/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorBelt extends SubsystemBase {
  /**
   * Creates a new ConveyorBelt.
   */
  private Spark belt1;
  private Spark belt2;
  private PIDController beltController;
  private SimpleMotorFeedforward beltFeedForward;

  public ConveyorBelt() {
    belt1 = new Spark(Constants.BELT1);
    belt2 = new Spark(Constants.BELT2);
    beltController = new PIDController(1, 0, 0);
    beltFeedForward = new SimpleMotorFeedforward(1, 1);
  }

  public void moveBelt() // moves belt with set power
  {

  }

  public void invertBeltDirection() // invert the direction it move
  {

  }
  
  public void getBeltDirection() // find the direction it moves, up means to shooter, down means to intake
  {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
