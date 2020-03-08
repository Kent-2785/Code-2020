/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;


/**
 * Add your docs here.
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX frontLeft;
  private WPI_TalonSRX frontRight;
  private WPI_TalonSRX backLeft;
  private WPI_TalonSRX backRight;

  public static final double kMaxSpeed = Constants.MAXSPEED;
  public static final double kMaxAngularSpeed = Math.PI;

  private Translation2d frontLeftLocation;
  private Translation2d frontRightLocation;
  private Translation2d backLeftLocation;
  private Translation2d backRightLocation;
  
  private AnalogGyro m_gyro;

  private MecanumDriveKinematics m_kinematics;
  private MecanumDriveOdometry m_odometry;

  public DriveTrain()
  {
    frontLeft = new WPI_TalonSRX(Constants.FRONT_LEFT);
    frontRight = new WPI_TalonSRX(Constants.FRONT_RIGHT);
    backLeft = new WPI_TalonSRX(Constants.BACK_LEFT);
    backRight = new WPI_TalonSRX(Constants.BACK_RIGHT);
    
    frontLeft.configNeutralDeadband(0.1);
    fronRight.configNeutralDeadband(0.1);
    backLeft.configNeutralDeadband(0.1);
    backRight.configNeutralDeadband(0.1);

    frontLeftLocation = new Translation2d(Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
    frontRightLocation = new Translation2d(Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);
    backLeftLocation = new Translation2d(-Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
    backRightLocation = new Translation2d(-Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);
    
    m_gyro = new AnalogGyro(0);
    m_gyro.reset();

    m_kinematics = new MecanumDriveKinematics(frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation);

    m_odometry = new MecanumDriveOdometry(m_kinematics, getAngle());
  }
  
  public void TalonSRXSetUp()
  {
     frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    frontLeft.config_kP(0, 1);
    frontRight.config_kP(0, 1);
    backLeft.config_kP(0, 1);
    backRight.config_kP(0, 1);

    frontLeft.config_kI(0, 0);
    frontRight.config_kI(0, 0);
    backLeft.config_kI(0, 0);
    backRight.config_kI(0, 0);

    frontLeft.config_kD(0, 0);
    frontRight.config_kD(0, 0);
    backLeft.config_kD(0, 0);
    backRight.config_kD(0, 0);

    frontLeft.config_kF(0, 0);
    frontRight.config_kF(0, 0);
    backLeft.config_kF(0, 0);
    backRight.config_kF(0, 0);
  }

  public Rotation2d getAngle()
  {
    return Rotation2d.fromDegrees(-m_gyro.getAngle());
  }

  public MecanumDriveWheelSpeeds getCurrentState() 
  {
    return new MecanumDriveWheelSpeeds(
        frontLeft.getSelectedVelocity(),
        frontRight.getSelectedVelocity(),
        backLeft.getSelectedveclotiy(),
        backRight.getSelectedVelocity()
    );
  }
  
  public void setSpeeds(MecanumDriveWheelSpeeds speeds) {

    final var frontLeftOutput = speeds.frontLeftMetersPerSecond;
    final var frontRightOutput = speeds.frontRightMetersPerSecond;
    final var backLeftOutput = speeds.rearLeftMetersPerSecond;
    final var backRightOutput = speeds.rearRightMetersPerSecond;

    frontLeft.setVoltage(frontLeftOutput);
    frontRight.setVoltage(frontRightOutput);
    backLeft.setVoltage(backLeftOutput);
    backRight.setVoltage(backRightOutput);
  }

  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    var mecanumDriveWheelSpeeds = m_kinematics.toWheelSpeeds(
        fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed, ySpeed, -rot, getAngle()
        ) : new ChassisSpeeds(xSpeed, ySpeed, -rot)
    );
    mecanumDriveWheelSpeeds.normalize(kMaxSpeed);
    setSpeeds(mecanumDriveWheelSpeeds);
  }

  public void updateOdometry() {
    m_odometry.update(getAngle(), getCurrentState());
  }

}
