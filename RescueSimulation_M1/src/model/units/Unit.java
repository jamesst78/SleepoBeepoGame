package model.units;

import static org.junit.Assume.assumeNoException;

import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import simulation.Simulator;

public abstract class Unit implements Simulatable, SOSResponder {

	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;
	

	public Unit(String unitID, Address location, int stepsPerCycle ,WorldListener worldListener) {

		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		

	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}
	
	public void cycleStep() {
		
			Address Targetloc = this.getTarget().getLocation();
	int distance = Targetloc.getX() + Targetloc.getY();
	
	
	
	if(this.getState() == UnitState.TREATING) {
		this.getTarget().treat();
		this.setState(UnitState.IDLE);
		
	}
	if(this.getState() == UnitState.RESPONDING) {
		distance= distance-this.getStepsPerCycle();
		this.setDistanceToTarget(distance);
		if(distance <=0) {
			this.setLocation(Targetloc);
			this.setState(UnitState.TREATING);
			
		}
	}
}
	
	public void treat() {
		this.target.getDisaster().setActive(false);
	}
	
	public void jobsDone() {
		
			this.setState(UnitState.IDLE);
			this.target =null;
		
	}
	
	public void respond(Rescuable r) {
	
		if (target!=null ) {
			if (!(this instanceof MedicalUnit && this.getState()==UnitState.TREATING))
				target.struckBy(target.getDisaster());
		}
				
		
		target = r;
		this.setState(UnitState.RESPONDING);
		int x1 = this.getLocation().getX();
		int y1 = this.getLocation().getY();
		int x2 = r.getLocation().getX();
		int y2 = r.getLocation().getY();
		int x;
		int y;
		if(x1>=x2) { 
			 x = x1-x2;
		}
		else {
			 x = x2-x1;
		}
		
		if(y1>=y2) { 
			 y = y1-y2;
		}
		else {
			y = y2-y1;
		}
		
		this.setDistanceToTarget(x+y);
			 
		
			
		}
	
	
		
		
	}


