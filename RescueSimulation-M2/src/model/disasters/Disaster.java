package model.disasters;

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
	public void strike() 
	{
		
		target.struckBy(this);
		active=true;
	}
	public boolean isInText() {
		return inText;
	}
	public void setInText(boolean inText) {
		this.inText = inText;
	}
}
