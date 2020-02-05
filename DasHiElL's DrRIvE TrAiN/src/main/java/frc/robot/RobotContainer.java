package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Climb;
import frc.robot.commands.DropTop;
import frc.robot.commands.HorizontalMovement;
import frc.robot.commands.PneumaticsPractice;
import frc.robot.commands.Pullup;
import frc.robot.commands.WheelOfFortune;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.MotorEncoder;
import frc.robot.subsystems.Pneumatics;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  public final static Drivetrain d_train = new Drivetrain();
  public final static Pneumatics p=new Pneumatics();
  private final static Joystick m_stick = new Joystick(0);
  public final static MotorEncoder spinny = new MotorEncoder();
  public final static ControlPanel w_spin = new ControlPanel();
  

  public static double getX() 
  {
    return m_stick.getX();
  }

  public static double getY()
  {
    return m_stick.getY();
  }

  public static double getZ()
  {
    return m_stick.getZ();
  }

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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

    new JoystickButton(m_stick, 1).whileHeld(new HorizontalMovement());
    //new JoystickButton(m_stick,2).whenPressed(new PneumaticsPractice());
    new JoystickButton(m_stick,2).whenPressed(new Pullup());
    new JoystickButton(m_stick,4).whenPressed(new DropTop());
    new JoystickButton(m_stick,5).whenHeld(new Climb());
    new JoystickButton(m_stick,6).whenHeld(new WheelOfFortune());

 }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand; //this some dumb shit
  }
}