package simulation;

import model.disasters.Disaster;

public interface Rescuable {
	public Address getLocation();
	public Disaster getDisaster();
	//public void struckBy(Disaster d);
	public void treat();
	
}
