package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Injury;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public abstract class Unit implements Simulatable, SOSResponder {
	private String unitID;
	private UnitState state;
	private Address location;
	private Rescuable target;
	private int distanceToTarget;
	private int stepsPerCycle;
	private WorldListener worldListener;

	public Unit(String unitID, Address location, int stepsPerCycle,
			WorldListener worldListener) {
		this.unitID = unitID;
		this.location = location;
		this.stepsPerCycle = stepsPerCycle;
		this.state = UnitState.IDLE;
		this.worldListener = worldListener;
	}

	public void setWorldListener(WorldListener listener) {
		this.worldListener = listener;
	}

	public WorldListener getWorldListener() {
		return worldListener;
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

	public void setDistanceToTarget(int distanceToTarget) {
		this.distanceToTarget = distanceToTarget;
	}

	@Override
	public void respond(Rescuable r) throws IncompatibleTargetException, CannotTreatException{ 

		if (target != null && state == UnitState.TREATING)
			reactivateDisaster();
		
		if(!this.canTreat(r)) {
			throw new CannotTreatException(this, r, "cannot");
		}
		else
		if(this.canTreat(r))
		if(this instanceof Ambulance) {
				if(r instanceof ResidentialBuilding) {
					throw new IncompatibleTargetException(this, r, "cannot");
					
				}
				else {
					Citizen c = (Citizen)r;
					if(!(r.getDisaster() instanceof Injury)) {
						throw new IncompatibleTargetException(this, r, "cannot");
					}
				}
		}
		if( this instanceof DiseaseControlUnit) {
			if(r instanceof ResidentialBuilding) {
				throw new IncompatibleTargetException(this, r, "cannot");
				
			}
			else {
				
			}
	}
				else {
					if(this instanceof GasControlUnit ) {
					if(r instanceof Citizen)
						throw new IncompatibleTargetException(this, r, "cannot");
					}
					if( this instanceof PoliceUnit ) {
						if(r instanceof Citizen)
							throw new IncompatibleTargetException(this, r, "cannot");
						}
					if( this instanceof FireTruck) {
						if(r instanceof Citizen)
							throw new IncompatibleTargetException(this, r, "cannot");
						}
				}	
			
		finishRespond(r);
		
		
		
	}

	public void reactivateDisaster() {
		Disaster curr = target.getDisaster();
		curr.setActive(true);
	}

	public void finishRespond(Rescuable r) {
		target = r;
		state = UnitState.RESPONDING;
		Address t = r.getLocation();
		distanceToTarget = Math.abs(t.getX() - location.getX())
				+ Math.abs(t.getY() - location.getY());

	}

	public abstract void treat();

	public void cycleStep() {
		if (state == UnitState.IDLE)
			return;
		if (distanceToTarget > 0) {
			distanceToTarget = distanceToTarget - stepsPerCycle;
			if (distanceToTarget <= 0) {
				distanceToTarget = 0;
				Address t = target.getLocation();
				worldListener.assignAddress(this, t.getX(), t.getY());
			}
		} else {
			state = UnitState.TREATING;
			treat();
		}
	}

	public void jobsDone() {
		target = null;
		state = UnitState.IDLE;

	}
	
	public boolean canTreat(Rescuable r) {
		
			if(r instanceof Citizen) {
				Citizen c = (Citizen)r;
				if(this instanceof Ambulance) {
					if(c.getBloodLoss() == 0)
						return false;			
				}
				if(this instanceof DiseaseControlUnit) {
					if(c.getToxicity() == 0) 
						return false;
				}
			}
			else {
			ResidentialBuilding b = (ResidentialBuilding)r;
			if(this instanceof GasControlUnit) {
				if(b.getGasLevel() ==0)
					return false;
			}
			if(this instanceof FireTruck) {
				if(b.getFireDamage() ==0)
					return false;
			}
			if(this instanceof Evacuator){
				if(b.getFoundationDamage() == 0)
					return false;
				}
					
			}
			return true;
		}
	
	public String getInfo() {
		String x = "";
		
		x+= "Unit's ID : " +this.unitID + "\n";
		x+= "Location : " + this.getLocation().getX() + "," + this.getLocation().getY() + "\n";
		x+="Steps per cycle : " + this.getStepsPerCycle() + "\n";
		if(this.target!=null) {
			if(target instanceof Citizen) {
				x+= "Target : Citizen with name " + ((Citizen) this.getTarget()).getName() + "\n";
			}
			else {
				x+= "Target : Building ";
			}
			
			x+= "At Location : " + target.getLocation().getX() + ","+ target.getLocation().getY() +"\n";
			
		}
		
		if(this instanceof PoliceUnit) {
			PoliceUnit y = (PoliceUnit)this;
			x += "Unit of Type : Evacuator" + "\n";
			x += "Number of Passengers : " + y.getPassengers().size() + "\n";
			x+= "Passengers' info :  \n";
			for(int i = 0 ; i<y.getPassengers().size() ; i++) {
				x+= y.getPassengers().get(i).getInfo();
			}
			
		}
		if(this instanceof Ambulance) {
			x+= "Unit type : Ambulance \n"; 
		}
		if(this instanceof DiseaseControlUnit) {
			x+= "Unit type : DiseaseControlUnit \n";
		}
		if(this instanceof FireTruck) {
			x+= "Unit type : FireTruck \n";
		}
		if(this instanceof GasControlUnit) {
			x+= "Unit type : GasControlUnit";
		}
		
		
		
			return x;
		
		
	}
}
