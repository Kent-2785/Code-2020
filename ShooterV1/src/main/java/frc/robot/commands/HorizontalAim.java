/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.networktables.*;


public class HorizontalAim extends CommandBase {
  /**
   * Creates a new HorizontalAim.
   */
  private double tx;
  private double kP;
  private double min_command;
  private double speed;
  private double heading_error;

  public HorizontalAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_train.drive(0,0,0, false);
    min_command= 0.05;
    kP = -0.1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    heading_error = -tx;
    speed = 0;

    if(tx>0)
    {
      speed = heading_error*kP - min_command;
    }
    else if (tx<0)
    {
      speed = heading_error*kP + min_command;
    }

    RobotContainer.m_train.drive(0, speed, 0, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_train.drive(0,0,0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (tx <= 0.1 && tx >= -0.1;
  }
}
