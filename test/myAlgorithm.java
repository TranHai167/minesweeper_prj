package test;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class myAlgorithm extends JFrame{
	
	myPanel panel;
	public myAlgorithm() 
	{
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setTitle("H A* I");
		panel = new myPanel();
		
		this.add(panel);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
