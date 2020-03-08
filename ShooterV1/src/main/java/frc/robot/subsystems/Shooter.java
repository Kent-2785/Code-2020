/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.networktables.*;

/**
 * Add your docs here.
 */
public class Shooter extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX shooter1;
  private WPI_TalonSRX shooter2;

  
  public Shooter()
  {
    shooter1 = new WPI_TalonSRX(Constants.SHOOTER_1);
    shooter2 = new WPI_TalonSRX(Constants.SHOOTER_2);    
    TalonSRXSetUp();
  }
  
  public void TalonSRXSetUp()
  {
    shooter1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    
    shooter1,config_kF(0,0);
    shooter1.config_kP(0,0.1);
    shooter1.config_kI(0,0.1);
    shooter1.config_kD(0,0.1);

  }
  
  public void setShooter(double power) // move the shooter WPI_TalonSRXs with degsinated power
  {     
    shooter1.setVoltage(-power);
    shooter2.setInverted(OpposeMaster);
  }

  public void shootBall()
  {
    double kP = 0.1;
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    
    setShooter((100-ta)*kP);
  }
  
  public boolean readyToShoot()
  {
    return(shooter1.getSelectedSensorVelocity() >= 9000);
  }
  

}
