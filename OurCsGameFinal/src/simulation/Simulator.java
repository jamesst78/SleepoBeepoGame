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
	Address [] world2 = new Address[10];
	
	public Simulator() {
		for()
	}
	
	
	public void loadBuildings(String path)throws IOException {
		String currentLine="";
		ArrayList<String> returnPlis = new ArrayList<String>();
		FileReader fileReader = new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while((currentLine = br.readLine()) != null) {
			//System.out.println(currentLine);
			String [] result = currentLine.split(",");
			buildings.add(new ResidentialBuilding(world[result[0],result[1]]));
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		readFile("buildings.csv");
	}

	
	
	
}

	


