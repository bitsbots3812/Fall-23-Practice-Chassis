// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  final int LEFT_BACK_ID   = 0;
  final int LEFT_FRONT_ID  = 1;
  final int RIGHT_BACK_ID  = 2;
  final int RIGHT_FRONT_ID = 3;

  TalonSRX leftBack   = new TalonSRX(LEFT_BACK_ID);
  TalonSRX leftFront  = new TalonSRX(LEFT_FRONT_ID);
  TalonSRX rightBack  = new TalonSRX(RIGHT_BACK_ID);
  TalonSRX rightFront = new TalonSRX(RIGHT_FRONT_ID);

  TalonSRXGroup leftMotors = new TalonSRXGroup(new TalonSRX[] {leftFront, leftBack});
  TalonSRXGroup rightMotors = new TalonSRXGroup(new TalonSRX[] {rightFront, rightBack});

  DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors , rightMotors);

  Joystick DriverStick = new Joystick(0);

  @Override
  public void robotInit() {
    CameraServer.startAutomaticCapture();

    leftFront.setInverted(true);
    rightFront.setInverted(true);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {
    leftMotors.set(0.1);
    rightMotors.set(0.1);
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    
    /*
     * WPILIB DRIVE CONTROL
     *  AXIS CONVENTIONS
     *    <FORWARD>
     *              ^ +X
     *              |
     *       +RZ  __|
     *           /  |
     * + Y       V  |
     * <------------+
     */ 

    /* 
     * JOYSTICK AXES
     *   <FORWARD>
     * +------------>
     * |   |      + X
     * | <_/ +RZ
     * | 
     * | + Y
     * V
     * 
    */ 
    
    double throttle = map (-DriverStick.getRawAxis(3), -1, 1, 0, 1);

    differentialDrive.arcadeDrive(throttle * squareAxis(-DriverStick.getRawAxis(AxisType.kY.value)) , 
                                  throttle * squareAxis(-DriverStick.getRawAxis((AxisType.kX.value))));
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  double squareAxis(double value) {

    return (value < 0 ? -1 : 1) * Math.pow(value, 2);
  
  }

  double map(double val, double inMin, double inMax, double outMin, double outMax) {
    return (((val - inMin) / (inMax - inMin)) * (outMax - outMin)) + outMin;
  }

}



