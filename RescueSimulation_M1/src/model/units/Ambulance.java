package model.units;

import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;

public class Ambulance extends MedicalUnit {

	public Ambulance(String unitID, Address location, int stepsPerCycle,WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);

	}
	
	public void treat() {
		Citizen x = (Citizen)this.getTarget();
		x.getDisaster().setActive(false);
		if(x.getBloodLoss()==0) {
			this.heal();
		}
		else {
			if(x.getBloodLoss()-this.getTreatmentAmount() >0)
				x.setBloodLoss(x.getBloodLoss()-this.getTreatmentAmount());
			else {
				x.setBloodLoss(0);
				x.setState(CitizenState.RESCUED);
			}
		}
		
		
	}
	

	
	
	
	
}
