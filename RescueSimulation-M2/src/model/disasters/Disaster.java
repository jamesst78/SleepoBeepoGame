package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	private boolean inText;
	public Disaster(int startCycle, Rescuable target) {
		this.startCycle = startCycle;
		this.target = target;
		this.inText = true;
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
	public void strike() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException{
		if(this.getTarget() instanceof Citizen) {
			Citizen f = (Citizen)this.getTarget();
			if(f.getState().equals(CitizenState.DECEASED)) {
				throw new CitizenAlreadyDeadException(this, "Let the human RIP") ;
			}
			else {
				target.struckBy(this);
				active=true;
			}
		}
		if(this.getTarget() instanceof ResidentialBuilding) {
			ResidentialBuilding k = (ResidentialBuilding)this.getTarget();
			if(k.getFoundationDamage() == 100) {
				throw new BuildingAlreadyCollapsedException(this, "Building already gone") ;
			}
			else {
				target.struckBy(this);
				active=true;
			
			
		}
		}
		
	}
	public boolean isInText() {
		return inText;
	}
	public void setInText(boolean inText) {
		this.inText = inText;
	}
	public String toString() {
		String x = "";
		if(this instanceof Collapse)
			x = "Collapse";
		if(this instanceof Fire)
			x = "Fire";
		if(this instanceof GasLeak)
			x = "GasLeak";
		if(this instanceof Infection)
			x = "Infection";
		if(this instanceof Injury)
			x = "Injury";
		
		
		return x;
	}
}
