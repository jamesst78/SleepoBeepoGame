package model.people;

import simulation.*;
import model.disasters.*;


public class Citizen implements Simulatable , Rescuable  {

	private CitizenState state ;
	private Disaster disaster;
	private Address location ;
	private String nationalID;
	private String name;
	private int age;
	private int hp;
	private int bloodLoss;
	private int toxicity;
	
	
	public Citizen(Address location , String nationalID , String name , int age) {
	this.state = CitizenState.SAFE ;
	this.hp = 100;
	this.bloodLoss = 0;
	this.toxicity = 0;
	}
	
	
	
}
