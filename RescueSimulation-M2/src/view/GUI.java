package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.AttributeSet.FontAttribute;

import controller.CommandCenter;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CannotTreatException;
import exceptions.CitizenAlreadyDeadException;
import exceptions.IncompatibleTargetException;
import model.disasters.Disaster;
import model.infrastructure.ResidentialBuilding;
import model.units.UnitState;
import simulation.Address;
import simulation.Rescuable;

public class GUI extends JFrame implements ActionListener , EventListener {
	JPanel rightPanel;
	JPanel leftPanel;
	JPanel mainPanel;
	JPanel midPanel;
	JPanel mapPanel;
	JPanel infoPanel;
	JPanel logPanel;
	JPanel cyclePanel;
	JPanel avaliableUnitsPanel;
	JPanel respondingUnitsPanel;
	JPanel treatingUnitsPanel;
	JPanel buttonsOfMapPanel;
	JPanel textPanel;
	JPanel LogPanelDescendant;
	JPanel AvailableUnits;
	JPanel RespondingUnits;
	JPanel TreatingUnits;
	JPanel nCycles;
	JTextArea infoPanelText;
	JTextArea logPanelText;
	JTextArea nCyclesText;
	JTextArea currentDisastersText;
	JTextArea t5;
	JScrollPane scroll;
	JScrollPane scroll2;
	
	JOptionPane TargetSelect;
	CommandCenter player;
	ArrayList<JButton> buttonsOfMap = new ArrayList<>();
	ArrayList<JButton> allButtons = new ArrayList<>();
	ArrayList<JButton> unitsButtons = new ArrayList<>();
	ArrayList<JButton> AvailableUnitsArrayButtons = new ArrayList<>();
	ArrayList<JButton> RespondingUnitsArrayButtons = new ArrayList<>();
	ArrayList<JButton> TreatingUnitsArrayButtons = new ArrayList<>();
	ArrayList<JButton> JOptionButtons = new ArrayList<>();
	Rescuable toBeRescued;
	JButton nextCycleButton;
	Object [] inThisLocation;
	GUI gui;
	Boolean targetIsSelected = false;
	String targetData = "";

	public GUI() throws Exception{
		this.player = new CommandCenter();
		this.setResizable(false);
		this.setTitle("Rescue Simulation By Yuka Engy Muhad");
		this.setSize(1200, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel sizing = new JPanel();
		sizing.setSize(600, 600);
		Dimension d = new Dimension(300,600);
		Dimension d2 = new Dimension(600,500);
		 mainPanel = new JPanel();
		 rightPanel= new JPanel();
		 leftPanel= new JPanel();
		 midPanel= new JPanel();
		 infoPanel= new JPanel();
		 logPanel= new JPanel();
		 mapPanel= new JPanel();
		 cyclePanel = new JPanel();
		 avaliableUnitsPanel = new JPanel();
		 respondingUnitsPanel = new JPanel();
		 treatingUnitsPanel = new JPanel();
		 buttonsOfMapPanel = new JPanel();
		 infoPanelText = new JTextArea();
		 textPanel = new JPanel();
		 logPanelText = new JTextArea();
		 LogPanelDescendant = new JPanel();
		 JOptionPane TargetSelect = new JOptionPane();
		 AvailableUnits = new JPanel();
		 RespondingUnits = new JPanel();
		 TreatingUnits = new JPanel();
		 this.gui = this;
		 scroll = new JScrollPane(infoPanelText);
		 nCycles = new JPanel();
		 nCyclesText = new JTextArea();
		 currentDisastersText = new JTextArea();
		 scroll2 = new JScrollPane(currentDisastersText);
		
		 
		
		
		
		
		rightPanel.setPreferredSize(d);
		rightPanel.setBackground(Color.magenta);
		
		
		leftPanel.setPreferredSize(d);
		leftPanel.setBackground(Color.MAGENTA);
		
		
		midPanel.setPreferredSize(sizing.getSize());
		midPanel.setBackground(Color.red);
		mapPanel.setPreferredSize(new Dimension(600,620));
		mapPanel.setBackground(Color.darkGray);
		midPanel.add(mapPanel , BorderLayout.NORTH);
		midPanel.add(nCycles , BorderLayout.EAST);
		nCycles.setPreferredSize(new Dimension(200,90));
		nCyclesText.setPreferredSize(new Dimension(50,50));
		nCycles.add(nCyclesText , BorderLayout.CENTER);
		nCycles.setBackground(Color.GREEN);
		nCyclesText.setText("\n  0");
		nCyclesText.setForeground(Color.ORANGE);
		nCyclesText.setBackground(Color.black);
		nCyclesText.setFont(new Font("Ariel" , Font.BOLD , 15));
		
		JLabel hamada = new JLabel("Cycles Passed");
		hamada.setVisible(true);
		hamada.setForeground(Color.BLACK);
		hamada.setBackground(Color.LIGHT_GRAY);
		nCycles.add(hamada, BorderLayout.WEST);
		
		
		
		
		
		infoPanel.setPreferredSize(new Dimension (300,300));
		textPanel.setPreferredSize(new Dimension(300,260));
		infoPanel.setBackground(Color.BLACK);
		textPanel.add(scroll);
		
		scroll.setVisible(true);
		scroll.setPreferredSize(new Dimension(290,250));
		scroll.setBackground(Color.black);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		infoPanelText.setBackground(Color.black);
		infoPanelText.setForeground(Color.WHITE);
		textPanel.setBackground(Color.black);
		JLabel info = new JLabel("Information Panel");
		JLabel decoy = new JLabel("7amada");
		decoy.setForeground(Color.BLACK);
		
		info.setForeground(Color.WHITE);
		infoPanel.add(info, BorderLayout.NORTH);
		infoPanel.add(textPanel , BorderLayout.SOUTH);
		leftPanel.add(infoPanel , BorderLayout.NORTH);
		
		logPanel.setPreferredSize(new Dimension(300,150));
		logPanel.setBackground(Color.BLACK);
		JLabel log = new JLabel("Events Log");
		log.setForeground(Color.WHITE);
		logPanel.add(log, BorderLayout.NORTH);
		leftPanel.add(logPanel , BorderLayout.CENTER);
		
		
		cyclePanel.setPreferredSize(new Dimension(300,150));
		cyclePanel.setBackground(Color.BLACK);
		JLabel cycle = new JLabel("Disasters currently happening : ");
		scroll2.setPreferredSize(new Dimension(290,70));
		scroll2.setVisible(true);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		cycle.setForeground(Color.WHITE);
		currentDisastersText.setBackground(Color.BLACK);
		currentDisastersText.setForeground(Color.WHITE);
		cyclePanel.add(cycle, BorderLayout.NORTH);
		cyclePanel.add(scroll2,BorderLayout.SOUTH);
		leftPanel.add(cyclePanel , BorderLayout.SOUTH);
		
		
		
		
		avaliableUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel avaliableUnits = new JLabel("Currently Available Units");
		avaliableUnitsPanel.add(avaliableUnits, BorderLayout.NORTH);
		avaliableUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(avaliableUnitsPanel);
		AvailableUnits.setPreferredSize(new Dimension(290,190));
		AvailableUnits.setBackground(Color.black);
		avaliableUnitsPanel.add(AvailableUnits, BorderLayout.SOUTH);
		
		
		
		respondingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel respondingUnits = new JLabel("Currently Responding Units");
		respondingUnitsPanel.add(respondingUnits , BorderLayout.NORTH);
		respondingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(respondingUnitsPanel);
		RespondingUnits.setPreferredSize(new Dimension(290,190));
		RespondingUnits.setBackground(Color.black);
		respondingUnitsPanel.add(RespondingUnits, BorderLayout.SOUTH);
		
		
		
		treatingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel treatingUnits = new JLabel("Currently Treating Units");
		treatingUnitsPanel.add(treatingUnits);
		treatingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(treatingUnitsPanel);
		TreatingUnits.setPreferredSize(new Dimension(290,190));
		TreatingUnits.setBackground(Color.black);
		treatingUnitsPanel.add(TreatingUnits, BorderLayout.SOUTH);
		
		
		LogPanelDescendant.setPreferredSize(new Dimension(300,190));
		logPanel.add(LogPanelDescendant , BorderLayout.SOUTH);
		LogPanelDescendant.add(logPanelText);
		logPanelText.setForeground(Color.white);
		logPanelText.setBackground(Color.BLACK);
		LogPanelDescendant.setBackground(Color.black);
		
		
		//working on the array of buttons
		buttonsOfMapPanel.setPreferredSize(new Dimension(600,620));
		buttonsOfMapPanel.setLayout(new GridLayout(10,10));
		mapPanel.add(buttonsOfMapPanel , BorderLayout.NORTH);
		mapPanel.setBackground(Color.YELLOW);
		buttonsOfMapPanel.setBackground(new Color(102, 51, 0));
		
		
		for(int i = 0; i < this.player.getEngine().getEmergencyUnits().size(); i++) {
			String t = this.player.getEngine().getEmergencyUnits().get(i).getUnitID();
			JButton b2 = new JButton(t);
			b2.setName(t);
			b2.addActionListener(this);
			this.allButtons.add(b2);
			this.unitsButtons.add(b2);
			b2.addMouseListener(new java.awt.event.MouseAdapter(){
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					if(unitsButtons.contains(evt.getSource())){
						for(int r = 0 ; r<unitsButtons.size() ; r++) {
							if(unitsButtons.get(r).getName().equals(((JButton) evt.getSource()).getName())) {
								for(int u = 0 ; u<gui.player.getEngine().getEmergencyUnits().size();u++) {
									if(gui.player.getEngine().getEmergencyUnits().get(u).getUnitID().equals(unitsButtons.get(r).getName())) {
										infoPanelText.setText(gui.player.getEngine().getEmergencyUnits().get(u).getInfo());
										infoPanelText.setVisible(true);
										infoPanelText.setFont(new Font("Ariel" , Font.BOLD,13));
										infoPanelText.setForeground(Color.ORANGE);
									}
								}
							}
						}
					}
					
				}
				public void mouseExited(java.awt.event.MouseEvent evt) {
					infoPanelText.setText("");
				}
			});
			AvailableUnits.add(b2);
			AvailableUnitsArrayButtons.add(b2);
			
		}
		
		
		String k ="";
		for(int i = 0 ; i<10 ; i++) {
			for(int j = 0 ; j<10 ; j++) {
				k = i+ "," +j;
				JButton b1 = new JButton();
				
				    
				   //b1.setBackground(Color.BLACK);
				    
				//ImageIcon  img2 = new ImageIcon("terrain.png"); 
				b1.setName(i +""+ j);
				b1.setBorder(null);
				b1.setOpaque(true);
				b1.setContentAreaFilled(false);
				b1.setBorderPainted(false);
				//b1.setIcon(img2);
				buttonsOfMap.add(b1);
				allButtons.add(b1);
				b1.addActionListener(this);
				buttonsOfMapPanel.add(b1);
			}
		}
		
		
		
		
		this.add(rightPanel , BorderLayout.EAST);
		this.add(leftPanel , BorderLayout.WEST);
		this.add(midPanel , BorderLayout.CENTER);
		
		
		nextCycleButton = new JButton("Next Cycle");
		nextCycleButton.addActionListener(this);
		nextCycleButton.setPreferredSize(new Dimension(150,90));
		nextCycleButton.setBackground(Color.ORANGE);
		ImageIcon  img2 = new ImageIcon("next.png");
		nextCycleButton.setIcon(img2);
		allButtons.add(nextCycleButton);
		midPanel.add(nextCycleButton, BorderLayout.SOUTH);
		infoPanelText.setSize(new Dimension(300,300));
		
		for(int q = 0 ; q<buttonsOfMap.size() ; q ++) {
			if(buttonsOfMap.get(q).getName().equals("00")) {
				ImageIcon  img = new ImageIcon("base.png");
				buttonsOfMap.get(q) .setIcon(img);
			}
		}

		
	}
	
	public static void main(String[] args) throws Exception {
		GUI game = new GUI();
		game.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		int i = 0;
		JButton temp = (JButton)e.getSource();
		for( i = 0 ; i<allButtons.size() ; i++) {
			if(allButtons.get(i).equals(temp))
				break;
		}
		temp = allButtons.get(i);
		if(temp.equals(nextCycleButton)) {
			RespondingUnits.repaint();
			AvailableUnits.repaint();
			TreatingUnits.repaint();
			try {
			player.getEngine().nextCycle();
			}
			catch(CitizenAlreadyDeadException | BuildingAlreadyCollapsedException e1){
				e1.printStackTrace();
			}
			String cycleCount = player.getEngine().getCurrentCycle() + "";
			nCyclesText.setText("   " +cycleCount );
			nCyclesText.setFont(new Font("Ariel" , Font.BOLD,16));
			logPanelText.setText(this.player.getEngine().eventsJustHappened());
			logPanelText.setFont(new Font("Ariel" , Font.BOLD,13));
			logPanelText.setForeground(Color.ORANGE);
			logPanelText.setVisible(true);
			
			for( i = 0; i<player.getVisibleBuildings().size() ; i++) {
				System.out.println(i);
				if(!player.getVisibleBuildings().get(i).isIconAlreadySet()) {
					String p = player.getVisibleBuildings().get(i).getLocation().getX()+ ""+player.getVisibleBuildings().get(i)
							.getLocation().getY();
					
					for(int j = 0 ; j<buttonsOfMap.size() ; j++) {
						if(buttonsOfMap.get(j).getName().equals(p)) {
							System.out.println("i got here");
							Random r = new Random();
							String theChosen = "";
							int x = r.nextInt(4) +1;
							switch(x) {
							case(1): theChosen = "building1.png";break;
							case(2): theChosen = "building2.png";break;
							case(3): theChosen = "building3.png";break;
							case(4): theChosen = "building4.png";break;
									//hii
							}
							
							ImageIcon  img = new ImageIcon(theChosen);
							buttonsOfMap.get(j) .setIcon(img);
							player.getVisibleBuildings().get(i).setIconAlreadySet(true);
							
						}
					}
				}
			}
			
			for(i = 0 ; i<player.getVisibleCitizens().size() ; i++) {
				String u = player.getVisibleCitizens().get(i).getLocation().getX() + "" +
						player.getVisibleCitizens().get(i).getLocation().getY();
				for(int j = 0 ; j<player.getVisibleBuildings().size() ; j++) {
					if(player.getVisibleCitizens().get(i).getLocation().equals(player.getVisibleBuildings().get(j).getLocation())) {
						player.getVisibleCitizens().get(i).setIconAlreadySet(true);
						break;
					}
					
				}
				if(!player.getVisibleCitizens().get(i).isIconAlreadySet())
				for(int k = 0 ; k<buttonsOfMap.size() ; k++) {
				  if(buttonsOfMap.get(k).getName().equals(u) ) {
					Random c = new Random();
					int w = c.nextInt(20)+1;
					String theChosenCitz = "";
					switch(w) {
					case(1): theChosenCitz = "pixelcharc1.png";break;
					case(2): theChosenCitz = "pixelcharc2.png";break;
					case(3): theChosenCitz = "pixelcharc3.png";break;
					case(4): theChosenCitz = "pixelcharc4.png";break;
					case(5): theChosenCitz = "pixelcharc5.png";break;
					case(6): theChosenCitz = "pixelcharc6.png";break;
					case(7): theChosenCitz = "pixelcharc7.png";break;
					case(8): theChosenCitz = "pixelcharc8.png";break;
					case(9): theChosenCitz = "pixelcharc9.png";break;
					case(10): theChosenCitz = "pixelcharc10.png";break;
					case(11): theChosenCitz = "pixelcharc11.png";break;
					case(12): theChosenCitz = "pixelcharc12.png";break;
					case(13): theChosenCitz = "pixelcharc13.png";break;
					case(14): theChosenCitz = "pixelcharc14.png";break;
					case(15): theChosenCitz = "pixelcharc15.png";break;
					case(16): theChosenCitz = "pixelcharc16.png";break;
					case(17): theChosenCitz = "pixelcharc17.png";break;
					case(18): theChosenCitz = "pixelcharc18.png";break;
					case(19): theChosenCitz = "pixelcharc19.png";break;
					case(20): theChosenCitz = "pixelcharc20.png";break;
					}
					ImageIcon  img2 = new ImageIcon(theChosenCitz);
					buttonsOfMap.get(k) .setIcon(img2);
					player.getVisibleCitizens().get(i).setIconAlreadySet(true);
				}
					
				}
			}
			
			
			for(int t = 0 ;t<AvailableUnitsArrayButtons.size() ; t++){
				String ID = AvailableUnitsArrayButtons.get(t).getName();
				JButton current = AvailableUnitsArrayButtons.get(t);
				for(int r = 0 ; r<this.player.getEngine().getEmergencyUnits().size();r++) {
					if(this.player.getEngine().getEmergencyUnits().get(r).getUnitID().equals(ID)) {
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.RESPONDING)) {
							AvailableUnits.remove(current);
							AvailableUnitsArrayButtons.remove(current);
							RespondingUnits.add(current);
							RespondingUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
							
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.TREATING)) {
							AvailableUnits.remove(current);
							AvailableUnitsArrayButtons.remove(current);
							TreatingUnits.add(current);
							TreatingUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
						}
					}
					
				}
			}
			
			for(int t = 0 ;t<RespondingUnitsArrayButtons.size() ; t++){
				String ID = RespondingUnitsArrayButtons.get(t).getName();
				JButton current = RespondingUnitsArrayButtons.get(t);
				for(int r = 0 ; r<this.player.getEngine().getEmergencyUnits().size();r++) {
					if(this.player.getEngine().getEmergencyUnits().get(r).getUnitID().equals(ID)) {
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.IDLE)) {
							RespondingUnits.remove(current);
							RespondingUnitsArrayButtons.remove(current);
							AvailableUnits.add(current);
							AvailableUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.TREATING)) {
							RespondingUnits.remove(current);
							RespondingUnitsArrayButtons.remove(current);
							TreatingUnits.add(current);
							TreatingUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
						}
					}
					
				}
			}
			
			for(int t = 0 ;t<TreatingUnitsArrayButtons.size() ; t++){
				String ID = TreatingUnitsArrayButtons.get(t).getName();
				JButton current = TreatingUnitsArrayButtons.get(t);
				for(int r = 0 ; r<this.player.getEngine().getEmergencyUnits().size();r++) {
					if(this.player.getEngine().getEmergencyUnits().get(r).getUnitID().equals(ID)) {
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.IDLE)) {
							TreatingUnits.remove(current);
							TreatingUnitsArrayButtons.remove(current);
							AvailableUnits.add(current);
							AvailableUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.RESPONDING)) {
							TreatingUnits.remove(current);
							TreatingUnitsArrayButtons.remove(current);
							RespondingUnits.add(current);
							RespondingUnitsArrayButtons.add(current);
							RespondingUnits.repaint();
							AvailableUnits.repaint();
							TreatingUnits.repaint();
						}
					}
					
				}
			}
			String k = "";
			for(int w= 0 ; w<this.player.getEngine().getExecutedDisasters().size();w++) {
				
				if(this.player.getEngine().getExecutedDisasters().get(w).isActive()) {
					Disaster d = this.player.getEngine().getExecutedDisasters().get(w);
					k+= "Disaster " + d.toString() + " Is Active on a Target at location " + d.getTarget().getLocation().getX() +
							"," + d.getTarget().getLocation().getY() + "\n";
				}
			}
			currentDisastersText.setText(k);
			
			
			
		}
		if(buttonsOfMap.contains(temp)) {
			String x = temp.getName();
			int u = Integer.parseInt(x);
			int I = u/10;
			int J = u%10;
			System.out.println(I);
			String total = "";
			Address  a = this.player.getEngine().getWorld()[I][J];
			
			for(int y = 0 ; y<this.player.getEngine().getEmergencyUnits().size() ; y++) {
				if(this.player.getEngine().getEmergencyUnits().get(y).getLocation().equals(a)) {
					total+= " \n Units Parked here INFO : " +  this.player.getEngine().getEmergencyUnits().get(y).getInfo();
				}
			}
			
			for(int p =0;p<this.player.getVisibleBuildings().size() ; p++) {
				if(this.player.getVisibleBuildings().get(p).getLocation().equals(a)) {
					total+=this.player.getVisibleBuildings().get(p).getInfo() + " \n Citizens INFO:  \n" ;
					for(int h = 0 ; h<this.player.getVisibleBuildings().get(p).getOccupants().size() ; h++) {
						total+= this.player.getVisibleBuildings().get(p).getOccupants().get(h).getInfo() + "\n ";
					}
					for(int y = 0 ; y<this.player.getEngine().getEmergencyUnits().size() ; y++) {
						if(this.player.getEngine().getEmergencyUnits().get(y).getLocation().equals(a)) {
							total+= "Units Parked here INFO : " +  this.player.getEngine().getEmergencyUnits().get(y).getInfo();
						}
					}
					
					System.out.println();
					break;
				}
			}
			for(int r = 0 ; r<player.getVisibleCitizens().size(); r++) {
				if(this.player.getVisibleCitizens().get(r).getLocation().equals(a)) {
					
					total+= this.player.getVisibleCitizens().get(r).getInfo();
					for(int y = 0 ; y<this.player.getEngine().getEmergencyUnits().size() ; y++) {
						if(this.player.getEngine().getEmergencyUnits().get(y).getLocation().equals(a)) {
							total+= "Units here INFO : " +  this.player.getEngine().getEmergencyUnits().get(y).getInfo();
						}
					}
					
					break;
				}
			}
			
			infoPanelText.setText(total);
			infoPanelText.setFont(new Font("Ariel" , Font.BOLD,13));
			infoPanelText.setForeground(Color.ORANGE);
			infoPanelText.setVisible(true);
			
			
			
			//here we create a JOption
		
			String name = temp.getName();
			int coordinates = Integer.parseInt(name);
			int x1 = coordinates/10;
			int y1 = coordinates%10;
			int counter = 0;
			
			for(int r = 0 ; r<player.getVisibleBuildings().size(); r ++) {
				if(player.getVisibleBuildings().get(r).getLocation().getX()==x1 && player.getVisibleBuildings().get(r).getLocation().getY() ==y1) {
						counter++;
						
				}
				
			}
			
		for(int y = 0 ;y <player.getVisibleCitizens().size() ; y++) {
			if(player.getVisibleCitizens().get(y).getLocation().getX() == x1 && player.getVisibleCitizens().get(y).getLocation().getY() == y1) {
				counter ++;
			}
		}
		System.out.println("counter is" + counter);
		Object [] array = new Object[counter];
		Rescuable [] arrayR = new Rescuable[counter];
		counter = 0 ;
		
		for(int r = 0 ; r<player.getVisibleBuildings().size(); r ++) {
			if(player.getVisibleBuildings().get(r).getLocation().getX()==x1 && player.getVisibleBuildings().get(r).getLocation().getY() ==y1) {
				array[counter] =  player.getVisibleBuildings().get(r).toString();	
				arrayR[counter++] = player.getVisibleBuildings().get(r);
					
			}
			
		}
		
	for(int y = 0 ;y <player.getVisibleCitizens().size() ; y++) {
		if(player.getVisibleCitizens().get(y).getLocation().getX() == x1 && player.getVisibleCitizens().get(y).getLocation().getY() == y1) {
			array[counter] = player.getVisibleCitizens().get(y).toString();
			arrayR[counter++] = player.getVisibleCitizens().get(y);
		}
	}
	
	
	
	int indexTaken = JOptionPane.showOptionDialog(TargetSelect, "Please choose a target", "Choose target",
			JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, array, null);
	
	if(!(indexTaken ==-1)) {
		toBeRescued =  arrayR[indexTaken];
		targetIsSelected = true;
		
	}
	
	
			
			
			
			
		}
		
//		if(JOptionButtons.contains(temp)) {
//			for(int j = 0; j <JOptionButtons.size(); j++) {
//				if(temp.equals(JOptionButtons.get(j))){
//					targetData = JOptionButtons.get(j).getName();
//					targetIsSelected = true;
//				}
//				if(targetData.contains(",")) {
//					for(int k = 0; k < player.getVisibleBuildings().size(); k++) {
//						String check = player.getVisibleBuildings().get(k).getLocation().getX() + "," + 
//								player.getVisibleBuildings().get(k).getLocation().getY();		
//						if(targetData.equals(check)) {
//							toBeRescued = player.getVisibleBuildings().get(k);
//						}
//					}
//				}
//				else {
//					for(int k = 0; k <player.getVisibleCitizens().size();k++) {
//						String check = player.getVisibleCitizens().get(k).getNationalID();
//						if(targetData.equals(check)) {
//							toBeRescued = player.getVisibleCitizens().get(k);
//						}
//					}
//				}
//				
//			}
//		}
		if(unitsButtons.contains(temp)) {
			if(targetIsSelected) {
				System.out.println("I got here ya yuka");
				for(int j = 0; j < player.getEngine().getEmergencyUnits().size();j++) {
					if(player.getEngine().getEmergencyUnits().get(j).getUnitID().equals(temp.getName())) {
						try {
							player.getEngine().getEmergencyUnits().get(j).respond(toBeRescued);
						} catch (IncompatibleTargetException | CannotTreatException e1) {
							
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						targetIsSelected = false;
					}
				}
			
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
