package model.disasters;
import model.people.*;

public class Injury extends Disaster{
	
	
	public Injury(){
		
	}
	
	public Injury(int cycle, Citizen target){
		cycle=getStartCycle();
		target=(Citizen)getTarget();
		
		
	}

}
