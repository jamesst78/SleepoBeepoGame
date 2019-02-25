package model.disasters;
import model.infrastructure.*;
import simulation.*;

public class Fire extends Disaster{
	
	public Fire(){
		
	}
	public Fire(int cycle, ResidentialBuilding target ){
		
		super(cycle,target);
	}
}
