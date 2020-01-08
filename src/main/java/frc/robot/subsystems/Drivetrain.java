/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import frc.robot.commands.DriveArcade;
/**
 * Add your docs here.
 * 
 * @param <Public>
 */
public class Drivetrain extends Subsystem {

    TalonSRX leftFront;
    TalonSRX leftBack;
    TalonSRX rightFront;
    TalonSRX rightBack;

    DifferentialDrive diffDrive;

    Encoder driveEncoder;

    public Drivetrain() {

        leftFront = new TalonSRX(RobotMap.LEFT_FRONT);
        leftBack = new TalonSRX(RobotMap.LEFT_BACK);
        rightFront = new TalonSRX(RobotMap.RIGHT_FRONT);
        rightBack = new TalonSRX(RobotMap.RIGHT_BACK);

        driveEncoder = new Encoder(1, 2);
    }

    public void setFrontMotor(double speed)
    {
        leftFront.set(ControlMode.PercentOutput, speed);
        rightFront.set(ControlMode.PercentOutput, speed);
    }

    public void setBackMotor(double speed)
    {
        leftBack.set(ControlMode.PercentOutput, speed);
        rightBack.set(ControlMode.PercentOutput, speed);
    }
    
    public void setLeftMotor(double speed)
    {
        leftFront.set(ControlMode.PercentOutput, -speed);
        leftBack.set(ControlMode.PercentOutput, -speed);
    }

    public void setRightMotor(double speed)
    {
        rightFront.set(ControlMode.PercentOutput, speed);
        rightBack.set(ControlMode.PercentOutput, speed);
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new TankDrive());
        
    }
}
