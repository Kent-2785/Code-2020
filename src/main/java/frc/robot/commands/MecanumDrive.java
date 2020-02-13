/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class MecanumDrive extends CommandBase {
  /**
   * Creates a new MecanumDrive.
   */

   private boolean fieldRelative; 
   private final DriveTrain m_train;
   private final Joystick m_stick;

  public MecanumDrive(boolean fieldRelative, Joystick stick) {
    m_train = new DriveTrain();
    m_stick = stick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_train);
    this.fieldRelative = fieldRelative;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      final var xSpeed = m_stick.getX();
      final var ySpeed = m_stick.getY();
      final var rot = m_stick.getZ();
      
      m_train.drive(xSpeed, ySpeed, rot, this.fieldRelative);
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
