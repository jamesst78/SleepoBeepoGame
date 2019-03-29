package model.units;

import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {

		super(unitID, location, stepsPerCycle, worldListener);

	}
	public void treat() {
		
			
				ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
				if(x.getDisaster()!= null)
				x.getDisaster().setActive(false);
				
				int change = x.getFireDamage()-10;
				x.setFireDamage(change);
				if(x.getFireDamage()==0)
				this.jobsDone();
				
				
			}
				
				
			
		
	}


