/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.networktables.*;

public class VerticalAim extends CommandBase {
  /**
   * Creates a new VerticalAim.
   */
  private final Shooter m_shooter;
  private double ty;
  private double min_command;
  private double kP;
  private double heading_error;
  private double power;
  
  public VerticalAim() {
    m_shooter = new Shooter();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.setShooter(0);
    min_command = 0.05;
    kP = 0.1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    heading_error = -ty;

    if(ty > 0)
    {
      power = heading_error*kP - min_command;
    }
    else if (ty < 0)
    {
      power = heading_error*kP + min_command;
    }

    m_shooter.changeAngle(power);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.setShooter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
