package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Disaster implements Simulatable {

	private int startCycle;
	private Rescuable target;
	private boolean active;

	public Disaster(int startCycle, Rescuable target) {

		this.startCycle = startCycle;
		this.target = target;

	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getStartCycle() {
		return startCycle;
	}

	public Rescuable getTarget() {
		return target;
	}
	
	public abstract void cycleStep();
		
	
	public void strike() {
		this.setActive(true);
		
		if(this.getTarget() instanceof Citizen) {
		Citizen c = (Citizen)this.getTarget();
			c.setState(CitizenState.IN_TROUBLE);
			
			if(this instanceof Infection) {
				c.setToxicity(c.getToxicity()+25);
		
	}
			//if(this instanceof Injury) {
			//c.setBloodLoss(c.getBloodLoss()+30);
		
	//}
		}
		
		
		if(this.getTarget() instanceof ResidentialBuilding){
			ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
			
		
		if(this instanceof Collapse) {
			b.setFoundationDamage(b.getFoundationDamage()+10);
			
			
		}
		//if(this instanceof Fire) {
			//b.setFireDamage(b.getFireDamage()+10);
			
		//}
		if(this instanceof GasLeak) {
			b.setGasLevel(b.getGasLevel()+10);
	
}

		}

	}
	

}
