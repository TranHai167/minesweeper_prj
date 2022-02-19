package Minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class WelcomePanel extends JPanel implements ActionListener{
	
	static int WIDTH = 1155;
	static int HEIGHT = 662;
	
	Image image;
	
	JButton button1;
	JButton button2;
	JButton button3;
	
	JPanel panel = new JPanel();

	public WelcomePanel() {
		
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		panel.setBounds(0, 600, 1155, 70);
		panel.setBackground(Color.gray);
		
		
		image = new ImageIcon("minesweeper.PNG").getImage();
		button2 = new JButton("Story");
		button2.setSize(268, 76);
		button2.setBackground(new Color(191, 191, 191));	
		button2.setFont(new Font("MV Boli", Font.BOLD, 45));
		button2.addActionListener(this);
		
		
		button1 = new JButton("Play");
		button1.setSize(268, 76);
		button1.setBackground(new Color(191, 191, 191));
		button1.setFont(new Font("MV Boli", Font.BOLD, 45));
		button1.addActionListener(this);
			
		
		this.add(panel, BorderLayout.SOUTH);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(image , 0, 0, null);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==button1) {
			new selectLevel();
		}
	}
}

