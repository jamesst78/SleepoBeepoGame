package model.units;

import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);

	}
	
	public void treat() {
		Citizen x = (Citizen)this.getTarget();
		x.getDisaster().setActive(false);
		if(x.getToxicity() >0) {
			x.setToxicity(x.getToxicity()-this.getTreatmentAmount());
		
		}
		else {
			x.setState(CitizenState.RESCUED);
			this.heal();
		
		}
	}

}
