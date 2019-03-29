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
	

	public PoliceUnit(String unitID, Address location, int stepsPerCycle,WorldListener worldListener, int maxCapacity ) {

		super(unitID, location, stepsPerCycle, worldListener);
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
	
	
	
	
	public void wadeehomElBase() {
		if(this.getDistanceToBase() ==0) {
			this.getWorldListener().assignAddress(this, 0, 0);
			//habda2 afadeehom
		}
	}



	
	public void treat() {
		int i = 0;
		if(this.getTarget() instanceof ResidentialBuilding) {
			
			ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
			
			while((!x.getOccupants().isEmpty()) && this.getPassengers().size()<this.getMaxCapacity() ) {
						this.getPassengers().add(x.getOccupants().get(i));  //7ateeto fl 3rbya
						x.getOccupants().remove(i); 
						//removed mn el occupants
						
					
					
				}
			}
			
					
				
			}
	

	public ArrayList<Citizen> getPassengers() {
		return passengers;
	}

	public boolean isToGoBackToBuilding() {
		return ToGoBackToBuilding;
	}

	public void setToGoBackToBuilding(boolean toGoBackToBuilding) {
		ToGoBackToBuilding = toGoBackToBuilding;
	}

	public boolean isToGoBackToBase() {
		return ToGoBackToBase;
	}

	public void setToGoBackToBase(boolean toGoBackToBase) {
		ToGoBackToBase = toGoBackToBase;
	}

}
