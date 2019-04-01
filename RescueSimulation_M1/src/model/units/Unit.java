package model.units;

import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import simulation.Simulator;

public abstract class Unit implements Simulatable, SOSResponder {

	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;
	private boolean firstTimeSetting;
	

	public Unit(String unitID, Address location, int stepsPerCycle ,WorldListener worldListener) {

		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;

	}

	public UnitState getState() {
		return state;
	}

	public void setState(UnitState state) {
		this.state = state;
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public String getUnitID() {
		return unitID;
	}

	public Rescuable getTarget() {
		return target;
	}

	public int getStepsPerCycle() {
		return stepsPerCycle;
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}
	
	public void cycleStep() {
	
		
		if(this instanceof PoliceUnit) {
			PoliceUnit p = (PoliceUnit)this;
								
			if(this.getState().equals(UnitState.RESPONDING)){
				ResidentialBuilding b  = (ResidentialBuilding)this.getTarget();
				int distancetobase = b.getLocation().getX() + b.getLocation().getY();
				
				
				if(this.distanceToTarget ==0) {		
					this.setState(UnitState.TREATING);
					this.treat();
					p.setToGoBackToBase(true);
					p.setToGoBackToBuilding(false);
					return;
						
				}
				
				else {
				if(this.distanceToTarget - this.getStepsPerCycle() <= 0) {	
					this.setDistanceToTarget(0);	
					p.setDistanceToBase(distancetobase);
					this.worldListener.assignAddress(p, b.getLocation().getX(), b.getLocation().getY());
					return;
					
					
				}
				else {
					this.setDistanceToTarget(this.distanceToTarget - this.getStepsPerCycle());
					return;
				}
				}
				
				
			}
			else {
				if(this.getState().equals(UnitState.TREATING)) {
					ResidentialBuilding b  = (ResidentialBuilding)this.getTarget();
					int distancetobase = b.getLocation().getX() + b.getLocation().getY();
					int i = 0;
					
					if(this.getState().equals(UnitState.TREATING) && p.isToGoBackToBuilding()==true && p.isToGoBackToBase()==false) {
						if(this.distanceToTarget ==0) {	
							this.setState(UnitState.TREATING);
							this.treat();
							p.setToGoBackToBase(true);
							p.setToGoBackToBuilding(false);
							p.setDistanceToBase(distancetobase);
							return;
											
							
						}
						else {
						if(this.distanceToTarget - this.getStepsPerCycle() <= 0) {	
							this.setDistanceToTarget(0);	
							this.worldListener.assignAddress(p, b.getLocation().getX(), b.getLocation().getY());
							
						}
						else {
							this.setDistanceToTarget(this.distanceToTarget - this.getStepsPerCycle());
							
							
						}
						}
					}
						
					
					if(this.getState().equals(UnitState.TREATING) && p.isToGoBackToBase()==true &&p.isToGoBackToBuilding()==false) {
						if(p.getDistanceToBase() == 0) {
							
							//hena han set location bel listener w nfady el nas w n4of law hnrg3 tany
							while(!p.getPassengers().isEmpty()) {
								p.getPassengers().get(i).getWorldListener().assignAddress(p.getPassengers().get(i), 0, 0);
								if(!p.getPassengers().get(i).getState().equals(CitizenState.DECEASED))
								p.getPassengers().get(i).setState(CitizenState.RESCUED);
								p.getPassengers().remove(i);
								
							}
							p.setToGoBackToBase(false);
							for(i = 0 ; i<b.getOccupants().size() ; i++) {
							if(!b.getOccupants().get(i).getState().equals(CitizenState.DECEASED)) {
								p.setToGoBackToBuilding(true);
								this.distanceToTarget = distancetobase;
								return;
							}
							
						}
							if(p.isToGoBackToBuilding()==false) {
								this.jobsDone();
								return;
							}
						}
						
						if(p.getDistanceToBase()-this.getStepsPerCycle() <=0) {
							p.setDistanceToBase(0);
							this.getWorldListener().assignAddress(this, 0, 0);
							return;
							
							
						}
						else {
							p.setDistanceToBase(p.getDistanceToBase()-this.stepsPerCycle);
							return;
						}
					}
					
				}
			}
			
			
		}
		
		

		else {
	if(this.getState().equals(UnitState.RESPONDING) || this.getState().equals(UnitState.TREATING)){
		
		if(this.distanceToTarget ==0) {
			this.worldListener.assignAddress(this, this.getTarget().getLocation().getX(), this.getTarget().getLocation().getY());		
			this.setState(UnitState.TREATING);
			this.treat();
			
			
		}
		else {
		if(this.distanceToTarget - this.getStepsPerCycle() <= 0) {	
			this.setDistanceToTarget(0);				
		}
		else {
			this.setDistanceToTarget(this.distanceToTarget - this.getStepsPerCycle());
			
		}
		}
		
		
	}
		}
	}

	
	
	
	public void treat() {
		
	}

	
	public void jobsDone() {
		
			this.setState(UnitState.IDLE);
			this.target =null;
		
	}
	
	public void respond(Rescuable r) {
	
		if (target!=null ) {
			if(this.getTarget() instanceof Citizen) {
				Citizen c = (Citizen)this.getTarget();
			if (!(this instanceof Ambulance && c.getState().equals(CitizenState.RESCUED)))
				target.struckBy(target.getDisaster());
		}
			else
				target.struckBy(target.getDisaster());
		}
				
		
		target = r;
		this.setState(UnitState.RESPONDING);
		int x1 = this.getLocation().getX();
		int y1 = this.getLocation().getY();
		int x2 = r.getLocation().getX();
		int y2 = r.getLocation().getY();
		int x;
		int y;
		if(x1>=x2) { 
			 x = x1-x2;
		}
		else {
			 x = x2-x1;
		}
		
		if(y1>=y2) { 
			 y = y1-y2;
		}
		else {
			y = y2-y1;
		}
		
		this.setDistanceToTarget(x+y);
		
	
			 
		
			
		}
	
	
		
		
	}


