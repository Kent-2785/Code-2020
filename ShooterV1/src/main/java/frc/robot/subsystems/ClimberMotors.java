/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Spark


/**
 * Add your docs here.
 */
public class ClimberMotors extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX arm = new TalonSRX(Constants.ARM);
  private Spark pullerL = new Spark(0);
  private Spark pullerR = new Spark(1);
  public void Pullup(double speed)
  {
    pullerL.setSpeed(speed);
    pullerR.follow(pullerL);
  }
  public void Release(double speed)
  {
    pullerL.setSpeed(speed*-1);
    pullerR.follow(pullerL);
  }
  public void armExtend(double speed)
  {
    arm.set(ControlMode.PercentOutput, speed);
  }
  public void armRelease(double speed)
  {
    arm.set(ControlMode.PercentOutput, speed*-1);
  }

}
