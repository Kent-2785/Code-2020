/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.commands.Climb;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;

/**
 * Add your docs here.
 */
public class MotorEncoder extends SubsystemBase {
 /* private PIDController m_pidController;
  private AnalogInput m_potentiometer;
  private SpeedController m_elevatorMotor;
  private Joystick m_joystick;
  private int m_index;
  private boolean m_previousButtonValue;
  
*/
  private static double kDt = 0.02;
  private final Encoder m_encoder = new Encoder(0, 1);

  private final TrapezoidProfile.Constraints m_constraints =
  new TrapezoidProfile.Constraints(1.75, 0.75);
  private final ProfiledPIDController m_controller =
  new ProfiledPIDController(1.3, 0.0, 0.7, m_constraints, kDt);
  private TalonSRX climber1;
  private TalonSRX climber2;
  public MotorEncoder(){
    climber1= new TalonSRX(Constants.CLIMBER_RIGHT);
    climber2= new TalonSRX(Constants.CLIMBER_LEFT);
   // m_potentiometer = new AnalogInput(Constants.KPOTCHANNEL);
  //m_elevatorMotor = new PWMVictorSPX(Constants.KMOTORCHANNEL);
 // m_joystick = new Joystick(Constants.KJOYSTICK);

  //m_pidController = new PIDController(Constants.P, Constants.I, Constants.D);
  
  }
  public double getDistance()
  {
     m_encoder.setDistancePerPulse(1.0 / 360.0 * 2.0 * Math.PI * 1.5);
     return m_encoder.getDistance();
  
  }
  public void SetClimbers()
  {
    double c= getDistance();
    climber1.set(ControlMode.PercentOutput, m_controller.calculate(c));
    climber2.set(ControlMode.Follower, m_controller.calculate(c));
  }


  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Climb());
  }
}
