// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class SwerveModule extends SubsystemBase {
  //Setup for motors, encoders, and PID  
  public CANSparkMax driveMotor;
  public CANSparkMax angleMotor;
  public SparkMaxPIDController drivePID;
  public SparkMaxPIDController anglePID;

  //Constants for PID, untuned.
  private double kP = 0.001;
  private double kI = 0.0;
  private double kD = 0.000;



  public SwerveModule(CANSparkMax driveMotor, CANSparkMax angleMotor) {
    this.driveMotor = driveMotor;
    this.angleMotor=angleMotor;

    //PID
    drivePID = driveMotor.getPIDController();
    anglePID = angleMotor.getPIDController();
    
    //FeedForwards copied from rapidReact shooter code, might need to be changed
    drivePID.setFF(1.0/Constants.kNeoMaxRPM);
    anglePID.setFF(1.0/Constants.kNeoMaxRPM);

    //Set pid values
    drivePID.setP(kP);
    anglePID.setP(kP);
    drivePID.setI(kI);
    anglePID.setI(kI);
    drivePID.setD(kD);
    anglePID.setD(kD);
  }
  public void driveModule(double speed, double angle){
      //Set speed for drive motor
      driveMotor.set(speed);
      //convert the angle to a rpm between 0 and the max rpm
      double setpoint = angle * (Constants.kNeoMaxRPM * 0.5)+(Constants.kNeoMaxRPM*0.5);
      if (setpoint<0){
          setpoint = Constants.kNeoMaxRPM+setpoint;
      }
      if (setpoint>Constants.kNeoMaxRPM){
          setpoint = setpoint-Constants.kNeoMaxRPM;
      }
      anglePID.setReference(setpoint, ControlType.kVelocity);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
