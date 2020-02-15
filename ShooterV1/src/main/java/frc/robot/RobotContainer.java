/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Shooter m_shooter;
  private final DriveTrain m_train;
  private final ClimberPneumatics m_climber;
  private final MotorEncoder m_spinny;
  private final ControlPanel m_wheel;
  
  private Joystick m_stick; // Joystick for controlling shooting and collecting ball.
  private Joystick d_stick; // Joystick for controlling drive train.

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    
    m_spinny = new MotorEncoder();
    m_wheel = new ControlPanel();
    m_climber = new ClimberPneumatics();
    m_shooter = new Shooter();
    m_train = new DriveTrain();
    
    m_stick = new Joystick(1);
    d_stick = new Joystick(0);
     
    m_train.setDefaultCommand(new MecanumDrive(false, d_stick));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    new JoystickButton(d_stick, 1).whileHeld(new InvertedMecanumDrive(d_stick));
    // Button to control robot mechanism
    new JoystickButton(m_stick, 7).whileHeld(new CollectBall());
    new JoystickButton(m_stick, 8).whileHeld(new ShootMotion());
    new JoystickButton(m_stick,2).whenPressed(new Pullup());
    new JoystickButton(m_stick,4).whenPressed(new DropTop());
    new JoystickButton(m_stick,5).whenHeld(new Climb());
    new JoystickButton(m_stick,6).whenHeld(new WheelOfFortune());
  }

}
