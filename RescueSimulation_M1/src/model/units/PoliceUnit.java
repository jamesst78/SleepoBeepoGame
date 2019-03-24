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
		
		
		//Law el unit responding
		//a7aded hal howa ray7 wla rag3?
		//I selected one of the two
		//ha call treat
		//h2leb el state le treating
		//el treat should load citizens on the car
		//BA2ET el cycle step , should deliver the citizens to the base / back to the location
		//once Im back ANDDDD theres a flag , en ana lesa m5lst4 , 
		//I'll call treat again
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
