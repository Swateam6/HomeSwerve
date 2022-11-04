// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.SwerveModule;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Create Joystick
  public Joystick swerveJoystick = new Joystick(0);
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  
  //fl = front left fr= front right bl= back left br= back right
  //TODO Set CAN ID's
  public final SwerveModule flSwerveModule = new SwerveModule(new CANSparkMax(14,MotorType.kBrushless), new CANSparkMax(15,MotorType.kBrushless));
  public final SwerveModule frSwerveModule = new SwerveModule(new CANSparkMax(14,MotorType.kBrushless), new CANSparkMax(15,MotorType.kBrushless));
  public final SwerveModule blSwerveModule = new SwerveModule(new CANSparkMax(14,MotorType.kBrushless), new CANSparkMax(15,MotorType.kBrushless));
  public final SwerveModule brSwerveModule = new SwerveModule(new CANSparkMax(14,MotorType.kBrushless), new CANSparkMax(15,MotorType.kBrushless));
  
  public final SwerveDrive swerveDrive = new SwerveDrive(brSwerveModule,blSwerveModule,frSwerveModule,flSwerveModule);

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //Setting a default command that gets the joysticks and sends them to SwerveDrive
    swerveDrive.setDefaultCommand(
      new RunCommand(()->{swerveDrive.drive(
        swerveJoystick.getRawAxis(0),swerveJoystick.getRawAxis(1),swerveJoystick.getRawAxis(3));}
        , swerveDrive)
    );
    // Configure the button bindings
    configureButtonBindings(
    
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
