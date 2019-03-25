package model.units;

import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;

public abstract class MedicalUnit extends Unit {

	private int healingAmount;
	private int treatmentAmount;

	public MedicalUnit(String unitID, Address location, int stepsPerCycle ,WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);
		healingAmount = 10;
		treatmentAmount = 10;

	}
	
	
		
	
	public void treat() {
		Citizen x = (Citizen)this.getTarget();
	this.getTarget().getDisaster().setActive(false);
	if(this instanceof Ambulance) {
		
		if(x.getBloodLoss()>0) {
		x.setBloodLoss(x.getBloodLoss()-this.treatmentAmount);
		}
		else {
			x.setState(CitizenState.RESCUED);
			this.heal();
			
		}
		
		if(this instanceof DiseaseControlUnit) {
			if(x.getToxicity() >0) {
				x.setToxicity(x.getToxicity()-this.treatmentAmount);
			
			}
			else {
				x.setState(CitizenState.RESCUED);
				this.heal();
			
			}
		}	
			
	}
	}
	
	public void heal() {
			Citizen x = (Citizen)this.getTarget();
			if(x.getHp()<100) {
				x.setHp(x.getHp() + this.healingAmount);
			}
			else {
				this.jobsDone();
			}
	}

}
