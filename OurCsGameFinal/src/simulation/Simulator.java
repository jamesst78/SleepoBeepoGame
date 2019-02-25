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
public class Simulator {

	int currentCycle;
	ArrayList<ResidentialBuilding> buildings;
	ArrayList<Citizen> citizens;
	ArrayList<Unit> emergencyUnits;
	ArrayList<Disaster> plannedDisasters;
	ArrayList<Disaster> excutedDisasters;
	Address[][] world;
	
	public static void main(String[] args) {
		unparseCvsFile();
		
	}


	
	
	public static String [] unparseCvsFile(){
		String [] toBeTaken = new String[1000];
		String csvFile = 
				"C:/Users/Muhad/Downloads/buildings.csv";
		String line = "";
		String cvsSplitBy = ",";
		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while((line = br.readLine()) != null) {
				 toBeTaken = line.split(cvsSplitBy);
			}
			
			}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("hi");
		toBeTaken.toString();
		return toBeTaken;
	}
	public String toString() {
		String k = "";
		String [] L = unparseCvsFile();
		for(int i = 0 ; i<L.length ; i++) {
			k+= L[i];
		}
		return k;
	}
}


