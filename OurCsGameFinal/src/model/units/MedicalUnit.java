package model.units;

import simulation.Address;

public abstract class MedicalUnit extends Unit {
	static int healingAmount = 10;
	static int treatmentAmount = 10;

	MedicalUnit(String id, Address location, int stepsPerCycle){
		super(id,location,stepsPerCycle);
	}
}
