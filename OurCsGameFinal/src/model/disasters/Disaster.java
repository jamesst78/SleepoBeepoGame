package model.disasters;
import simulation.*;
abstract public class Disaster {
	private int startCycle;
	private Rescuable target;
	private boolean active;
	
	
	public Disaster(){
		
	}
	public Disaster(int startCycle ,Rescuable target ) {
		this.startCycle = startCycle;
		this.target = target;
		this.active = false;
	}

	public int getStartCycle() {
		return startCycle;
	}

	public Rescuable getTarget() {
		return target;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
	
}
