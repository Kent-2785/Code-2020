/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants;
import frc.robot.commands.WheelOfFortune;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
/**
 * Add your docs here.
 */
public class ControlPanel extends SubsystemBase {
  private TalonSRX spinner;

  private static double kDt = 0.02;
  private final Encoder w_encoder = new Encoder(0, 2);

  private final TrapezoidProfile.Constraints m_constraints =
  new TrapezoidProfile.Constraints(1.75, 0.75);
  private final ProfiledPIDController w_controller =
  new ProfiledPIDController(1.3, 0.0, 0.7, m_constraints, kDt);
  public ControlPanel(){
    spinner= new TalonSRX(Constants.CONTROL_PANEL);
    w_encoder.setDistancePerPulse(1.0 / 360.0 * 2.0 * Math.PI * 1.5);
  }
  public double getDistance()
  {
     return w_encoder.getDistance();
  
  }
  public void SetSpin(double power)
  {
    double c= power;
    spinner.set(ControlMode.PercentOutput, w_controller.calculate(w_encoder.getRate(),c));
  }



  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WheelOfFortune());
  }
}
