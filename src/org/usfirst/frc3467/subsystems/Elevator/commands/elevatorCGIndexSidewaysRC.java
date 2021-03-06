package org.usfirst.frc3467.subsystems.Elevator.commands;

import org.usfirst.frc3467.subsystems.Elevator.Conveyor;
import org.usfirst.frc3467.subsystems.Elevator.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class elevatorCGIndexSidewaysRC extends CommandGroup {
    

	
    public  elevatorCGIndexSidewaysRC() {
        

    	// Lift RC above Indexer (and drive conveyor in at same time)
    	addParallel(new conveyorDrive(Conveyor.kIntakeHold));
    	addSequential(new elevatorToPosition(Elevator.kLevelIndexSidewaysRC, 2.0));

    	// Make sure Indexer is  engaged
    	addSequential(new indexerOperate(2));
    	
    	// Wait half a sec
    	addSequential(new WaitCommand(0.5));
    	
    	// Turn off conveyor
    	addSequential(new conveyorDrive(Conveyor.kStop));

    	// Lower conveyor back to "resting position" in two steps: manual@fixed speed, then PID to hold
    	addSequential(new elevatorDriveToPosition(Elevator.kDown_Fixed, Elevator.kLevelTwo));
    	addSequential(new elevatorToPosition(Elevator.kLevelZero));

    	// TODO: Add command to add 1 to tote count in Elevator
    }
}
