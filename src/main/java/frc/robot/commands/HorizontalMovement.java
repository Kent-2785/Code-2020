/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class HorizontalMovement extends Command {
  public HorizontalMovement() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double m_stickX = Robot.m_oi.getXAxis();
    double m_stickY = Robot.m_oi.getYAxis();
    double m_stickZ = Robot.m_oi.getZ();
    if(m_stickZ > 0 && Math.abs(m_stickY) < 0.1 && Math.abs(m_stickX) < 0.1)
    {
      Robot.m_train.setLeftMotor(m_stickZ*RobotMap.SPEED_ADJUST);
      Robot.m_train.setRightMotor(-m_stickZ*RobotMap.SPEED_ADJUST);
    } else
    {
      Robot.m_train.setLeftMotor(-m_stickZ*RobotMap.SPEED_ADJUST);
      Robot.m_train.setRightMotor(m_stickZ*RobotMap.SPEED_ADJUST);
    }
    if( Math.abs(m_stickX) >= 0.1 )
    {
      Robot.m_train.setFrontMotor(m_stickX);
      Robot.m_train.setBackMotor(-m_stickX);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
