package model.units;

import model.events.WorldListener;
import simulation.Address;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {

		super(unitID, location, stepsPerCycle, worldListener);

	}

}
