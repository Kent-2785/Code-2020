/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;

public class CollectBall extends CommandBase {
  /**
   * Creates a new CollectBall.
   */
  
  Joystick m_stick;
  public CollectBall(Joystick stick) {
    m_stick = stick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_intake);
    addRequirements(RobotContainer.m_train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.collectBall(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double xSpeed = m_stick.getY() + RobotContainer.m_intake.xSpeedAssist();
    double ySpeed = m_stick.getX(); 
    double rot = m_stick.getZ() + RobotContainer.m_intake.rotAssist();
    
    m_intake.collectBall(0.4);
    drive(xSpeed, ySpeed, rot, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.collectBall(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
