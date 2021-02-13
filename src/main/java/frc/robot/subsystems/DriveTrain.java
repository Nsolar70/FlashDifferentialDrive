// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.EncoderConstants;

public class DriveTrain extends SubsystemBase {

  private final WPI_TalonFX m_frontLeftMotor = new WPI_TalonFX(1); 
  private final WPI_TalonFX m_backLeftMotor= new WPI_TalonFX(2);
  private final WPI_TalonFX m_frontRightMotor = new WPI_TalonFX(3);
  private final WPI_TalonFX m_backRightMotor = new WPI_TalonFX(4);


  //Robots Drive Type: Differential Drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeftMotor, m_frontRightMotor);

   // Default threshold value from XboxController
   double joyThreshold = 0.05;

   
  /** Creates a new DriveTrain. */
  public DriveTrain() {

    //Set Masters and Followers
    m_backLeftMotor.follow(m_frontLeftMotor);
    m_backRightMotor.follow(m_frontRightMotor);

    m_frontLeftMotor.configFactoryDefault();
    m_backLeftMotor.configFactoryDefault();
    m_frontRightMotor.configFactoryDefault();
    m_backLeftMotor.configFactoryDefault();

   /* 
    m_frontLeftMotor.setInverted(true);
    m_backLeftMotor.setInverted(true);
    m_frontRightMotor.setInverted(true);
    m_backRightMotor.setInverted(true);
   */

 /* m_frontLeftMotor.configPeakCurrentLimit(30); // don't activate current limit until current exceeds 30 A ...
  talon.configPeakCurrentDuration(100); // ... for at least 100 ms
  talon.configContinuousCurrentLimit(20); // once current-limiting is actived, hold at 20A
  talon.enableCurrentLimit(true); */

   // Drive Train Encoder Setup
   m_frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
   m_frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
  }

  @Override
  public void periodic() {

    // Pushing Drive Encoder Data to the SmartDashboard
   SmartDashboard.putNumber(" FrontLeftSensorPosition", m_frontLeftMotor.getSelectedSensorPosition(Constants.EncoderConstants.kPIDLoopIdx));
   SmartDashboard.putNumber(" BackLeftSensorPosition", m_backLeftMotor.getSelectedSensorPosition(Constants.EncoderConstants.kPIDLoopIdx));
  }
    // This method will be called once per scheduler run
  /**
   * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param xSpeed        Speed of the robot in the x direction (forward/backwards).
   * @param ySpeed        Speed of the robot in the y direction (sideways).
   * 
   */
  
  //Arcade Drive from Drive via XboxController input   
  public void drive(double xSpeed, double ySpeed) {
  
    if(Math.abs(xSpeed) > joyThreshold || Math.abs(ySpeed) > joyThreshold) {
      m_drive.arcadeDrive(xSpeed, ySpeed);

      m_drive.arcadeDrive(ySpeed*-1.0, xSpeed*1.0);
    }
    else {
      m_drive.arcadeDrive(0.0, 0.0);
    }

  }
    
 public void stopMotion(){
   m_frontLeftMotor.set(ControlMode.PercentOutput, 0.0);
   m_frontRightMotor.set(ControlMode.PercentOutput, 0.0);

  }

}
  

  

  

