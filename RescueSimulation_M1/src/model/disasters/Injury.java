package model.disasters;

import model.people.Citizen;

public class Injury extends Disaster {

	public Injury(int startCycle, Citizen target) {

		super(startCycle, target);

	}
public void cycleStep() {
	Citizen c= (Citizen)this.getTarget();
	c.setBloodLoss(c.getBloodLoss()+10);
	}

}