// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDrive extends SubsystemBase {
  private SwerveModule brSwerveModule;
  private SwerveModule blSwerveModule;
  private SwerveModule frSwerveModule;
  private SwerveModule flSwerveModule;
  /** Creates a new ExampleSubsystem. */
  public SwerveDrive(SwerveModule brSwerveModule, SwerveModule blSwerveModule, SwerveModule frSwerveModule, SwerveModule flSwerveModule) {
    this.brSwerveModule = brSwerveModule;
    this.blSwerveModule = blSwerveModule;
    this.frSwerveModule = frSwerveModule;
    this.flSwerveModule = flSwerveModule;
  }
  public final double kLength = 18;
  public final double kWidth = 17;

  public void drive(double x,double y, double rotation){
    //calculate radius
    double radius = Math.sqrt((kLength*kLength)+(kWidth*kWidth));
    //invert y axis
    y *=-1;
    
    double a = x-rotation*(kLength/radius);
    double b = x+rotation*(kLength/radius);
    double c = y-rotation*(kWidth/radius);
    double d = y+rotation*(kWidth/radius);

    //compute speeds for each motor
    double backRightSpeed = Math.sqrt((a*a)+(d*d));
    double backLeftSpeed = Math.sqrt((a*a)+(c*c));
    double frontRightSpeed = Math.sqrt((b*b)+(d*d));
    double frontLeftSpeed = Math.sqrt((b*b)+(c*c));

    //compute wheel angles
    double backRightAngle = Math.atan2(a, d)/Math.PI;
    double backLeftAngle = Math.atan2(a, c)/Math.PI;
    double frontRightAngle = Math.atan2(b, d)/Math.PI;
    double frontLeftAngle = Math.atan2(b, c)/Math.PI;

    brSwerveModule.driveModule(backRightSpeed, backRightAngle);
    blSwerveModule.driveModule(backLeftSpeed, backLeftAngle);
    frSwerveModule.driveModule(frontRightSpeed, frontRightAngle);
    flSwerveModule.driveModule(frontLeftSpeed, frontLeftAngle);
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
