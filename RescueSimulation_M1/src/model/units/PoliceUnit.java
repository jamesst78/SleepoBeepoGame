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
	private boolean ToGoBackToBuilding;
	private boolean  ToGoBackToBase;

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
		ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
		
		if(this.getState().equals(UnitState.RESPONDING)) {
			for(int i = 0 ; i<x.getOccupants().size() ; i++) {
				if(!x.getOccupants().get(i).getState().equals(CitizenState.DECEASED)) {
					break;
				}
				else {
					this.jobsDone();
					return;
				}
			}
			
			int distance = x.getLocation().getX() + x.getLocation().getY();
			this.setDistanceToBase(distance);
			if(distance<=0) {
				this.setLocation(x.getLocation());
				//dlw2ty 27na wasalna for the first time. We need to load up the citizens and make sure TO CHECK B3DAHA if there are any left. Also turn state into treating and call treat method
				this.setState(UnitState.TREATING);
				this.treat();
				this.ToGoBackToBase = true;
			}
			else {
			distance = distance - this.getStepsPerCycle();
			}
			
			
		if(this.getState().equals(UnitState.TREATING) && this.ToGoBackToBase ) {
				
				int distance2 = this.getDistanceToBase();
				if(distance2<=0) {
					this.getWorldListener().assignAddress(this, 0, 0);
					//big shit
					for(int i = 0 ; i<this.passengers.size() ; i++) { //bafady el citizens
							this.passengers.get(i).getWorldListener().assignAddress(this.passengers.get(i), 0, 0);
							this.passengers.get(i).setState(CitizenState.RESCUED);
							this.passengers.remove(i);
					}
						this.ToGoBackToBase = false; //saying eno ana 5las 5lst el base boolean
						if(x.getOccupants().size() == 0){
							this.ToGoBackToBuilding = false;
							this.jobsDone();
							return;
						}
							
					for(int i = 0 ; i<x.getOccupants().size() ; i++) { //checking if I have a reason to go back
						if(!x.getOccupants().get(i).getState().equals(CitizenState.DECEASED)) {
							this.ToGoBackToBuilding = true; //if i have a reason , set its boolean eny raye7 tany to the building
							break;
						
						}
						else {
							this.ToGoBackToBuilding = false;
							this.jobsDone();
										
						}
								
					}
				}
					else {
						distance2 = distance2 - this.getStepsPerCycle();
					}
					
			}
			
			if(this.getState().equals(UnitState.TREATING) && this.ToGoBackToBuilding ) {
				
				int distance3 = x.getLocation().getX() + x.getLocation().getY();
				if(distance3 <=0) {				
					this.treat(); //3abeet
					this.ToGoBackToBuilding = false;
					this.ToGoBackToBase = true;
					
									
				}
				else {
					distance3 = distance3-this.getStepsPerCycle();
					
				}
					
				
			}
			
		}
		
						
		
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
		
		if(!x.getOccupants().isEmpty()) {
		for(int i = 0 ; i<x.getOccupants().size() && this.passengers.size()<this.maxCapacity ; i++) {
				if(!x.getOccupants().get(i).getState().equals(CitizenState.DECEASED)) {
					passengers.add(x.getOccupants().get(i));  //7ateeto fl 3rbya
					x.getOccupants().remove(i);    //removed mn el occupants
					
				}
				
			}
		}
		
				
			
		}
	}

	public ArrayList<Citizen> getPassengers() {
		return passengers;
	}

}
