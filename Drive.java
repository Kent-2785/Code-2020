package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Drive extends CommandBase {
  /**
   * Creates a new Drive.
   */
  public Drive() {
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
    
    if(m_stickX ==0 && m_stickY != 0) // Foward & Backward
    {
      RobotContainer.d_train.setLeftMotor(m_stickY*Constants.SPEED_ADJUST);
      RobotContainer.d_train.setRightMotor(m_stickY*Constants.SPEED_ADJUST);
    }

    if(m_stickX < 0) // Left & Right
    {
      RobotContainer.d_train.setLeftMotor(m_stickY*(1-(-1*m_stickX))); 
      RobotContainer.d_train.setRightMotor(m_stickY);
    }
    else 
    {
      RobotContainer.d_train.setRightMotor(m_stickY*(1-m_stickX));
      RobotContainer.d_train.setLeftMotor(m_stickY);
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