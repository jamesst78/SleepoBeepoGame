package model.units;

import java.util.ArrayList;

import simulation.Address;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;

public abstract class PoliceUnit extends Unit {

	private ArrayList<Citizen> passengers;
	private int maxCapacity;
	private int distanceToBase;

	public PoliceUnit(String unitID, Address location, int stepsPerCycle, int maxCapacity , WorldListener toAdd) {

		super(unitID, location, stepsPerCycle, toAdd);
		passengers = new ArrayList<Citizen>();
		this.maxCapacity = maxCapacity;

	}

	public int getDistanceToBase() {
		return distanceToBase;
	}

	public void setDistanceToBase(int distanceToBase) {
		this.distanceToBase = distanceToBase;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	
	public void cycleStep() {
		
		Address Targetloc = this.getTarget().getLocation();
		int distance = Targetloc.getX() + Targetloc.getY();



		if(this.getState() == UnitState.TREATING) {
				this.getTarget().treat();
				this.setState(UnitState.IDLE);
	
		}
		if(this.getState() == UnitState.RESPONDING) {
			if(//EL LOCATION BTA3 EL UNIT FE 0 , 0)
				distance= distance-this.getStepsPerCycle();
				this.setDistanceToTarget(distance);
		if(distance <=0) {
				this.setLocation(Targetloc);
				this.setState(UnitState.TREATING);
				
				
			if(//EL LOCATION BTA3 EL UNIT ALREADY 3ND EL TARGET)	
	}
}
}
	
	public void treat() {
		if(this.getTarget() instanceof ResidentialBuilding) {
			
			ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
			int distance = x.getLocation().getX() + x.getLocation().getY();
			this.setDistanceToBase(distance); 
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
			
			for(int j = 0 ; j<x.getOccupants().size() && passengers.size()< maxCapacity ; j++) {
				if(!x.getOccupants().get(j).getState().equals(CitizenState.DECEASED))
				passengers.add(x.getOccupants().get(j));
			}
			if(this.passengers.size() == this.maxCapacity) {
				if(distance <=0) {
					this.getWorldListener().assignAddress(this,0,0);
					//Hanfady el 3arabeya hena lazm n7ot listeners lel citizens el awel.
					if(!x.getOccupants().isEmpty()) {
						
					}
					if(x.getOccupants().isEmpty()) {
						this.jobsDone();
					}
					
				}
				else {
					distance = distance - this.getStepsPerCycle();
				}
				
			}
				
			
		}
	}

}
