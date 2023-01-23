package Minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeFrame extends JFrame implements ActionListener
{
	
	Image image = new ImageIcon("minesweeper.PNG").getImage();
	JPanel panel = new JPanel();
	JButton button1, button2;
	BorderLayout border = new BorderLayout(50, 50);
	
	public WelcomeFrame() 
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1155,680);

		button2 = new JButton("Story");
		button2.setBackground(new Color(191, 191, 191));	
		button2.setFont(new Font("MV Boli", Font.BOLD, 45));
		button2.setBounds(10, 550, 120, 80);
		button2.addActionListener(this); 
		
		
		button1 = new JButton("Play");
		button1.setBackground(new Color(191, 191, 191));
		button1.setFont(new Font("MV Boli", Font.BOLD, 45));
		button1.setBounds(850, 550, 120, 80);
		button1.addActionListener(this);
		
		Container container = this.getContentPane();
		container.add(button1);
		container.add(button2);
		
		this.add(button1);
		this.add(button2);
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource()==button1) {
			new selectLevel();
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawImage(image, 0, 0, new JRootPane());
	}
}
