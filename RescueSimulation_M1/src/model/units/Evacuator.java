package model.units;

import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.CitizenState;
import simulation.Address;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle,WorldListener worldListener, int maxCapacity) {

		super(unitID, location, stepsPerCycle, worldListener,maxCapacity);

	}
	public void treat() {
		if(this.getTarget() instanceof ResidentialBuilding) {
			
		ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
		
		if(!x.getOccupants().isEmpty()) {
		for(int i = 0 ; i<x.getOccupants().size() && this.getPassengers().size()<this.getMaxCapacity() ; i++) {
				
					this.getPassengers().add(x.getOccupants().get(i));  //7ateeto fl 3rbya
					x.getOccupants().remove(i); 
					//removed mn el occupants
					
				
				
			}
		}
		
				
			
		}
	}

}
