package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JPanelBG extends JPanel{
	
	public JPanelBG() {
		
		super();
		
	}
@Override
protected void paintComponent(Graphics g) {

  super.paintComponent(g);
  Image bgImage = Toolkit.getDefaultToolkit().createImage("background2.png");
  
      g.drawImage(bgImage, 0, 0, null);
}

public static void main(String[] args) {
	JPanelBG b = new JPanelBG();
	JFrame x = new JFrame();
	
	x.setSize(1500,1200);
	b.setSize(1200,600);
	b.setVisible(true);
	x.add(b);
	x.setVisible(true);
}

}

