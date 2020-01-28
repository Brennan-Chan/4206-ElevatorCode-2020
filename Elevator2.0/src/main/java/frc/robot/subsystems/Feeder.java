/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.*;

public class Feeder extends Subsystem {
  
  public WPI_TalonSRX feeder1 = new WPI_TalonSRX(RobotMap.feeder1);
  public WPI_TalonSRX feeder2 = new WPI_TalonSRX(RobotMap.feeder2);


  public Feeder(){
  }

  public void setPower(double power){
    feeder1.set(power);
    feeder2.set(-power);
  }
  
  public void stop(){
    feeder1.set(0.15);
    feeder2.set(-0.15);
  }

  @Override
  public void initDefaultCommand(){
    setDefaultCommand(null);
  }
}
