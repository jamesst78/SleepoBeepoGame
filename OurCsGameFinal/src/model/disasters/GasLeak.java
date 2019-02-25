package model.disasters;
import model.infrastructure.*;
import simulation.*;

public class GasLeak extends Disaster{

	public GasLeak(){
		
	}
	
	public GasLeak(int cycle, ResidentialBuilding target){
		super(cycle,target);
		cycle=getStartCycle();
		target=(ResidentialBuilding)getTarget();
		
	}
	
	
}
