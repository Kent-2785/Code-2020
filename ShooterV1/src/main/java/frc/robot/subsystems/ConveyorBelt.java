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
  private SpeedControllerGroup c_belt;
  private boolean inverted;

  public ConveyorBelt() {
    belt1 = new Spark(Constants.BELT1);
    belt2 = new Spark(Constants.BELT2);
    c_belt = new SpeedControllerGroup(belt1, belt2);
    
    beltController = new PIDController(1, 0, 0);
    beltFeedForward = new SimpleMotorFeedforward(1, 1);
    inverted = false
  }

  public void moveBelt() // moves belt with set power
  {
    c_belt.set(beltController.calculate(c_belt.getSpeed(), 0.4);
  }

  public void invertBeltDirection() // invert the direction it move
  {
    inverted = !inverted;
    c_belt.setInverted(inverted);
  }
  
  public boolean getBeltDirection() // find the direction it moves, up means to shooter, down means to intake
  {
    return c_belt.getInverted();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
