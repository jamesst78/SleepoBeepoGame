package model.disasters;
import model.people.*;

public class Infection extends Disaster {
	
	public Infection(){
		
	}
	
	public Infection(int cycle, Citizen target){
		
		target=(Citizen)getTarget();
		cycle=getStartCycle();
		
		
		
	}
	

}
