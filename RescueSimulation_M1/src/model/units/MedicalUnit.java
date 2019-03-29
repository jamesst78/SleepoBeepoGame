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
	x.getDisaster().setActive(false);
	if(this instanceof Ambulance) {
		
		
	
		
	}
			
		
		
		if(this instanceof DiseaseControlUnit) {
			
		}
	}
			
	
	
	
	public void heal() {
			
			Citizen x = (Citizen)this.getTarget();
			
			if(x.getHp()<100) {
				x.setHp(x.getHp() + this.healingAmount);
			}
			
			if(x.getHp()== 100) {
				x.setState(CitizenState.SAFE);
				this.jobsDone();
			}
	}
	
	public void jobsDone() {
		
		this.setState(UnitState.IDLE);
		this.getTarget().equals(null);
	
}




	public int getTreatmentAmount() {
		return treatmentAmount;
	}

}
