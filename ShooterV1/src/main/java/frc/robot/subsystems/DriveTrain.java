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

  private  Encoder frontLeftEncoder;
  private  Encoder frontRightEncoder;
  private  Encoder backLeftEncoder;
  private  Encoder backRightEncoder;

  private Translation2d frontLeftLocation;
  private Translation2d frontRightLocation;
  private Translation2d backLeftLocation;
  private Translation2d backRightLocation;

  private PIDController frontLeftPIDController;
  private PIDController frontRightPIDController;
  private PIDController backLeftPIDController;
  private PIDController backRightPIDController;
  
  private SimpleMotorFeedforward m_Feedforward; 

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
    

    frontLeftEncoder = new Encoder(0, 1);
    frontRightEncoder = new Encoder(2, 3);
    backLeftEncoder = new Encoder(4, 5);
    backRightEncoder = new Encoder(6, 7);

    frontLeftLocation = new Translation2d(Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
    frontRightLocation = new Translation2d(Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);
    backLeftLocation = new Translation2d(-Constants.BaseToWheelFrontBack, Constants.BaseToWheelLeftRight);
    backRightLocation = new Translation2d(-Constants.BaseToWheelFrontBack, -Constants.BaseToWheelLeftRight);

    frontLeftPIDController = new PIDController(1, 0, 0);
    frontRightPIDController = new PIDController(1, 0, 0);
    backLeftPIDController = new PIDController(1, 0, 0);
    backRightPIDController = new PIDController(1, 0, 0);
    
    m_Feedforward = new SimpleMotorFeedforward(Constants.FEEDFOWARD_kS, Constants.FEEDFOWARD_kV);

    m_gyro = new AnalogGyro(0);
    m_gyro.reset();

    m_kinematics = new MecanumDriveKinematics(frontLeftLocation, frontRightLocation, backLeftLocation, backRightLocation);

    m_odometry = new MecanumDriveOdometry(m_kinematics, getAngle());
  }

  public Rotation2d getAngle()
  {
    return Rotation2d.fromDegrees(-m_gyro.getAngle());
  }

  public MecanumDriveWheelSpeeds getCurrentState() 
  {
    return new MecanumDriveWheelSpeeds(
        frontLeftEncoder.getRate(),
        frontRightEncoder.getRate(),
        backLeftEncoder.getRate(),
        backRightEncoder.getRate()
    );
  }
  
  public double getXDistance()
  {
      return frontLeftEncoder.getDistance(); //find the correcdt way to return x-distance
  }

  public double getYDistance()
  {
    double left = (frontLeftEncoder.getDistance() + backLeftEncoder.getDistance())/2;
    double right = (frontRightEncoder.getDistance() + backRightEncoder.getDistance()/2);
    return (left + right)/2;
  }

  public double getYDistanceFront()
  {
    return (getYDistance()+Constants.BaseToWheelFrontBack);
  }
  
  public void setSpeeds(MecanumDriveWheelSpeeds speeds) {
    
    final double frontLeftFeedFoward = m_Feedforward.calculate(speeds.frontLeftMetersPerSecond);
    final double frontRightFeedFoward = m_Feedforward.calculate(speeds.frontRightMetersPerSecond);
    final double backLeftFeedFoward = m_Feedforward.calculate(speeds.rearLeftMetersPerSecond);
    final double backRightFeedFoward = m_Feedforward.calculate(speeds.rearRightMetersPerSecond);
    
    final var frontLeftOutput = frontLeftPIDController.calculate(
        frontLeftEncoder.getRate(), speeds.frontLeftMetersPerSecond
    );
    final var frontRightOutput = frontRightPIDController.calculate(
        frontRightEncoder.getRate(), speeds.frontRightMetersPerSecond
    );
    final var backLeftOutput = backLeftPIDController.calculate(
        backLeftEncoder.getRate(), speeds.rearLeftMetersPerSecond
    );
    final var backRightOutput = backRightPIDController.calculate(
        backRightEncoder.getRate(), speeds.rearRightMetersPerSecond
    );

    frontLeft.setVoltage(frontLeftOutput + frontLeftFeedFoward);
    frontRight.setVoltage(frontRightOutput + frontRightFeedFoward);
    backLeft.setVoltage(backLeftOutput + backLeftFeedFoward);
    backRight.setVoltage(backRightOutput + backRightFeedFoward);
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
