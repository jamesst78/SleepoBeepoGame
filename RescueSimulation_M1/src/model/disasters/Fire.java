package model.disasters;

import model.infrastructure.ResidentialBuilding;

public class Fire extends Disaster {

	public Fire(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}
public void cycleStep() {
	ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
	b.setFireDamage(b.getFireDamage()+10);
	}

public void strike() {
	this.setActive(true);
	ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
	b.setFireDamage(b.getFireDamage()+10);
}

}
