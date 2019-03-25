package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;

public class Infection extends Disaster {

	public Infection(int startCycle, Citizen target) {

		super(startCycle, target);

	}
public void cycleStep() {
	Citizen c= (Citizen)this.getTarget();	
		c.setToxicity(c.getToxicity()+15);
	}

}
