
package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



import frc.robot.commands.*;
import frc.robot.subsystems.*;

/*
  The VM is configured to automatically run this class, and to call the
  functions corresponding to each mode, as described in the TimedRobot
  documentation. If you change the name of this class or the package after
  creating this project, you must also update the build.gradle file in the
  project.
*/
public class Robot extends TimedRobot {
  //Subsystem instancing
  public static Elevator m_Elevator;

  public static RobotMap rm = new RobotMap();
  public static Motor motor = new Motor();
  public static OI oi;
  public static HatchGrabber hg = new HatchGrabber();
  public static Feeder fd = new Feeder();


  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  

  //INIT METHODS
  @Override
  public void robotInit(){
    oi = new OI();
    m_Elevator = Elevator.getInstance();
    SmartDashboard.putData("Teleop", m_chooser);
    
    
  }  

  @Override
  public void disabledInit(){
    System.out.println("You DIED");
  }

  @Override
  public void teleopInit(){
    //Cancels autonomous mode upon initializing operator control
    if (m_autonomousCommand != null){
      m_autonomousCommand.cancel();
    }
    if(!m_Elevator.isClosedLoop()) {
      m_Elevator.configClosedLoop();
    }
    if(oi.driver.getRawButton(10) && (m_Elevator.getElevatorMPosition() == 0)) {
      //m_Beak.setBeakGrab(false);
      Scheduler.getInstance().add(new SetElevator(Constants.ELEVATOR_LOW_GOAL));
    }
    System.out.print(m_Elevator.getElevatorPosition());
  }

  @Override
  public void autonomousInit(){
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null){
      m_autonomousCommand.start();
    }
  }

  //PERIODIC FUNCTIONS
  @Override
  public void robotPeriodic(){
    Scheduler.getInstance().run();
  }

  @Override
  public void disabledPeriodic(){
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic(){
    Scheduler.getInstance().run();

  
  
  }

  @Override
  public void testPeriodic(){
    Scheduler.getInstance().run();
  }

  
}