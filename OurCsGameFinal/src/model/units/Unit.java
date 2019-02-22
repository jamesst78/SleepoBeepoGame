package model.units;
import simulation.*;
abstract public class Unit {
		String unitID;
		UnitState state;
		Address location;
		Rescuable target;
		int distanceToTarget;
		int stepsPerCycle;
		
		
		
	public Unit(String id , Address location , int stepsPerCycle) {
		this.unitID = id;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
	}
	public Unit() {
		
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



	public int getStepsPerCycle() {
		return stepsPerCycle;
	}



	public void setTarget(Rescuable target) {
		this.target = target;
	}
}
