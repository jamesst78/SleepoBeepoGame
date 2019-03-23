package model.units;

import model.events.WorldListener;
import simulation.Address;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle, int maxCapacity,WorldListener worldListener) {

		super(unitID, location, stepsPerCycle, maxCapacity,worldListener);

	}

}
