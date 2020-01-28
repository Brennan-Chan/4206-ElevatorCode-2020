/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
Pneumatics subsystem for the hatch grabber
Holder solenoid handles the claw, while extender solenoid handles
the extension and retraction the grabber
*/
public class HatchGrabber extends Subsystem {
  public DoubleSolenoid extender = new DoubleSolenoid(0,1);

  public void shiftPiston(DoubleSolenoid piston){
    switch (piston.get()){
      case kOff:
        piston.set(DoubleSolenoid.Value.kForward);
        break;
      case kForward:
        piston.set(DoubleSolenoid.Value.kReverse);
        break;
      case kReverse:
        piston.set(DoubleSolenoid.Value.kForward);
        break;
    }
  }
  
  @Override
  public void initDefaultCommand(){
    setDefaultCommand(null);
  }
}