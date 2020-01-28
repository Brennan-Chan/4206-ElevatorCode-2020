/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
//import edu.wpi.first.wpilibj.Counter;
//import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class controls the elevator 
 */
public class Motor extends Subsystem {
  
  public WPI_TalonSRX feeder = new WPI_TalonSRX(RobotMap.ELEVATOR_ONE);
  DigitalInput limitSwitch = new DigitalInput(1);
  DigitalInput bimitSwitch = new DigitalInput(2);
  
  /**
   * phisical limit switch code
   * I dont think it works as it has not been 
   * updated since 2016 
  Counter counter = new Counter(limitSwitch);
  Counter blimit = new Counter(bimitSwitch);
  
  
  public boolean isSwitchSet(){
    return counter.get() > 0;

    
  }

  public void intializeCounter(){
    counter.reset();
  }
  */
  
  public int getElevatorPosition() {

    return feeder.getSelectedSensorPosition(0);
  }

  public void setPower(double power)
  {
    feeder.configNominalOutputForward(0.0, 0);
    feeder.configNominalOutputReverse(0.0, 0);
    feeder.setNeutralMode(NeutralMode.Brake);
    
    feeder.set(ControlMode.Position, 0.0);
    feeder.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

    feeder.set(power);
  }
  public void stop(){
    feeder.configNominalOutputForward(0.0, 0);
    feeder.configNominalOutputReverse(0.0, 0);
    feeder.setNeutralMode(NeutralMode.Brake);
    feeder.set(0.1);
  }
  public void ShitGoDown(){
    //increase as needed 
    feeder.set(-.2);
  }
  public void ShitGoUp(){
    //increase as needed 
    feeder.set(.2);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
