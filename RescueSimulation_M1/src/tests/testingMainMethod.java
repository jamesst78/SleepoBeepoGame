package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.disasters.Infection;
import model.disasters.Injury;
import model.events.SOSListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import model.units.Ambulance;
import model.units.DiseaseControlUnit;
import model.units.Evacuator;
import model.units.FireTruck;
import model.units.GasControlUnit;
import model.units.Unit;
import model.units.UnitState;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulator;

public class testingMainMethod {
	private boolean hascalledBuilding;
	private boolean hascalledCitizen;
	private boolean hascalledPlannedDisaster;
	private boolean hascalledExecutedDisaster;
	private boolean hascalledUnit;
	private int callcount;
	Address testAddress1 = new Address(0, 0);
	Address testAddress2 = new Address(1, 1);
	Address testAddress3 = new Address(2, 3);
	Address testAddress4 = new Address(4, 4);

	String id1 = "1";
	String id2 = "2";
	String id3 = "3";
	String id4 = "4";

	String name1 = "test1";
	String name2 = "test2";
	String name3 = "test3";
	String name4 = "test4";

	int age1 = 24;
	int age2 = 25;
	int age3 = 26;
	int age4 = 27;

	Citizen testCitizen1 = new Citizen(testAddress1, id1, name1, age1, null);
	Citizen testCitizen2 = new Citizen(testAddress2, id2, name2, age2, null);
	Citizen testCitizen3 = new Citizen(testAddress3, id3, name3, age3, null);
	Citizen testCitizen4 = new Citizen(testAddress4, id4, name4, age4, null);

	ResidentialBuilding testBuilding1 = new ResidentialBuilding(testAddress1);
	ResidentialBuilding testBuilding2 = new ResidentialBuilding(testAddress2);
	ResidentialBuilding testBuilding3 = new ResidentialBuilding(testAddress3);
	ResidentialBuilding testBuilding4 = new ResidentialBuilding(testAddress4);

	Ambulance testAmbulance = new Ambulance(id1, testAddress1, 1, null);
	Evacuator testEvacutor = new Evacuator(id2, testAddress1, 1, null, 5);
	FireTruck testFireTruck = new FireTruck(id3, testAddress1, 1, null);
	DiseaseControlUnit testDiseaseContorlUnit = new DiseaseControlUnit(id4,
			testAddress1, 1, null);
	GasControlUnit testGasContorlUnit = new GasControlUnit("5", testAddress1,
			1, null);

	Collapse testCollapse = new Collapse(1, testBuilding1);
	Fire testFire = new Fire(3, testBuilding1);
	GasLeak testGasLeak = new GasLeak(3, testBuilding1);
	Infection testiInfection = new Infection(1, testCitizen1);
	Injury testinjInjury = new Injury(2, testCitizen2);

	static final String buildingPath = "model.infrastructure.ResidentialBuilding";
	static final String disasterPath = "model.disasters.Disaster";
	static final String sosListenerPath = "model.events.SOSListener";

	static String simulatorPath = "simulation.Simulator";
	static String addressPath = "simulation.Address";
	static String rescuablePath = "simulation.Rescuable";
	static String simulatablePath = "simulation.Simulatable";
	static String residentialBuildingPath = "model.infrastructure.ResidentialBuilding";
	static String citizenStatePath = "model.people.CitizenState";
	static String unitStatePath = "model.units.UnitState";
	static String citizenPath = "model.people.Citizen";
	static String unitPath = "model.units.Unit";
	static String policeUnitPath = "model.units.PoliceUnit";
	static String fireUnitPath = "model.units.FireUnit";
	static String medicalUnitPath = "model.units.MedicalUnit";
	static String evacuatorPath = "model.units.Evacuator";
	static String fireTruckPath = "model.units.FireTruck";
	static String gasControlUnitPath = "model.units.GasControlUnit";
	static String ambulancePath = "model.units.Ambulance";
	static String diseaseControlUnitPath = "model.units.DiseaseControlUnit";
	static String collapsePath = "model.disasters.Collapse";
	static String firePath = "model.disasters.Fire";
	static String gasLeakPath = "model.disasters.GasLeak";
	static String infectionPath = "model.disasters.Infection";
	static String injuryPath = "model.disasters.Injury";
	static String commandCenterPath = "controller.CommandCenter";
	static String worldListenerPath = "model.events.WorldListener";
	static String sosResponderPath = "model.events.SOSResponder";

	public static boolean treatCalled = false;
	public static boolean healCalled = false;
	private static boolean struckByCalled = false;

	private boolean hascalledInjuryDisaster;
	private boolean hascalledInfectionDisaster;
	private boolean hascalledCollapseDisaster;
	private boolean hascalledGasDisaster;
	private boolean hascalledFireDisaster;

	Infection testInfection = new Infection(1, testCitizen1);
	Injury testInjury = new Injury(2, testCitizen2);

	private static boolean called = false;
	private static Rescuable target = null;

	HashMap<String, Integer> count;
	SOSListener sos = new SOSListener() {

		@Override
		public void receiveSOSCall(Rescuable r) {

		}
	};
public static void main(String[] args) throws Exception {
	testingMainMethod k = new testingMainMethod();
	k.testEvacuatorTreatLogicCycle0CitizensLocations();
	
}

public void testEvacuatorTreatLogicCycle0CitizensLocations()
		throws Exception {
	Simulator s = new Simulator(sos);
	Address ad = getAddressFromWorld(s, 3, 9);

	Evacuator u = new Evacuator("evacuator1", getAddressFromWorld(s, 3, 4),
			5, null, 2);
	u.setWorldListener(s);

	ResidentialBuilding b = new ResidentialBuilding(ad);
	b.setFoundationDamage(10);
	ArrayList<Citizen> citizensToBeTested = new ArrayList<Citizen>();
	for (int i = 1; i <= 5; i++) {
		Citizen c = new Citizen(ad, i + "", "citizen" + i, 15 + i, null);
		c.setWorldListener(s);
		citizensToBeTested.add(c);
		b.getOccupants().add(c);
	}
	Disaster d = new Collapse(3, b);
	dshelper(d);

	unitRespond(u, b, 5);
	for (int i = 0; i < 1; i++) {
		u.cycleStep();
		b.setStructuralIntegrity(b.getStructuralIntegrity() - 10);
	}

	assertEquals("first Citizen should be in the location " + ad.getX()
			+ " " + ad.getY() + " but was "
			+ citizensToBeTested.get(0).getLocation().getX() + " "
			+ citizensToBeTested.get(0).getLocation().getY(), ad,
			citizensToBeTested.get(0).getLocation());

	assertEquals("second Citizen should be in the location " + ad.getX()
			+ " " + ad.getY() + " but was "
			+ citizensToBeTested.get(1).getLocation().getX() + " "
			+ citizensToBeTested.get(1).getLocation().getY(), ad,
			citizensToBeTested.get(1).getLocation());

	assertEquals("third Citizen should be in the location " + ad.getX()
			+ " " + ad.getY() + " but was "
			+ citizensToBeTested.get(2).getLocation().getX() + " "
			+ citizensToBeTested.get(2).getLocation().getY(), ad,
			citizensToBeTested.get(2).getLocation());

	assertEquals("fourth Citizen should be in the location " + ad.getX()
			+ " " + ad.getY() + " but was "
			+ citizensToBeTested.get(3).getLocation().getX() + " "
			+ citizensToBeTested.get(3).getLocation().getY(), ad,
			citizensToBeTested.get(3).getLocation());

	assertEquals("fifth Citizen should be in the location " + ad.getX()
			+ " " + ad.getY() + " but was "
			+ citizensToBeTested.get(4).getLocation().getX() + " "
			+ citizensToBeTested.get(4).getLocation().getY(), ad,
			citizensToBeTested.get(4).getLocation());

}


private static Address getAddressFromWorld(Simulator s, int x, int y)
		throws IllegalArgumentException, IllegalAccessException {

	Field f = null;
	Class curr = s.getClass();
	while (f == null) {
		if (curr == Object.class)
			fail("Class " + s.getClass().getSimpleName()
					+ " should have the instance variable \"" + "world"
					+ "\".");
		try {
			f = curr.getDeclaredField("world");
		} catch (NoSuchFieldException e) {
			curr = curr.getSuperclass();
		}
	}
	f.setAccessible(true);
	Address myWorld[][] = (Address[][]) f.get(s);
	return myWorld[x][y];
}


private static void dshelper(Disaster d) throws IllegalArgumentException,
IllegalAccessException {
Rescuable target = d.getTarget();
if (target instanceof Citizen) {
Citizen c = (Citizen) target;
Field f = null;
Class curr = c.getClass();
while (f == null) {
	if (curr == Object.class)
		fail("Class " + c.getClass().getSimpleName()
				+ " should have the instance variable \""
				+ "disaster" + "\".");
	try {
		f = curr.getDeclaredField("disaster");
	} catch (NoSuchFieldException e) {
		curr = curr.getSuperclass();
	}
}
f.setAccessible(true);
f.set(c, d);
} else {
ResidentialBuilding b = (ResidentialBuilding) target;
Field f = null;
Class curr = b.getClass();
while (f == null) {
	if (curr == Object.class)
		fail("Class " + b.getClass().getSimpleName()
				+ " should have the instance variable \""
				+ "disaster" + "\".");
	try {
		f = curr.getDeclaredField("disaster");
	} catch (NoSuchFieldException e) {
		curr = curr.getSuperclass();
	}
}
f.setAccessible(true);
f.set(b, d);
}
d.setActive(true);
}

private static void unitRespond(Unit u, Rescuable r, int dist)
		throws IllegalArgumentException, IllegalAccessException {
	if (r != null && u.getState() == UnitState.TREATING) {

		Disaster curr = r.getDisaster();
		curr.setActive(true);
	}

	Field targetField = null;
	Class curr0 = u.getClass();
	while (targetField == null) {
		if (curr0 == Object.class)
			fail("Class " + u.getClass().getSimpleName()
					+ " should have the instance variable \"" + "disaster"
					+ "\".");
		try {
			targetField = curr0.getDeclaredField("target");
		} catch (NoSuchFieldException e) {
			curr0 = curr0.getSuperclass();
		}
	}
	targetField.setAccessible(true);
	targetField.set(u, r);

	u.setState(UnitState.RESPONDING);
	int distanceToTarget = dist;

	Field f = null;
	Class curr = u.getClass();
	while (f == null) {
		if (curr == Object.class)
			fail("Class " + u.getClass().getSimpleName()
					+ " should have the instance variable \"" + "disaster"
					+ "\".");
		try {
			f = curr.getDeclaredField("distanceToTarget");
		} catch (NoSuchFieldException e) {
			curr = curr.getSuperclass();
		}
	}
	f.setAccessible(true);
	f.set(u, distanceToTarget);

}

	
	
	
	

	
}

