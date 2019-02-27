package model.disasters;
import model.infrastructure.*;
import simulation.*;

public class Collapse extends Disaster {
	
	public Collapse(){
		
	}
	
	public Collapse(int cycle, ResidentialBuilding target){
		
		super(cycle,target);
	}

}
