package simulation;
import model.*;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.infrastructure.*;
import model.people.Citizen;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.util.*;
public class Simulator extends ReadingCSVFile{

	private int currentCycle;
	private ArrayList<ResidentialBuilding> buildings;
	private ArrayList<Citizen> citizens;
	private	ArrayList<Unit> emergencyUnits;
	private	ArrayList<Disaster> plannedDisasters;
	private ArrayList<Disaster> executedDisasters;
	private Address [][] world = new Address[10][10];
	
	
	public Simulator() throws Exception{
		for(int i = 0 ; i<10 ; i++) {
			for(int k = 0 ; k<10 ; k++) {
				this.world[i][k] = new Address(i,k);
			}
			 buildings = new ArrayList<>();
			 citizens = new ArrayList<>();
			 emergencyUnits = new ArrayList<>(); 
			 plannedDisasters = new ArrayList<>();
			 executedDisasters = new ArrayList<>();
			
		}
		this.loadBuildings("buildings.csv");
		this.loadCitizens("citizens.csv");
		this.loadDisasters("disasters.csv");
		this.loadUnits("units.csv");
		
	}
	//hiiiiui
	private void loadCitizens(String path) throws IOException{
		ResidentialBuilding y = null;
		String currentLine="";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			Citizen k = new Citizen(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])] , result[2] ,result[3] , Integer.parseInt(result[4]));
			citizens.add(k);
			for(int i = 0 ; i<buildings.size() ; i++) {
				y = buildings.get(i);
				if(y.getLocation().getX() == Integer.parseInt(result[0]) &&
						y.getLocation().getY() == Integer.parseInt(result[1])) {
					break;
				}
					
			}
			y.getOccupants().add(k);
			
		}
	}
	
	
	private void loadBuildings(String path)throws IOException {
		String currentLine="";
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			buildings.add(new ResidentialBuilding(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])]));
		}}
		
	private void loadUnits(String path) throws IOException{
		String currentLine="";
		ArrayList<String> toreturn = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			String[] result = currentLine.split(",");
			switch(result[0]) {
			case("AMB"): emergencyUnits.add(new Ambulance(result[1],world[0][0], Integer.parseInt(result[2])));break;
			case("DCU"): emergencyUnits.add(new DiseaseControlUnit(result[1],world[0][0], Integer.parseInt(result[2])));break;
			case("EVC"): emergencyUnits.add(new Evacuator(result[1],world[0][0], Integer.parseInt(result[2]), Integer.parseInt(result[3])));break;
			case("FTK"): emergencyUnits.add(new FireTruck(result[1],world[0][0], Integer.parseInt(result[2])));break;
			case("GCU"): emergencyUnits.add(new GasControlUnit(result[1],world[0][0], Integer.parseInt(result[2])));break;
			}
			
		} }
	
	
	
			
	
	
	public static void main(String[] args) throws IOException {
		readFile("buildings.csv");
		
	}
	
	
	public void loadDisasters(String path) throws Exception{
		String z = "";
		ResidentialBuilding y = null;
		String currentLine="";
		ArrayList<String> returnPlis = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			 
				
				switch(result[1]) {
								
			case "INJ": plannedDisasters.add(new Injury(Integer.parseInt(result[0]) ,this.getTheCitizen(result[2]) )); break;
			case "INF": plannedDisasters.add(new Infection(Integer.parseInt(result[0]) , this.getTheCitizen(result[2])));break;
				}
			
			
				
				switch(result[1]) {
				case "FIR" : plannedDisasters.add(new Fire(Integer.parseInt(result[0]) , this.getTheBuilding(result[2], result[3])));break;
				case "GLK" : plannedDisasters.add(new GasLeak(Integer.parseInt(result[0]) , this.getTheBuilding(result[2], result[3])));break;
				}
			
			
			
			
			
	}
}
	
	
	
	public  Citizen getTheCitizen(String z) {
		Citizen x = null ;
		for(int i = 0 ; i<citizens.size();i++) {
			 x = citizens.get(i);
				if(x.getNationalID().equals(z))
					break;
			}return x;
	}
	
	public ResidentialBuilding getTheBuilding(String z2, String z3) {
		ResidentialBuilding y = null;
		for(int i = 0 ; i<buildings.size() ; i++) {
			y = buildings.get(i);
			if(y.getLocation().getX() == Integer.parseInt(z2) &&
					y.getLocation().getY() == Integer.parseInt(z3)) {
				break;
			}
				
		}
		return y;
		
	}

	

	


	
	
}

	


