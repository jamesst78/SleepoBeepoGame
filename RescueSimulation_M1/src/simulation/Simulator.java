package simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

	public Simulator(SOSListener emergencyService) throws Exception {

		buildings = new ArrayList<ResidentialBuilding>();
		citizens = new ArrayList<Citizen>();
		emergencyUnits = new ArrayList<Unit>();
		plannedDisasters = new ArrayList<Disaster>();
		executedDisasters = new ArrayList<Disaster>();
		this.emergencyService =emergencyService;
		this.currentCycle =0;
		world = new Address[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				world[i][j] = new Address(i, j);
			}
		}

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
		WorldListener worldListener;
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		ArrayList <WorldListener> ListOfListeners= new ArrayList<>();
			

		while (line != null) {

			String[] info = line.split(",");
			String id = info[1];
			int steps = Integer.parseInt(info[2]);
			
			
			switch (info[0]) {

			case "AMB":
				
				emergencyUnits.add(new Ambulance(id, world[0][0], steps,this));
				break;

			case "DCU":
				
				emergencyUnits.add(new DiseaseControlUnit(id, world[0][0], steps,this));
				break;

			case "EVC":
				emergencyUnits.add(new Evacuator(id, world[0][0], steps, this,Integer.parseInt(info[3])));
				break;

			case "FTK":
				emergencyUnits.add(new FireTruck(id, world[0][0], steps,this));
				break;

			case "GCU":
				emergencyUnits.add(new GasControlUnit(id, world[0][0], steps,this));
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
			buildings.add(b);
			b.setEmergencyService(emergencyService);

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
			Citizen z = new Citizen(world[x][y], id, name, age,this);
			citizens.add(z);
			z.setEmergencyService(emergencyService);

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
	public void assignAddress(Simulatable sim, int x, int y) {
			if(sim instanceof Citizen) {
				Citizen C = (Citizen)sim;
				C.setLocation(world[x][y]);
			}
			if(sim instanceof Unit) {
				Unit U = (Unit)sim;
				U.setLocation(world[x][y]);
				
			}
		
	}

	public ArrayList<Unit> getEmergencyUnits() {
		return emergencyUnits;
	}

	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}
	
	public boolean checkGameOver() {
		boolean gameOver1 = false;
		boolean gameOver2Citz = true;
		boolean gameOver3 = true;
		boolean gameOver2Builds = true;
		
		if(!(this.plannedDisasters.isEmpty()))
			gameOver1 = true;
		for(int i = 0; i < this.citizens.size(); i++) {
			if(!(citizens.get(i).getState().equals(CitizenState.DECEASED)) && citizens.get(i).getDisaster().isActive()==true) {
				gameOver2Citz = false;
				break;
				
			}
		}
		for(int j = 0; j < this.buildings.size() ; j++) {
			if(buildings.get(j).getDisaster().isActive() == true) {
				gameOver2Builds = false;
				break;
			}
		}
			
			for(int z = 0 ; z<emergencyUnits.size();z++) {
				if(!emergencyUnits.get(z).getState().equals(UnitState.IDLE)) {
					gameOver3=false;
				}
			}
			
		
		return(gameOver1 & gameOver2Citz & gameOver3 & gameOver2Builds);
	}
	
	
	public int calculateCasualities() {
		int r = 0;
		for(int u = 0; u < this.citizens.size(); u++) {
			if(citizens.get(u).getState().equals(CitizenState.DECEASED)) {
				r = r + 1;
			}
		}
		return r;
	}
	
	
	
	public void nextCycle() {
		//hn3ml planned disasters
		
		for(int i = 0 ; i<plannedDisasters.size() ; i++) {
			ResidentialBuilding x = (ResidentialBuilding)this.plannedDisasters.get(i).getTarget();
			Citizen c = (Citizen)this.plannedDisasters.get(i).getTarget();
		if(this.currentCycle == this.plannedDisasters.get(i).getStartCycle()) {
			Disaster d = (Disaster)this.plannedDisasters.get(i);
			Rescuable r = this.plannedDisasters.get(i).getTarget();
			if(this.plannedDisasters.get(i) instanceof Fire ) {
				
				x.cycleStep();
				if(x.getGasLevel() == 0) {
					x.struckBy(d);
					this.executedDisasters.add(d);
					this.plannedDisasters.remove(i);
					
				}
				if(x.getGasLevel()>0 && x.getGasLevel()<70) {
					Collapse k = new Collapse(this.currentCycle,x);
					x.struckBy(k);
					executedDisasters.add(k);
					this.plannedDisasters.remove(i);
					
				}
				if(x.getGasLevel()>=70) {
					x.setStructuralIntegrity(0);
				}
				
			}
			
			if(d instanceof GasLeak) {
				if(x.getDisaster() instanceof Fire) {
					x.cycleStep();
					Collapse k2 = new Collapse(this.currentCycle,x);
					x.struckBy(k2);
					executedDisasters.add(k2);
					plannedDisasters.remove(i);
					
				}
			}
			 
		}
		}
	}
	
	
	public void checkDisasters() {
		for(int i = 0 ; i<plannedDisasters.size() ; i++) {
			ResidentialBuilding x = (ResidentialBuilding)this.plannedDisasters.get(i).getTarget();
			Citizen c = (Citizen)this.plannedDisasters.get(i).getTarget();
		if(this.currentCycle == this.plannedDisasters.get(i).getStartCycle()) {
			Disaster d = (Disaster)this.plannedDisasters.get(i);
			Rescuable r = this.plannedDisasters.get(i).getTarget();
			if(this.plannedDisasters.get(i) instanceof Fire ) {
				
				x.cycleStep();
				if(x.getGasLevel() == 0) {
					x.struckBy(d);
					this.executedDisasters.add(d);
					this.plannedDisasters.remove(i);
					
				}
				if(x.getGasLevel()>0 && x.getGasLevel()<70) {
					Collapse k = new Collapse(this.currentCycle,x);
					x.struckBy(k);
					executedDisasters.add(k);
					this.plannedDisasters.remove(i);
					
				}
				if(x.getGasLevel()>=70) {
					x.setStructuralIntegrity(0);
				}
				
			}
			
			if(d instanceof GasLeak) {
				if(x.getDisaster() instanceof Fire) {
					x.cycleStep();
					Collapse k2 = new Collapse(this.currentCycle,x);
					x.struckBy(k2);
					executedDisasters.add(k2);
					plannedDisasters.remove(i);
					
				}
			}
			 
		}
		}
		
		for(int j = 0 ; j<buildings.size() ; j++) {
			if(this.buildings.get(j).getFireDamage() == 100) {
				Collapse f = new Collapse(this.currentCycle,this.buildings.get(j));
				this.buildings.get(j).struckBy(f);
				this.executedDisasters.add(f);
			}
		}
	}
	
	
}
