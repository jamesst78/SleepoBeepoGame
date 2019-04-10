package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame {
	

	public GUI(){
		this.setTitle("Rescue Simulation By Yuka Engy Muhad");
		this.setSize(1200, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel sizing = new JPanel();
		sizing.setSize(600, 600);
		Dimension d = new Dimension(300,600);
		Dimension d2 = new Dimension(600,500);
		JPanel mainPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JPanel mapPanel = new JPanel();
		mainPanel.setSize(1200,600);
		
		
		
		rightPanel.setPreferredSize(d);
		rightPanel.setBackground(Color.BLUE);
		
		leftPanel.setPreferredSize(d);
		leftPanel.setBackground(Color.GREEN);
		midPanel.setPreferredSize(sizing.getSize());
		midPanel.setBackground(Color.GRAY);
		mapPanel.setPreferredSize(d2);
		midPanel.add(mapPanel , BorderLayout.NORTH);
		
		
		this.add(rightPanel , BorderLayout.EAST);
		this.add(leftPanel , BorderLayout.WEST);
		this.add(midPanel , BorderLayout.CENTER);
		
		
		midPanel.add(new JButton("Next Cycle") , BorderLayout.SOUTH);
		
		

		
	}
	
	public static void main(String[] args) {
		GUI game = new GUI();
	}
	
}
