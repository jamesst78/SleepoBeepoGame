package model.units;

import model.events.WorldListener;
import simulation.Address;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);

	}

}
