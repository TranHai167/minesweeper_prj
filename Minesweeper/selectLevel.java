package Minesweeper;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class selectLevel extends JFrame{
	
	static final int SCREEN_WIDTH = 420;
	static final int SCREEN_HEIGHT = 500;
	
	int HEIGHT = 9 ,WIDTH = 9, BOMB_NUMBER =40;
	
	private JButton button1 = new JButton();
	private JButton button2 = new JButton();
	private JButton button3 = new JButton();
	private JButton startButton = new JButton("Play");
	
	private SpinnerModel spinner = new SpinnerNumberModel(40, 0, 99, 1);
	private JSpinner pn = new JSpinner (spinner) ;

	private JComboBox box1, box2;
	
	private Integer select, width []= { 9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30 };
	private Integer height [] = { 9,10,11,12,13,14,15,16};
	
	private Image image = new ImageIcon("bom.png").getImage();
	
	private Font font = new Font("Arial", Font.BOLD, 15);
	public selectLevel()	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420,500);
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		startButton.setFont(font);
		button1.setText("9x9");
		button2.setText("16x16");
		button3.setText("30x16");
		
		button1.setBounds(220 , 45, 120, 40);
		button2.setBounds(220 , 95, 120, 40);
		button3.setBounds(220 , 145, 120, 40);
		startButton.setBounds(148, 400, 120, 40);
		
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);

		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HEIGHT = 9;
				WIDTH = 9;
				BOMB_NUMBER = 10;	
				System.out.println(BOMB_NUMBER +" " +WIDTH +" "+ HEIGHT);
				new game16x16(BOMB_NUMBER, WIDTH, HEIGHT);
				dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HEIGHT = 16;
				WIDTH = 16;
				BOMB_NUMBER = 40;	
				System.out.println(BOMB_NUMBER +" " +WIDTH +" "+ HEIGHT);
				new game16x16(BOMB_NUMBER, WIDTH, HEIGHT);
				dispose();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HEIGHT = 16;
				WIDTH = 30;
				BOMB_NUMBER = 99;	
				System.out.println(BOMB_NUMBER +" " +WIDTH +" "+ HEIGHT);
				new game16x16(BOMB_NUMBER, WIDTH, HEIGHT);
				dispose();
			}
		});
		
		pn.setBounds(260, 250, 60, 40);
		pn.setFont(font);
		
		pn.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				BOMB_NUMBER = (int) ((JSpinner) e.getSource()).getValue();
				
			}
		});
		
		box1 = new JComboBox<>(width);
		box2 = new JComboBox<>(height);
		
		box1.setEditable(true);
		box2.setEditable(true);
		
		box1.setFont(font);
		box2.setFont(font);
		
		box1.setBounds(220, 340, 50, 30);
		box2.setBounds(290, 340, 50, 30);
		
		box1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				select = box1.getSelectedIndex();
				System.out.println(select);
				box2.setMaximumRowCount(select+1);
				WIDTH = (int) box1.getSelectedItem();
			}
		});
		
		box2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				box2.setMaximumRowCount(select+1);
				HEIGHT = (int) box2.getSelectedItem();
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(BOMB_NUMBER +" " +WIDTH +" "+ HEIGHT);
				new game16x16(BOMB_NUMBER, WIDTH, HEIGHT);
				dispose();
			}
		});
		
		this.add(pn);
		this.add(box1);
		this.add(box2);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		this.add(startButton);
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		FontMetrics metrics = getFontMetrics(g.getFont());
		
		g.drawString("------ Default Mode ------",(SCREEN_WIDTH - metrics.stringWidth("------ Default Mode ------"))/2, 50);
		g.drawString("------ Setting Mode ------",(SCREEN_WIDTH - metrics.stringWidth("------ Setting Mode ------"))/2, 250);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Beginner", 80, 100);
		g.drawString("Intermediate", 80, 150);
		g.drawString("Expert", 80, 200);
		
		g.setFont(new Font("Arial", Font.BOLD, 22));
		g.drawString("Bomb", 80, 310);
		g.drawString("Dimension", 80, 393);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString("WIDTH", 228, 360);
		g.drawString("HEIGHT", 294, 360);
		
		g.setColor(Color.black);
		g.drawString("X", 282 , 390);
		g.drawImage(image, 225, 272, null);
	}
}
