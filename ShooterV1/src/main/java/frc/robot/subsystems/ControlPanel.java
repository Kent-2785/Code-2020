/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private WPI_TalonSRX c_panel;
  private PIDController cPanelController;

  public ControlPanel() {
    c_panel = new WPI_TalonSRX(Constants.CONTROL_PANEL);
    cPanelController = new PIDController(2,1,0);

  }

  public void runPanel() 
  {
    c_panel.set(ControlMode.PercentOutput, cPanelController.calculate(c_panel.getMotorOutputPercent(), 1));
  }

  public void stopPanel()
  {
    c_panel.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
