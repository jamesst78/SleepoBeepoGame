package model.units;

import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.CitizenState;
import simulation.Address;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle , WorldListener worldListener) {

		super(unitID, location, stepsPerCycle, worldListener);

	}
	
	public void treat() {
		if(this.getTarget() instanceof ResidentialBuilding) {
		this.getTarget().getDisaster().setActive(false);
		ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
		if(x.getGasLevel() <=0) {
			this.jobsDone();
			return;
		}
		boolean AllDead = true;
		for(int i = 0 ; i<x.getOccupants().size() ; i++) {
			if(!x.getOccupants().get(i).getState().equals(CitizenState.DECEASED)) {
				AllDead = false;
			}
			
			if(AllDead) {
				this.jobsDone();
				return;
			}
			
		}
		
				
		int change = x.getGasLevel()-10;
		x.setGasLevel(change);
		
		
		
		//shouldnt we make it that if the Gas leve reaches 0 , it should end the treatment and start changing the state to idle?
		}
	}

}
