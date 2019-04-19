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
import exceptions.CitizenAlreadyDeadException;
import model.units.UnitState;
import simulation.Address;

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
	JTextArea infoPanelText;
	JTextArea logPanelText;
	JTextArea t3;
	JTextArea t4;
	JTextArea t5;
	JScrollPane scroll;
	
	JOptionPane TargetSelect;
	CommandCenter player;
	ArrayList<JButton> buttonsOfMap = new ArrayList<>();
	ArrayList<JButton> allButtons = new ArrayList<>();
	ArrayList<JButton> unitsButtons = new ArrayList<>();
	ArrayList<JButton> AvailableUnitsArrayButtons = new ArrayList<>();
	ArrayList<JButton> RespondingUnitsArrayButtons = new ArrayList<>();
	ArrayList<JButton> TreatingUnitsArrayButtons = new ArrayList<>();
	
	JButton nextCycleButton;
	Object [] inThisLocation;
	GUI gui;

	public GUI() throws Exception{
		this.player = new CommandCenter();
		this.setResizable(false);
		this.setTitle("Rescue Simulation By Yuka Engy Muhad");
		this.setSize(1200, 600);
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
		
		 
		
		
		
		
		rightPanel.setPreferredSize(d);
		rightPanel.setBackground(Color.magenta);
		
		
		leftPanel.setPreferredSize(d);
		leftPanel.setBackground(Color.MAGENTA);
		
		
		midPanel.setPreferredSize(sizing.getSize());
		midPanel.setBackground(Color.GRAY);
		mapPanel.setPreferredSize(d2);
		mapPanel.setBackground(Color.darkGray);
		midPanel.add(mapPanel , BorderLayout.NORTH);
		
		
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
		
		logPanel.setPreferredSize(new Dimension(300,200));
		logPanel.setBackground(Color.BLACK);
		JLabel log = new JLabel("Events Log");
		log.setForeground(Color.WHITE);
		logPanel.add(log, BorderLayout.NORTH);
		leftPanel.add(logPanel , BorderLayout.CENTER);
		
		
		cyclePanel.setPreferredSize(new Dimension(300,100));
		cyclePanel.setBackground(Color.BLACK);
		JLabel cycle = new JLabel("Cycles Count:");
		cycle.setForeground(Color.WHITE);
		cyclePanel.add(cycle, BorderLayout.NORTH);
		leftPanel.add(cyclePanel , BorderLayout.SOUTH);
		
		
		
		
		avaliableUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel avaliableUnits = new JLabel("Currently Available Units");
		avaliableUnitsPanel.add(avaliableUnits, BorderLayout.NORTH);
		avaliableUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(avaliableUnitsPanel);
		AvailableUnits.setPreferredSize(new Dimension(290,190));
		avaliableUnitsPanel.add(AvailableUnits, BorderLayout.SOUTH);
		
		
		
		respondingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel respondingUnits = new JLabel("Currently Responding Units");
		respondingUnitsPanel.add(respondingUnits , BorderLayout.NORTH);
		respondingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(respondingUnitsPanel);
		RespondingUnits.setPreferredSize(new Dimension(290,190));
		respondingUnitsPanel.add(RespondingUnits, BorderLayout.SOUTH);
		
		
		
		treatingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel treatingUnits = new JLabel("Currently Treating Units");
		treatingUnitsPanel.add(treatingUnits);
		treatingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(treatingUnitsPanel);
		TreatingUnits.setPreferredSize(new Dimension(290,190));
		treatingUnitsPanel.add(TreatingUnits, BorderLayout.SOUTH);
		
		
		LogPanelDescendant.setPreferredSize(new Dimension(300,190));
		logPanel.add(LogPanelDescendant , BorderLayout.SOUTH);
		LogPanelDescendant.add(logPanelText);
		logPanelText.setForeground(Color.white);
		logPanelText.setBackground(Color.BLACK);
		LogPanelDescendant.setBackground(Color.black);
		
		
		//working on the array of buttons
		buttonsOfMapPanel.setPreferredSize(new Dimension(600,500));
		buttonsOfMapPanel.setLayout(new GridLayout(10,10));
		mapPanel.add(buttonsOfMapPanel , BorderLayout.NORTH);
		mapPanel.setBackground(Color.YELLOW);
		buttonsOfMapPanel.setBackground(Color.gray);
		
		
		for(int i = 0; i < this.player.getEngine().getEmergencyUnits().size(); i++) {
			String t = this.player.getEngine().getEmergencyUnits().get(i).getUnitID();
			JButton b2 = new JButton("Unit");
			b2.setName(t);
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
				    
				  
				b1.setName(i +""+ j);
				b1.setBorder(null);
				b1.setOpaque(true);
				b1.setContentAreaFilled(false);
				b1.setBorderPainted(false);
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
		allButtons.add(nextCycleButton);
		midPanel.add(nextCycleButton, BorderLayout.SOUTH);
		infoPanelText.setSize(new Dimension(300,300));
		
		

		
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
			try {
			player.getEngine().nextCycle();
			}
			catch(CitizenAlreadyDeadException | BuildingAlreadyCollapsedException e1){
				e1.printStackTrace();
			}
			logPanelText.setText(this.player.getEngine().eventsJustHappened());
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
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.TREATING)) {
							AvailableUnits.remove(current);
							AvailableUnitsArrayButtons.remove(current);
							TreatingUnits.add(current);
							TreatingUnitsArrayButtons.add(current);
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
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.TREATING)) {
							RespondingUnits.remove(current);
							RespondingUnitsArrayButtons.remove(current);
							TreatingUnits.add(current);
							TreatingUnitsArrayButtons.add(current);
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
						}
						if(this.player.getEngine().getEmergencyUnits().get(r).getState().equals(UnitState.RESPONDING)) {
							TreatingUnits.remove(current);
							TreatingUnitsArrayButtons.remove(current);
							RespondingUnits.add(current);
							RespondingUnitsArrayButtons.add(current);
						}
					}
					
				}
			}
			
			
			
		}
		if(buttonsOfMap.contains(temp)) {
			String x = temp.getName();
			int u = Integer.parseInt(x);
			int I = u/10;
			int J = u%10;
			System.out.println(I);
			String total = "";
			Address  a = this.player.getEngine().getWorld()[I][J];
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
					infoPanelText.setText(total);
					infoPanelText.setVisible(true);
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
					infoPanelText.setText(total);
					infoPanelText.setVisible(true);
					break;
				}
			}
			
			
		}
		
	}
	
}
