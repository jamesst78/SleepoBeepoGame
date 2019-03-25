package model.units;

import model.events.WorldListener;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import simulation.Simulator;

public abstract class Unit implements Simulatable {

	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;
	

	public Unit(String unitID, Address location, int stepsPerCycle ,WorldListener toAdd) {

		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = toAdd;

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

}
