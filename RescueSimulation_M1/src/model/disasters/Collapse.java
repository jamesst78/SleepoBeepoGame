package model.disasters;

import model.infrastructure.ResidentialBuilding;

public class Collapse extends Disaster {

	public Collapse(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}

	
	public void cycleStep() {
		ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
		b.setFoundationDamage(b.getFoundationDamage()+10);
		
		
	}
	

}
