/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;


public class InvertedMecanumDrive extends CommandBase {
  /**
   * Creates a new InvertedMecanumDrive.
   */
  private final Joystick m_stick;
  
  public InvertedMecanumDrive(Joystick stick) {
    m_stick = stick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_train);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_train.drive(0, 0, 0,false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    final var xSpeed = -m_stick.getY();
    final var ySpeed = -m_stick.getX();
    final var rot = -m_stick.getZ();

    RobotContainer.m_train.drive(rot, ySpeed, xSpeed,false); // the xSpeed,ySpeed,and rot have to be position different than specified to work idk why
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_train.drive(0, 0, 0,false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
