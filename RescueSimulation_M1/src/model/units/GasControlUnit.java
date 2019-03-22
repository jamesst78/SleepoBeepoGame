package model.units;

import model.infrastructure.ResidentialBuilding;
import simulation.Address;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle) {

		super(unitID, location, stepsPerCycle);

	}
	
	public void treat() {
		if(this.getTarget() instanceof ResidentialBuilding) {
		this.getTarget().getDisaster().setActive(false);
		ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
		int change = x.getGasLevel()-10;
		x.setGasLevel(change);
		
		
		//shouldnt we make it that if the Gas leve reaches 0 , it should end the treatment and start changing the state to idle?
		}
	}

}
