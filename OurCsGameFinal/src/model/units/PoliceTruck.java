package model.units;

import simulation.Address;

public class PoliceTruck extends PoliceUnit implements Truck{
	private ReconstructionSpeed rspeed;

	public ReconstructionSpeed getRspeed() {
		return rspeed;
	}
	
	
	public PoliceTruck(String id, Address location, int stepsPerCycle, ReconstructionSpeed ReconstructionSpeed){
		super(id,location,stepsPerCycle,20);
		this.rspeed = ReconstructionSpeed;	
	}
	
	
	
	
	

}
