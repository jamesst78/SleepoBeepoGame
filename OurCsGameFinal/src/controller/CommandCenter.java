package controller;
import simulation.*;

import java.util.ArrayList;

import model.infrastructure.*;
import model.people.*;
import model.units.*;
public class CommandCenter {
		Simulator engine;
		ArrayList<ResidentialBuilding> visibleBuildings;
		ArrayList<Citizen> visibleCitizens;
		ArrayList<Unit> emergencyUnits;
		
		public CommandCenter() throws Exception{
			this.engine = new Simulator();
			this.visibleBuildings = new ArrayList<>();
			this.emergencyUnits = new ArrayList<>();
			this.emergencyUnits = new ArrayList<>();
		}
}		
