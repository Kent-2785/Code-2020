/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class TankDrive extends Command {
  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_train);
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
    

    if(m_stickX ==0 && m_stickY != 0)
    {
      
        Robot.m_train.setLeftMotor(-m_stickY*RobotMap.SPEED_ADJUST);
        Robot.m_train.setRightMotor(-m_stickY*RobotMap.SPEED_ADJUST);
    }
    else if(m_stickX > 0)
    {
      Robot.m_train.setRightMotor(m_stickY*(1-m_stickX)); 
      Robot.m_train.setLeftMotor(m_stickY);
    }
    else
    {
      Robot.m_train.setLeftMotor(m_stickY*(1-(-1*m_stickX)));
      Robot.m_train.setRightMotor(m_stickY);
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
    Robot.m_train.setLeftMotor(0);
    Robot.m_train.setRightMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
