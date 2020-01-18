/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.geometry.Translation2d;


import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;



/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

    TalonSRX frontLeft;
    TalonSRX frontRight;
    TalonSRX backLeft;
    TalonSRX backRight;

    public static double kMaxSpeed;

    private Translation2d m_frontLeftLocation;
    private Translation2d m_frontRightLocation;
    private Translation2d m_backLeftLocation;
    private Translation2d m_backRightLocation;
    private MecanumDriveKinematics m_kinematics;



    public DriveTrain()
    {
      frontLeft = new TalonSRX(Constants.FRONT_LEFT);
      frontRight = new TalonSRX(Constants.FRONT_RIGHT);
      backLeft = new TalonSRX(Constants.BACK_LEFT);
      backRight = new TalonSRX(Constants.BACK_RIGHT);
      kMaxSpeed = Constants.MAXSPEED; 

      m_frontLeftLocation = new Translation2d(Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
      m_frontRightLocation = new Translation2d(Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);
      m_backLeftLocation = new Translation2d(-Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
      m_backRightLocation = new Translation2d(-Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);

      m_kinematics = new MecanumDriveKinematics(m_frontLeftLocation, m_frontRightLocation, m_backLeftLocation, m_backRightLocation);
    }

    public void setSpeed(MecanumDriveWheelSpeeds speeds)
    {
      frontLeft.set(ControlMode.PercentOutput, speeds.frontLeftMetersPerSecond);
      frontRight.set(ControlMode.PercentOutput, speeds.frontRightMetersPerSecond);
      backLeft.set(ControlMode.PercentOutput, speeds.rearLeftMetersPerSecond);
      backRight.set(ControlMode.PercentOutput, speeds.rearRightMetersPerSecond);
    }

    public void drive(double xSpeed, double ySpeed, double rot) {
      var mecanumDriveWheelSpeeds = m_kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, ySpeed, rot));
      
      mecanumDriveWheelSpeeds.normalize(kMaxSpeed);
      setSpeed(mecanumDriveWheelSpeeds);
    }

}
