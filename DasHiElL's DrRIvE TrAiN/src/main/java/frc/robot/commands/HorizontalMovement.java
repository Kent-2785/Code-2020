/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HorizontalMovement extends CommandBase {
  /**
   * Creates a new HorizontalMovement.
   */
  public HorizontalMovement() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.d_train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double m_stickX = RobotContainer.getX();
    double m_stickY = RobotContainer.getY();
    double m_stickZ = RobotContainer.getZ();

    if(m_stickZ > 0 && Math.abs(m_stickY) < 0.1)
    {
      RobotContainer.d_train.setLeftMotor(m_stickZ*Constants.SPEED_ADJUST);
      RobotContainer.d_train.setRightMotor(-m_stickZ*Constants.SPEED_ADJUST);
    } else if (m_stickZ < 0 && Math.abs(m_stickY) < 0.1) {
      RobotContainer.d_train.setLeftMotor(-m_stickZ*Constants.SPEED_ADJUST);
      RobotContainer.d_train.setRightMotor(m_stickZ*Constants.SPEED_ADJUST);
    } else if( Math.abs(m_stickX) >= 0.1 ) {
      RobotContainer.d_train.setFrontMotor(m_stickX);
      RobotContainer.d_train.setBackMotor(-m_stickX);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}