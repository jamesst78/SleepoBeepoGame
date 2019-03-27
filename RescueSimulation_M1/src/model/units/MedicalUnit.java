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
<<<<<<< HEAD
		if(x.getBloodLoss()>0) {
			x.setBloodLoss(x.getBloodLoss()-this.treatmentAmount);
			}
			else {
				x.setState(CitizenState.RESCUED);
				this.heal();
				
			}
		
	}
			
		
=======
		
		if(x.getBloodLoss()<=0) {
		x.setState(CitizenState.RESCUED);
		this.heal();
				
		}
		else {
			x.setBloodLoss(x.getBloodLoss()-this.treatmentAmount);
		}
	}
>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
		
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
			
	
<<<<<<< HEAD
	
=======
	}
>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
	
	public void heal() {
<<<<<<< HEAD
			this.setState(UnitState.IDLE);
=======
		
		
>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
			Citizen x = (Citizen)this.getTarget();
			
			if(x.getHp()<100) {
				x.setHp(x.getHp() + this.healingAmount);
			}
			else {
				x.setState(CitizenState.SAFE);
				this.jobsDone();
				
			}
	}
	
	public void jobsDone() {
		
		this.setState(UnitState.IDLE);
		this.getTarget().equals(null);
	
}

}
