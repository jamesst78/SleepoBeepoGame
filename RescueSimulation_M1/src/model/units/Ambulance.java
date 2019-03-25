package model.units;

import model.events.WorldListener;
import model.people.Citizen;
import simulation.Address;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle,WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);

	}
	
	public void cycleStep() {

	}
	
	
}
