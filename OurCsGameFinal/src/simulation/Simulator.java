package simulation;
import model.*;
import model.disasters.Disaster;
import model.infrastructure.*;
import model.people.Citizen;
import model.units.Unit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.util.*;
public class Simulator extends ReadingCSVFile{

	int currentCycle;
	ArrayList<ResidentialBuilding> buildings;
	ArrayList<Citizen> citizens;
	ArrayList<Unit> emergencyUnits;
	ArrayList<Disaster> plannedDisasters;
	ArrayList<Disaster> excutedDisasters;
	Address [][] world = new Address[10][10];
	
	
	public Simulator() throws Exception{
		for(int i = 0 ; i<10 ; i++) {
			for(int k = 0 ; k<10 ; k++) {
				this.world[i][k] = new Address(i,k);
			}
		}
		this.loadBuildings("buildings.csv");
		
	}
	
	private void loadCitizens(String path) throws IOException{
		String currentLine="";
		ArrayList<String> returnPlis = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			citizens.add(new Citizen(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])] , ) );
		}
	}
	
	
	private void loadBuildings(String path)throws IOException {
		String currentLine="";
		ArrayList<String> returnPlis = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			buildings.add(new ResidentialBuilding(world[Integer.parseInt(result[0])][Integer.parseInt(result[1])]));
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		readFile("buildings.csv");
	}

	
	
	
}

	


