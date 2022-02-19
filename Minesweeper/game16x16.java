package Minesweeper;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class game16x16 extends JFrame implements MouseListener, ActionListener
{
	private int bomb_number;
	private int width;
	private int height;
	
	private String [][] array ;
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<String> shuffleList = new ArrayList<>();
	
	private JButton [][] buttons ;
	
	ImageIcon icon = new ImageIcon("bom.png");
	ImageIcon flag = new ImageIcon("flag.PNG");
	
	JLabel label = new JLabel("Game Over");
	
	private ArrayList< Integer> keyList = new ArrayList<>();
	private ArrayList< Integer> valueList = new ArrayList<>();
	
	JButton restartButton = new JButton("Reset");
	
	JPanel panel = new JPanel();
	JPanel dataPanel = new JPanel();
	
	JLabel dataLabel = new JLabel();
	JLabel bombLabel = new JLabel();
	
	javax.swing.Timer timer = new javax.swing.Timer(50, this);
	
	int bomb = 0 ;
	boolean game = true;

	public void createRandomArray() 
	{
		array = new String [this.height][this.width];
		
		for(int i=0; i< this.bomb_number; i++) {
			list.add(i,"Bom");
		}
		
		for(int i=bomb_number; i< (width*height); i++) {
			list.add(i,"0");
		}
		
		shuffleList = list;
		Collections.shuffle(shuffleList);
		
		for (int i=0; i < height; i++) 
		{
			for (int j=0; j < width; j++) 
			{
				array[i][j] = shuffleList.get(i*height+j);
				System.out.print(array[i][j]+"  ");
			}
			System.out.println();
		}	
	}
	
	public void checkNumber(String array[][]) 
	{
		for (int i=0; i<height; i++) 
		{
			for (int j=0; j<width; j++) 
			{
				if (array[i][j]!="Bom") {
					array[i][j] = String.valueOf(markNumber(i, j));
				}
				continue;
			}
		}
	}
	
	public int markNumber(int i, int j ) {
		int n = 0;
		if (i==0) {
			if (j==0) {
				if (array[0][1]=="Bom") {
					n++;
				}
				if (array[1][0]=="Bom"){
					n++;
				}
				if (array[1][1]=="Bom"){
					n++;
				}
				return n;
			}
			if (j == width-1) {
				if (array[0][width-2]=="Bom") {
					n++;
				}
				if (array[1][width-1]=="Bom"){
					n++;
				}
				if (array[1][width-2]=="Bom"){
					n++;
				}
				return n;
			}
			else {
				if (array[i][j-1] =="Bom")
					n++;
				if (array[i][j+1] =="Bom")
					n++;
				for( int t=-1; t<2; t++) {
					if(array[1][j+t]=="Bom") {
						n++;
					}
				}
				return n;
			}
		}
		else if (i== height-1) {
			if (j==0) {
				if (array[height-2][0]=="Bom") {
					n++;
				}
				if (array[height-2][1]=="Bom"){
					n++;
				}
				if (array[height-1][1]=="Bom"){
					n++;
				}
				return n;
			}
			if (j== width-1) {
				if (array[height-2][width-2]=="Bom") {
					n++;
				}
				if (array[height-1][width-2]=="Bom"){
					n++;
				}
				if (array[height-2][width-1]=="Bom"){
					n++;
				}
				return n;
			}
			else {
				if (array[i][j-1] =="Bom")
					n++;
				if (array[i][j+1] =="Bom")
					n++;
				for (int t=-1; t<2; t++) {
					if(array[height-2][j+t]=="Bom") {
						n++;
					}
				}
				return n;
			}
		}
		else if (j == 0) {
			if (array[i-1][j] =="Bom") {
				n++;	}
			if (array[i+1][j] =="Bom") {
				n++;	}
			for ( int t =-1; t<2; t++) {
				if(array[i+t][1]=="Bom") {
					n++;
				}
			}
			return n;
		}
		else if (j == width-1) {
			if (array[i-1][j] =="Bom")
				n++;
			if (array[i+1][j] =="Bom")
				n++;
			for ( int t =-1; t<2; t++) {
				if(array[i+t][width-2]=="Bom") {
					n++;
				}
			}
			return n;
		}
		else {
			for (int m = -1; m < 2; m++) 
			{
				for(int k= -1; k < 2; k++) 
				{
					if (array[i+m][j+k]=="Bom") 
					{
						n++;
					}
				}
			}
			return n;
		}
	}
	
	public void printArray() {
		for (int i=0; i<height; i++) {
			for (int j=0; j< width; j++) {
				System.out.print(array[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void spread(int i, int j) {
		int time = 0;
		keyList.add(i);
		valueList.add(j);
		while(keyList.size() != 0) {
			time++;
			
			// Check components around the button being verified
			if ( valueList.get(0)== 0 ) 
			{
				if(keyList.get(0) ==0) {
					for (int k = 0; k< 2; k++) 
					{
						for (int t = 0; t< 2; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
				
				else if (keyList.get(0) == height-1) {
					for (int k = 0; k< 2; k++) 
					{
						for (int t = -1; t< 1; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
				
				else {		
					for (int k = 0; k< 2; k++) 
					{
						for (int t = -1; t< 2; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
				
			}
			else if (valueList.get(0)== width-1 ) 
			{
				if(keyList.get(0) == 0 ) {
					for (int k = -1; k< 1; k++) 
					{
						for (int t = 0; t< 2; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
				else if (keyList.get(0) == height-1 ) {
					for (int k = -1; k< 1; k++) 
					{
						for (int t = -1; t< 1; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
				else {
					for (int k = -1; k< 1; k++) 
					{
						for (int t = -1; t< 2; t++) 
						{
							if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
							{
								keyList.add(keyList.get(0)+t);
								valueList.add(valueList.get(0)+k);
							}
						}
					}
				}
			}
			else if (keyList.get(0) == 0) {
				for (int k = -1; k< 2; k++) 
				{
					for (int t = 0; t< 2; t++) 
					{
						if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
						{
							keyList.add(keyList.get(0)+t);
							valueList.add(valueList.get(0)+k);
						}
					}
				}
			}
			else if (keyList.get(0) == height-1 ) {
				for (int k = -1; k< 2; k++) 
				{
					for (int t = -1; t< 1; t++) 
					{
						if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() != " ")
						{
							keyList.add(keyList.get(0)+t);
							valueList.add(valueList.get(0)+k);
						}
					}
				}
			}
			else
			{	
					for (int k = -1; k< 2; k++) 
					{
					for (int t =-1; t< 2; t++) 
					{
						if (array[keyList.get(0)+t][valueList.get(0)+k].equals("0") && buttons[keyList.get(0)+t][valueList.get(0)+k].getText() !=" ")
						{
							keyList.add(keyList.get(0)+t);
							valueList.add(valueList.get(0)+k);
						}
					}
				}
				
			}
			
			if (keyList.size() == 1 &&  time == 1 || !array[i][j].equals("0")) {
				
				valueList.remove(0);
				keyList.remove(0);
				break;
				
			}
			
			printList(keyList, valueList);
			// Open components around the button	
			for (int k = -1; k< 2; k++) 
			{
				for (int t =-1; t< 2; t++) 
				{
					try {
					if (array[keyList.get(0)+t][valueList.get(0)+k].equals("Bom"))
						continue;
					buttons[keyList.get(0)+t][valueList.get(0)+k].setText(null);
					buttons[keyList.get(0)+t][valueList.get(0)+k].setFont(new Font("Arial", Font.BOLD, 20));
					buttons[keyList.get(0)+t][valueList.get(0)+k].setBackground(Color.lightGray);
					buttons[keyList.get(0)+t][valueList.get(0)+k].setFocusable(false);			
					buttons[keyList.get(0)+t][valueList.get(0)+k].setText(array[keyList.get(0)+t][valueList.get(0)+k]);
					if(array[keyList.get(0)+t][valueList.get(0)+k].equals("0")) {
						buttons[keyList.get(0)+t][valueList.get(0)+k].setText(" ");
					}
					} catch (ArrayIndexOutOfBoundsException e) {
						System.err.println("No probem :)");
					}
				}
			}
			
		//	System.out.println("Finish Running");
			// remove component verified !
			valueList.remove(0);
			keyList.remove(0);
		}
	}
	
	public void printList(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		for (int i =0; i< list1.size(); i++) {
			System.out.println(list1.get(i)+"  "+list2.get(i));
		}
	}

	game16x16 (int bomb, int rong, int dai)
	{
		bomb_number = bomb;
		height = dai;
		width = rong;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width*50,height*50);
		this.setTitle("Minesweeper");
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		bombLabel.setBackground(new Color(25,25,25));
		bombLabel.setForeground(new Color(25, 255, 0));
		bombLabel.setFont(new Font("Ink Free", Font.BOLD, 25));
		bombLabel.setHorizontalAlignment(JLabel.LEFT);
		bombLabel.setText("Bomb: "+ String.valueOf(this.bomb_number - this.bomb));
		bombLabel.setOpaque(true);  // Hien mau backgroud
		
		
		restartButton.setBounds(width*50/2-61, 0, 120, 30);
		restartButton.setFocusable(false);
		restartButton.setHorizontalAlignment(JButton.CENTER);
		restartButton.addActionListener(this);
		
		dataPanel.setLayout(new BorderLayout());
		dataPanel.setBounds(0, 0, rong, 80);	
		
		panel.setBackground(new Color(150, 150, 150));	
		panel.setLayout(new GridLayout(dai, rong));
		
		createRandomArray();
		checkNumber(array);
		printArray();
		
		buttons = new JButton [this.height][this.width];	
	   
		for(int i=0; i< height; i++) 
		{
			for(int j=0; j< width; j++)
			{
				buttons[i][j] = new JButton();
				buttons[i][j].addMouseListener(this);
				panel.add(buttons[i][j]);		
			}
		}	
		
		dataLabel.add(restartButton);
		dataPanel.add(bombLabel);		
		
		this.add(dataPanel, BorderLayout.NORTH);
		this.add(restartButton);
		this.add(panel);	
	}

	public void gameOver() {
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {		
				if(array[i][j].equals("Bom")) {
					buttons[i][j].setText(null);
					buttons[i][j].setBackground(Color.lightGray);
					buttons[i][j].setIcon(icon);
				}
			}
		}
		timer.start();
	}
	
	public void paint(Graphics g) {
    //	super.paint(g);
		if (!game) {
			super.paintComponents(g);
			g.setColor(Color.red);
			g.setFont(new Font("Ink Free", Font.BOLD, 70));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Game Over", (this.width - metrics.stringWidth("Game Over"))/2, this.height/2);
		}
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e))
		{
			for(int i=0; i<height; i++) {
				for(int j=0; j<width; j++) {
					if(e.getSource() == buttons[i][j] && buttons[i][j].isEnabled()) {
						if (array[i][j] =="Bom") {
							buttons[i][j].setText(null);
							buttons[i][j].setBackground(Color.lightGray);
							buttons[i][j].setIcon(icon);
							buttons[i][j].setFocusable(false);
							game = false;
							gameOver();
						}
						else {
							buttons[i][j].setText(null);
							buttons[i][j].setFont(new Font("Arial", Font.BOLD, 20));
							buttons[i][j].setBackground(Color.lightGray);
							buttons[i][j].setFocusable(false);
							buttons[i][j].setText(array[i][j]);
							if(array[i][j].equals("0")) {
								buttons[i][j].setText(" ");
							}
							spread(i, j);
						}
						// check Spread !
					}
				}
			}
		}
		if(SwingUtilities.isRightMouseButton(e)) 
		{
			for(int i=0; i<height; i++)
			{
				for(int j=0; j<width; j++) 
				{
					if(e.getSource() == buttons[i][j]) 
					{
						
						if(buttons[i][j].isEnabled() && !buttons[i][j].getText().equals(" ")) 
						{
							bomb++;
							bombLabel.setText("Bomb: "+ String.valueOf(this.bomb_number - this.bomb));
							buttons[i][j].setIcon(flag);
							buttons[i][j].setEnabled(false);
							buttons[i][j].setDisabledIcon(flag);
						}
						else 
						{
							bomb--;
							bombLabel.setText("Bomb: "+ String.valueOf(this.bomb_number - this.bomb));
							buttons[i][j].setIcon(null);
							buttons[i][j].setEnabled(true);
						}
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == restartButton) {
			this.dispose();
			new game16x16(this.bomb_number, this.width, this.height);
		}
	}
}