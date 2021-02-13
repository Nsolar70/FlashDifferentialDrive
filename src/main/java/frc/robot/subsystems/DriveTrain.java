// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
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
  private static double joyThreshold = 0.05;

  private  static final int kMaxNumberOfMotors = 4;
  private WPI_TalonFX[] m_TalonFXs = new WPI_TalonFX[kMaxNumberOfMotors];
  
   
  /** Creates a new DriveTrain. */
  public DriveTrain() {

    // add motors to array
    m_TalonFXs[0] = m_frontLeftMotor;
    m_TalonFXs[1] = m_backLeftMotor;
    m_TalonFXs[2] = m_frontRightMotor;
    m_TalonFXs[3] = m_backRightMotor;

    //Set Masters and Followers
    m_backLeftMotor.follow(m_frontLeftMotor);
    m_backRightMotor.follow(m_frontRightMotor);

    int talonIndex = 0;

    for(talonIndex = 0; talonIndex< kMaxNumberOfMotors; talonIndex++){
        m_TalonFXs[talonIndex].configFactoryDefault();
        m_TalonFXs[talonIndex].configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 20, 25, 1.0));
        m_TalonFXs[talonIndex].configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 10, 15, 0.5));
        m_TalonFXs[talonIndex].setNeutralMode(NeutralMode.Coast);
      
    }

   /* 
    m_frontLeftMotor.setInverted(true);
    m_backLeftMotor.setInverted(true);
    m_frontRightMotor.setInverted(true);
    m_backRightMotor.setInverted(true);
   */

     // Drive Train Encoder Setup
     m_frontLeftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
     m_frontRightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, EncoderConstants.kPIDLoopIdx, EncoderConstants.kTimeoutMs);
  }

  @Override
  public void periodic() {

    // Pushing Drive Encoder Data to the SmartDashboard
   SmartDashboard.putNumber(" FrontLeftSensorPosition", getLeftPosition());
   SmartDashboard.putNumber(" FrontRightSensorPosition", getRightPosition());

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
    m_drive.arcadeDrive(deadband(xSpeed), deadband(ySpeed), true);

  }
    
 private double deadband( double value){
   if (Math.abs(value)>joyThreshold){
     return value;
   }
   else {
    return 0.0;
   }

 }
 public void stopMotion(){
   m_frontLeftMotor.set(ControlMode.PercentOutput, 0.0);
   m_frontRightMotor.set(ControlMode.PercentOutput, 0.0);

  }

  /**
   * hello
   * @return
   * Native units for left Falcon drive encoder
   */
  public double getLeftPosition(){
    return  m_frontLeftMotor.getSelectedSensorPosition(Constants.EncoderConstants.kPIDLoopIdx);
  }

  /**
   * 
   * @return
   * NAtive units for right Falcon dive encoder
   */
  public double getRightPosition(){
    return  m_frontRightMotor.getSelectedSensorPosition(Constants.EncoderConstants.kPIDLoopIdx);
  }
}
  

  

  

