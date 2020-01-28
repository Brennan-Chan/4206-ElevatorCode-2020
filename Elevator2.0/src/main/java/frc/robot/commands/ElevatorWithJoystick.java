package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.subsystems.*;
//import frc.robot.subsystems.Motor;
//import frc.robot.OI;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorWithJoystick extends Command {
  private double power = 0.0;
  boolean m_isFinished;
  double m_position;
  int MaximumPosition = 24100;
  int MinimumPosition = 10;
  //DigitalInput forwardLimitSwitch, reverseLimitSwitch;
 

  public ElevatorWithJoystick(double power)
  {
    requires(Robot.motor);
    requires(Elevator.getInstance());
    //requires(Robot.oi);
    this.power = power;
  
    m_isFinished = false;
    

  }
  @Override
  protected void initialize() {
    //DigitalInput forwardLimitSwitch = new DigitalInput(0);
    //DigitalInput reverseLimitSwitch = new DigitalInput(1);
    //Motor.intializeCounter();
    //Motor.ElevatorWithJoystick();
    m_isFinished = false;
  }

  @Override
  protected void execute() {
    //TODO: if negative a positve values did not change 
    //please swap them in here so that we dont die

    //if works change to the max position
    while(Elevator.getInstance().getElevatorPosition() > 7100){
      Robot.motor.ShitGoDown();
      System.out.println("go down");
    }
    //if F(x) values return negative deleat 
    while(Elevator.getInstance().getElevatorPosition() < 10){
      Robot.motor.ShitGoUp();
      System.out.println("go up");
    }
    if(Elevator.getInstance().getElevatorPosition() < 8000){
      Elevator.getInstance().setElevatorPosition(10, -.2);
      //(Position desired , the speed at which its done)
      System.out.println("Returning to home base");
      m_isFinished = true;
    }
    //if works chage 7100 to the Max position
    else if(Elevator.getInstance().getElevatorPosition() < 7100)
    {
      Robot.motor.setPower(power);
    }
    /** 
    else if(Elevator.getInstance().getElevatorPosition() > MaximumPosition){
      //interrupted();
      Robot.motor.setPower(-(Math.abs(power)));
    }
    */
    //if the elevator hits the bottom 
    else if(Elevator.getInstance().getElevatorPosition() == 0){

      while(Elevator.getInstance().getElevatorPosition() < 10){
        Robot.motor.ShitGoUp();
      }
      m_isFinished = true;
      System.out.println("go up");
    }
    
    System.out.println("Current Height:" + Elevator.getInstance().getElevatorPosition());
    Timer.delay(.005);

    
  }

  @Override
  protected boolean isFinished() {
    //if(Robot.motor.forwardLimitSwitch.get())
    //return false;
    return m_isFinished;
  }

  @Override
  protected void end() {
    Robot.motor.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
