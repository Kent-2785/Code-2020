/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.Drive;
//import frc.robot.commands.DriveArcade;
/**
 * Add your docs here.
 * 
 * @param <Public>
 */
public class Drivetrain extends SubsystemBase {

  private TalonSRX frontLeft;
  private TalonSRX frontRight;
  private TalonSRX backLeft;
  private TalonSRX backRight;

    public Drivetrain() {
      frontLeft = new TalonSRX(Constants.FRONT_LEFT);
      frontRight = new TalonSRX(Constants.FRONT_RIGHT);
      backLeft = new TalonSRX(Constants.BACK_LEFT);
      backRight = new TalonSRX(Constants.BACK_RIGHT);
    }

    public void setFrontMotor(double speed)
  {
    frontRight.set(ControlMode.PercentOutput, speed);
    frontLeft.set(ControlMode.PercentOutput, speed);
  }

  public void setLeftMotor(double speed)
  {
    frontLeft.set(ControlMode.PercentOutput, speed);
    backLeft.set(ControlMode.PercentOutput, speed);
  }
  public void setRightMotor(double speed)
  {
    frontRight.set(ControlMode.PercentOutput, speed); 
    backLeft.set(ControlMode.PercentOutput, speed); 
  }

  public void setBackMotor(double speed)
  {
    backRight.set(ControlMode.PercentOutput, speed);
    backLeft.set(ControlMode.PercentOutput, speed);
  }

  public void TankDrive(double left, double right)
  {
    frontRight.set(ControlMode.PercentOutput, right);
    backRight.set(ControlMode.PercentOutput, right);

    frontLeft.set(ControlMode.PercentOutput, left);
    backLeft.set(ControlMode.PercentOutput, left);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }

}