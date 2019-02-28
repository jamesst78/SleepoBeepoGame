package controller;
import simulation.*;


import java.util.ArrayList;

import model.infrastructure.*;
import model.people.*;
import model.units.*;
public class CommandCenter {
	private Simulator engine;
	private ArrayList<ResidentialBuilding> visibleBuildings;
	private ArrayList<Citizen> visibleCitizens;
	private ArrayList<Unit> emergencyUnits;
		
		public CommandCenter() throws Exception{
			this.engine = new Simulator();
			this.visibleBuildings = new ArrayList<>();
			this.visibleCitizens = new ArrayList<>();
			this.emergencyUnits = new ArrayList<>();
		}
}		
