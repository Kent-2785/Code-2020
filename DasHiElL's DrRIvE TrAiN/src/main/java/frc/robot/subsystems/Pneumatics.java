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
  Solenoid solenoid1 = new Solenoid(0);
  Solenoid solenoidCHAD= new Solenoid(1);

  public void extendSolenoid()
  {
    solenoid1.set(true);
    solenoidCHAD.set(true);
  }

  public void retractSolenoid()
  {
    solenoid1.set(false);
    solenoidCHAD.set(false);
  }


  
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new PneumaticsPractice());
    setDefaultCommand(new Pullup());
  }
}