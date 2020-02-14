/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class MoveToCPHorizontal extends CommandBase {
  /**
   * Creates a new MoveToCPHorizontal.
   */
  private final DriveTrain a_train;
  public MoveToCPHorizontal(int setup) {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(setup==1)
    {
    
      //if(5.0> a_train.FREgetDistance();)
      //{a_train.drive(Constants.A_SPEED,0.0,0.0,false);}
    }
    if(setup==2){
      //(if(1.1> a_train.FREgetDistance())
     // {a_train.drive(Constants.A_SPEED,0.0,0.0,false);})
    if(3.1> a_train.FLEgetDistance())
      {a_train.drive(0.0,Constants.A_SPEED*-1,0.0,false);}
     //(if(3.9> a_train.FREgetDistance())
     // {a_train.drive(Constants.A_SPEED,0.0,0.0,false);})
    }
    if(setup==3)
    {
      if(5.57> a_train.FLEgetDistance())
      {a_train.drive(0.0,Constants.A_SPEED*-1,0.0,false);}
    }
    if(setup==5){
      if(3.1> a_train.FREgetDistance())
      {a_train.drive(0.0,Constants.A_SPEED,0.0,false);}
    }
    if(setup==6)
    {
      if(5.57> a_train.FREgetDistance())
      {a_train.drive(0.0,Constants.A_SPEED,0.0,false);}
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
