package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet.FontAttribute;

import controller.CommandCenter;
import simulation.Address;

public class GUI extends JFrame implements ActionListener  {
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
	JTextArea infoPanelText;
	JTextArea t2;
	JTextArea t3;
	JTextArea t4;
	JTextArea t5;
	CommandCenter player;
	ArrayList<JButton> buttonsOfMap = new ArrayList<>();
	ArrayList<JButton> allButtons = new ArrayList<>();
	JButton nextCycleButton;

	public GUI() throws Exception{
		this.player = new CommandCenter();
		this.setResizable(true);
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
		textPanel.add(infoPanelText, BorderLayout.SOUTH);
		infoPanelText.setMaximumSize(new Dimension(300,260));
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
		
		
		
		respondingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel respondingUnits = new JLabel("Currently Responding Units");
		respondingUnitsPanel.add(respondingUnits , BorderLayout.NORTH);
		respondingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(respondingUnitsPanel);
		
		
		treatingUnitsPanel.setPreferredSize(new Dimension(300,200));
		JLabel treatingUnits = new JLabel("Currently Treating Units");
		treatingUnitsPanel.add(treatingUnits);
		treatingUnitsPanel.setBackground(Color.LIGHT_GRAY);
		rightPanel.add(treatingUnitsPanel);
		
		//working on the array of buttons
		buttonsOfMapPanel.setPreferredSize(new Dimension(600,500));
		buttonsOfMapPanel.setLayout(new GridLayout(10,10));
		mapPanel.add(buttonsOfMapPanel , BorderLayout.NORTH);
		
		String k ="";
		for(int i = 0 ; i<10 ; i++) {
			for(int j = 0 ; j<10 ; j++) {
				k = i+ "," +j;
				JButton b1 = new JButton(k);
				b1.setName(i + " " + j);
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
			player.getEngine().nextCycle();
			infoPanelText.setText("Structral Integ: 98 \n Citizens ALive : 5 \n Gas Level : 10");
			infoPanelText.setVisible(true);
		}
		else {
			String x = temp.getName();
			int I = x.charAt(0);
			int J = x.charAt(1);
			Address  a = this.player.getEngine().getWorld()[1][1];
			for(int p =0;p<this.player.getEngine().getBuildings().size() ; p++) {
				if(this.player.getEngine().getBuildings().get(p).getLocation().equals(a)) {
					infoPanelText.setText(this.player.getEngine().getBuildings().get(p).getInfo());
					infoPanelText.setVisible(true);
				}
			}
			
		}
		
	}
	
}
