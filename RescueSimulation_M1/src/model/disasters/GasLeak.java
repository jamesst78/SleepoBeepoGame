package model.disasters;

import model.infrastructure.ResidentialBuilding;

public class GasLeak extends Disaster {

	public GasLeak(int startCycle, ResidentialBuilding target) {

		super(startCycle, target);

	}
public void cycleStep() {
	ResidentialBuilding b = (ResidentialBuilding)this.getTarget();
	b.setGasLevel(b.getGasLevel()+ 15);
	}

}
