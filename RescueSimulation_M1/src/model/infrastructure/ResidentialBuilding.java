package model.infrastructure;

import java.util.ArrayList;
import java.util.Random;

import model.disasters.Disaster;
import model.events.SOSListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public class ResidentialBuilding implements Rescuable, Simulatable {

	private Address location;
	private int structuralIntegrity;
	private int fireDamage;
	private int gasLevel;
	private int foundationDamage;
	private ArrayList<Citizen> occupants;
	private Disaster disaster;
	private SOSListener emergencyService;

	public ResidentialBuilding(Address location) {

		this.location = location;
		this.structuralIntegrity = 100;
		occupants = new ArrayList<Citizen>();

	}

	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}

	public void setStructuralIntegrity(int structuralIntegrity) {
		if(structuralIntegrity >=100)
			this.structuralIntegrity = 100;
		if(structuralIntegrity<=0) {
			this.structuralIntegrity = 0;
		}
		else
			
		this.structuralIntegrity = structuralIntegrity;
		
		if(this.structuralIntegrity == 0) {
			for(int i = 0 ; i<this.occupants.size() ; i++) {
				this.occupants.get(i).setHp(0);
			}
		}
	}

	public int getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(int fireDamage) {
		if(fireDamage>=100)
			this.fireDamage = 100;
		if(fireDamage<=0)
			this.fireDamage = 0;
		else
			
		this.fireDamage = fireDamage;
	}

	public int getGasLevel() {
		
		return gasLevel;
	}

	public void setGasLevel(int gasLevel) {
		if(gasLevel >= 100)
			this.gasLevel = 100;
		if(gasLevel<=0)
			this.gasLevel = 0;
	else	
		this.gasLevel = gasLevel;
		if(this.gasLevel == 100) {
			for(int i = 0 ; i<this.occupants.size() ; i++) {
				this.occupants.get(i).setHp(0);
			}
		}
	}

	public int getFoundationDamage() {
		return foundationDamage;
	}

	public void setFoundationDamage(int foundationDamage) {
		if(foundationDamage >=100)
			this.structuralIntegrity = 0;
			
		this.foundationDamage = foundationDamage;
	}

	public Address getLocation() {
		return location;
	}

	public ArrayList<Citizen> getOccupants() {
		return occupants;
	}

	public Disaster getDisaster() {
		return disaster;
	}

	@Override
	public void cycleStep() {
		Random r = new Random();
		int n = r.nextInt(5)+5;
		
		if(this.foundationDamage >0) {
			this.structuralIntegrity-=n;
		}
		if(this.fireDamage>0 && this.fireDamage<30) {
			this.structuralIntegrity-=3;
		}
		if(this.fireDamage>=30 && this.fireDamage<70) {
			this.structuralIntegrity-=5;
		}
		if(this.fireDamage>=70) {
			this.structuralIntegrity-=7;
		}
	
		
	}

	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}
	
	public void treat() {
		
	}

	public void struckBy(Disaster d) {
		this.disaster = d;
		this.disaster.strike();
		emergencyService.receiveSOSCall(this);
	}
	
	public void killAll() {
		for(int i = 0; i < this.occupants.size(); i++) {
			this.occupants.get(i).setState(CitizenState.DECEASED);
		}
	}


}
