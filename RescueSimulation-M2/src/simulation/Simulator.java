package simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSListener;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;

public class Simulator implements WorldListener {
	private int currentCycle;
	private ArrayList<ResidentialBuilding> buildings;
	private ArrayList<Citizen> citizens;
	private ArrayList<Unit> emergencyUnits;
	private ArrayList<Disaster> plannedDisasters;
	private ArrayList<Disaster> executedDisasters;
	private Address[][] world;
	private SOSListener emergencyService;

	public Simulator(SOSListener l) throws Exception {
		emergencyService = l;

		buildings = new ArrayList<ResidentialBuilding>();
		citizens = new ArrayList<Citizen>();
		emergencyUnits = new ArrayList<Unit>();
		plannedDisasters = new ArrayList<Disaster>();
		executedDisasters = new ArrayList<Disaster>();

		world = new Address[10][10];
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				world[i][j] = new Address(i, j);

		loadUnits("units.csv");
		loadBuildings("buildings.csv");
		loadCitizens("citizens.csv");
		loadDisasters("disasters.csv");
		for (int i = 0; i < buildings.size(); i++) {
			ResidentialBuilding building = buildings.get(i);
			for (int j = 0; j < citizens.size(); j++) {
				Citizen citizen = citizens.get(j);
				if (citizen.getLocation() == building.getLocation())
					building.getOccupants().add(citizen);
			}
		}
	}

	private void loadUnits(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(",");
			String id = info[1];
			int steps = Integer.parseInt(info[2]);
			switch (info[0]) {
			case "AMB": {
				Ambulance a = new Ambulance(id, world[0][0], steps, this);
				emergencyUnits.add(a);

			}
				break;
			case "DCU": {
				DiseaseControlUnit d = new DiseaseControlUnit(id, world[0][0],
						steps, this);
				emergencyUnits.add(d);
			}
				break;
			case "EVC": {
				Evacuator e = new Evacuator(id, world[0][0], steps, this,
						Integer.parseInt(info[3]));
				emergencyUnits.add(e);
			}
				break;
			case "FTK": {
				FireTruck f = new FireTruck(id, world[0][0], steps, this);
				emergencyUnits.add(f);
			}
				break;
			case "GCU": {
				GasControlUnit g = new GasControlUnit(id, world[0][0], steps,
						this);
				emergencyUnits.add(g);
			}
				break;

			}
			line = br.readLine();
		}
		br.close();

	}

	private void loadBuildings(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			ResidentialBuilding b = new ResidentialBuilding(world[x][y]);
			b.setEmergencyService(emergencyService);
			buildings.add(b);
			line = br.readLine();
		}
		br.close();
	}

	private void loadCitizens(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(",");
			int x = Integer.parseInt(info[0]);
			int y = Integer.parseInt(info[1]);
			String id = info[2];
			String name = info[3];
			int age = Integer.parseInt(info[4]);
			Citizen c = new Citizen(world[x][y], id, name, age, this);
			c.setEmergencyService(emergencyService);
			citizens.add(c);
			line = br.readLine();
		}
		br.close();
	}

	private void loadDisasters(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		while (line != null) {
			String[] info = line.split(",");
			int startCycle = Integer.parseInt(info[0]);
			ResidentialBuilding building = null;
			Citizen citizen = null;
			if (info.length == 3)
				citizen = getCitizenByID(info[2]);
			else {
				int x = Integer.parseInt(info[2]);
				int y = Integer.parseInt(info[3]);
				building = getBuildingByLocation(world[x][y]);
			}
			switch (info[1]) {
			case "INJ":
				plannedDisasters.add(new Injury(startCycle, citizen));
				break;
			case "INF":
				plannedDisasters.add(new Infection(startCycle, citizen));
				break;
			case "FIR":
				plannedDisasters.add(new Fire(startCycle, building));
				break;
			case "GLK":
				plannedDisasters.add(new GasLeak(startCycle, building));
				break;
			}
			line = br.readLine();
		}
		br.close();
	}

	private Citizen getCitizenByID(String id) {
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getNationalID().equals(id))
				return citizens.get(i);
		}
		return null;
	}

	private ResidentialBuilding getBuildingByLocation(Address location) {
		for (int i = 0; i < buildings.size(); i++) {
			if (buildings.get(i).getLocation() == location)
				return buildings.get(i);
		}
		return null;
	}

	@Override
	public void assignAddress(Simulatable s, int x, int y) {
		if (s instanceof Citizen)
			((Citizen) s).setLocation(world[x][y]);
		else
			((Unit) s).setLocation(world[x][y]);

	}

	public void setEmergencyService(SOSListener emergency) {
		this.emergencyService = emergency;
	}

	public void nextCycle() throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException {

		currentCycle++;

		for (int i = 0; i < plannedDisasters.size(); i++) {
			Disaster d = plannedDisasters.get(i);
			if (d.getStartCycle() == currentCycle) {
				plannedDisasters.remove(d);
				i--;
				if (d instanceof Fire)
					handleFire(d);
				else if (d instanceof GasLeak)
					handleGas(d);
				else {
					d.strike();
					executedDisasters.add(d);
				}
			}
		}

		for (int i = 0; i < buildings.size(); i++) {
			ResidentialBuilding b = buildings.get(i);
			if (b.getFireDamage() >= 100) {
				b.getDisaster().setActive(false);
				b.setFireDamage(0);
				Collapse c = new Collapse(currentCycle, b);
				c.strike();
				executedDisasters.add(c);
			}
		}

		for (int i = 0; i < emergencyUnits.size(); i++) {
			emergencyUnits.get(i).cycleStep();
		}

		for (int i = 0; i < executedDisasters.size(); i++) {
			Disaster d = executedDisasters.get(i);
			if (d.getStartCycle() < currentCycle && d.isActive())
				d.cycleStep();
		}

		for (int i = 0; i < buildings.size(); i++) {
			buildings.get(i).cycleStep();
		}

		for (int i = 0; i < citizens.size(); i++) {
			citizens.get(i).cycleStep();
		}


	}

	private void handleGas(Disaster d) throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException {
		ResidentialBuilding b = (ResidentialBuilding) d.getTarget();
		if (b.getFireDamage() != 0) {
			b.setFireDamage(0);
			Collapse c = new Collapse(currentCycle, b);
			c.strike();
			executedDisasters.add(c);
		} else {
			d.strike();
			executedDisasters.add(d);
		}
	}

	private void handleFire(Disaster d) throws CitizenAlreadyDeadException, BuildingAlreadyCollapsedException {
		ResidentialBuilding b = (ResidentialBuilding) d.getTarget();
		if (b.getGasLevel() == 0) {
			d.strike();
			executedDisasters.add(d);
		} else if (b.getGasLevel() < 70) {
			b.setFireDamage(0);
			Collapse c = new Collapse(currentCycle, b);
			c.strike();
			executedDisasters.add(c);
		} else
			b.setStructuralIntegrity(0);

	}

	public boolean checkGameOver() {

		if (plannedDisasters.size() != 0)
			return false;

		for (int i = 0; i < executedDisasters.size(); i++) {
			if (executedDisasters.get(i).isActive()) {

				Disaster d = executedDisasters.get(i);
				Rescuable r = d.getTarget();
				if (r instanceof Citizen) {
					Citizen c = (Citizen) r;
					if (c.getState() != CitizenState.DECEASED)
						return false;
				} else {

					ResidentialBuilding b = (ResidentialBuilding) r;
					if (b.getStructuralIntegrity() != 0)
						return false;
				}

			}

		}

		for (int i = 0; i < emergencyUnits.size(); i++) {
			if (emergencyUnits.get(i).getState() != UnitState.IDLE)
				return false;
		}

		return true;
	}

	public int calculateCasualties() {
		int count = 0;
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.get(i).getState() == CitizenState.DECEASED)
				count++;
		}
		return count;

	}

	public ArrayList<Unit> getEmergencyUnits() {

		return emergencyUnits;
	}

	public Address[][] getWorld() {
		return world;
	}

	public int getCurrentCycle() {
		return currentCycle;
	}

	public ArrayList<ResidentialBuilding> getBuildings() {
		return buildings;
	}

	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public ArrayList<Disaster> getPlannedDisasters() {
		return plannedDisasters;
	}

	public ArrayList<Disaster> getExecutedDisasters() {
		return executedDisasters;
	}
	
	
	
	public String eventsJustHappened() {
		String k = "";
		
		//form a loop over the excuted disasters
		//if the flag of this excuted disaster is true lesa , add it to the text saying it affected x rescuable
		//at the y location of the world map
		//
		
		
		
		
		for(int i = 0 ; i<this.citizens.size(); i++) {
			if(citizens.get(i).getState().equals(CitizenState.DECEASED) && this.citizens.get(i).isInText()) {
				k+= "Citizen " + citizens.get(i).getName() + "Has just died at the address " + citizens.get(i).getLocation().getX() + "," 
						+ citizens.get(i).getLocation().getY() + "\n";
				
				citizens.get(i).setInText(false);
			}
		
		}
		for(int i = 0 ; i<this.buildings.size(); i++) {
			if(buildings.get(i).getStructuralIntegrity()==0 && this.buildings.get(i).isInText()) {
				k+="Building at address " + this.buildings.get(i).getLocation().getX() + "," + 
			this.buildings.get(i).getLocation().getY() + " has been destroyed";
				buildings.get(i).setInText(false);
			}
		}
		
		for(int i = 0 ; i<this.executedDisasters.size() ; i++) {
			if(executedDisasters.get(i).isInText()) {
				
				if(executedDisasters.get(i) instanceof Collapse) {
					k+= "A Collapse has struck a building at address \n " + executedDisasters.get(i).getTarget().getLocation().getX() + ","
							+ executedDisasters.get(i).getTarget().getLocation().getY() + "\n";
					
					executedDisasters.get(i).setInText(false);
				}
				if(executedDisasters.get(i) instanceof Fire) {
					k+= "A Fire has struck a building at address \n " + executedDisasters.get(i).getTarget().getLocation().getX() + ","
							+ executedDisasters.get(i).getTarget().getLocation().getY() +"\n";
					executedDisasters.get(i).setInText(false);
				}
				
				if(executedDisasters.get(i) instanceof GasLeak) {
					k+= "A GasLeak has struck a building at address \n " + executedDisasters.get(i).getTarget().getLocation().getX() + ","
							+ executedDisasters.get(i).getTarget().getLocation().getY() + "\n";
					executedDisasters.get(i).setInText(false);
				}
				if(executedDisasters.get(i) instanceof Infection) {
					k+= "An Infection has struck a Citizen at address \n" + executedDisasters.get(i).getTarget().getLocation().getX() + ","
							+ executedDisasters.get(i).getTarget().getLocation().getY() + " \n Whose name is  \n " +((Citizen) executedDisasters.get(i).getTarget()).getName() + "\n";
					executedDisasters.get(i).setInText(false);
				}
				if(executedDisasters.get(i) instanceof Injury) {
					k+= "An Injury has struck a Citizen at address \n" + executedDisasters.get(i).getTarget().getLocation().getX() + ","
							+ executedDisasters.get(i).getTarget().getLocation().getY() + "\n Whose name is  \n " +((Citizen) executedDisasters.get(i).getTarget()).getName() + "\n";
					executedDisasters.get(i).setInText(false);
				}
				
			}
		}
		
		
		
		
		
		return k;
		
		
		
		
		
		
	}

}
