/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.PneumaticsPractice;
import frc.robot.commands.Pullup;;


/**
 * Add your docs here.
 */
public class Pneumatics extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Solenoids
  Solenoid climber1 = new Solenoid(0);
  Solenoid climber2= new Solenoid(1);

  public void extendSolenoid()
  {
    climber1.set(true);
    climber2.set(true);
  }

  public void retractSolenoid()
  {
    climber1.set(false);
    climber2.set(false);
  }


  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new PneumaticsPractice());
    setDefaultCommand(new Pullup());
  }
}
