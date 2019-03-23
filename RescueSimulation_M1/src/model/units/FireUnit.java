package model.units;

import model.infrastructure.ResidentialBuilding;
import simulation.Address;

public abstract class FireUnit extends Unit {

	public FireUnit(String unitID, Address location, int stepsPerCycle) {

		super(unitID, location, stepsPerCycle);

	}
	
	public void treat() {
		if(this.getTarget() instanceof ResidentialBuilding) {
			this.getTarget().getDisaster().setActive(false);
			ResidentialBuilding x = (ResidentialBuilding)this.getTarget();
			int change = x.getFireDamage()-10;
			x.setFireDamage(change);
			//shouldnt we make it that if the fire level reaches 0 , it should end the treatment and start changing the state to idle?
		}
	}
}
