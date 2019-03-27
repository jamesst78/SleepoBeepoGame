package model.units;

import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;

public abstract class FireUnit extends Unit {

	public FireUnit(String unitID, Address location, int stepsPerCycle , WorldListener worldListener) {

		super(unitID, location, stepsPerCycle,worldListener);

	}
	
	public void treat() {
		
			this.getTarget().getDisaster().setActive(false);
			ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
			
			if(x.getFireDamage()<=0) {
				this.jobsDone();
				
			}
			if(x.getFireDamage()>=100) {
				this.jobsDone();
				
			}		
			
			int change = x.getFireDamage()-10;
			x.setFireDamage(change);
			
			
			
		}

	}
	


