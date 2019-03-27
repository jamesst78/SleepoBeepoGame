package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;

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
<<<<<<< HEAD
	ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
	b.setFireDamage(b.getFireDamage()+10);
=======
	

	
	
	if(this.getTarget() instanceof ResidentialBuilding){
		ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
		
	
	
	if(this instanceof Fire) {
		b.setFireDamage(b.getFireDamage()+10);
		
	}


	}

>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
}

}
