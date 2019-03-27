package model.people;

import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;
import model.disasters.Disaster;
import model.events.SOSListener;
import model.events.WorldListener;

public class Citizen implements Rescuable, Simulatable {

	private CitizenState state;
	private Disaster disaster;
	private String name;
	private String nationalID;
	private int age;
	private int hp;
	private int bloodLoss;
	private int toxicity;
	private Address location;
	private SOSListener emergencyService;
	private WorldListener worldListener;
	public Citizen(Address location, String nationalID, String name, int age, WorldListener wordListener) {

		this.name = name;
		this.nationalID = nationalID;
		this.age = age;
		this.location = location;
		this.state = CitizenState.SAFE;
		this.hp = 100;

	}

	public CitizenState getState() {
		return state;
	}

	public void setState(CitizenState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		
		if(hp >100)
			this.hp = 100;
		else
		if(hp<0)
			this.hp = 0;
		else
			this.hp = hp;
		
		if(this.hp == 0)
			this.setState(CitizenState.DECEASED);
	}

	public int getBloodLoss() {
		return bloodLoss;
	}

	public void setBloodLoss(int bloodLoss) {
		if(bloodLoss>100) {
			this.bloodLoss = 100;
		}
		else
		if(bloodLoss<0)
			this.bloodLoss = 0;
		else
		this.bloodLoss = bloodLoss;
		
		if(this.bloodLoss ==100) {
			this.setHp(0);
			
		}
	}

	public int getToxicity() {
		return toxicity;
	}

	public void setToxicity(int toxicity) {
		if(this.toxicity>100)
			this.toxicity = 100;
		else
		if(this.toxicity <0)
			this.toxicity = 0;
		else
			this.toxicity = toxicity;
		if(this.toxicity==100)
			this.setHp(0);
	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public Disaster getDisaster() {
		return disaster;
	}

	public String getNationalID() {
		return nationalID;
	}

	@Override
	public void cycleStep() {
		if((this.bloodLoss <30 && bloodLoss>0) || this.toxicity <30 && this.toxicity>0 ) {
			this.hp = hp-5;
		}
		if((this.bloodLoss >=30 && bloodLoss<70) || this.toxicity >=30 && this.toxicity<70 ) {
			this.hp = hp-10;
		}
		if((this.bloodLoss >=70) || this.toxicity >=70 ) {
			this.hp = hp-15;
		}
		
		
	}

	public WorldListener getWorldListener() {
		return worldListener;
	}

	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
	public void struckBy(Disaster d) {
		this.disaster = d;
		this.disaster.setActive(true);
		if(emergencyService != null)
		emergencyService.receiveSOSCall(this);
	}

	@Override
	public void treat() {
<<<<<<< HEAD
	
	}
	
=======
		// TODO Auto-generated method stub
		
	}
>>>>>>> branch 'master' of https://github.com/jamesst78/SleepoBeepoGame.git
	
	
}
