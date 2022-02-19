package test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class myPanel extends JPanel implements ActionListener 
{
	
	static int WIDTH_SCREEN = 600;
	static int HEIGHT_SCREEN = 450;
	
	ArrayList< tester > openList = new ArrayList<>();
	ArrayList< tester > closedList = new ArrayList<>();
	
	double cost = 0;
	int unitSize = 10;
	int boundX = WIDTH_SCREEN/unitSize;
	int boundY = HEIGHT_SCREEN/unitSize;
	int array [][] = new int [boundY][boundX];
	int path [][];
	int x=29;
	int y=21;
	Timer timer;	
	boolean check = true;
	Random random;
	int loop =0;
	
	public myPanel() 
	{
		this.setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));		
		createArray();
		printArray(array);
		pathFinding();
		timer = new Timer(35, this);
		timer.start();
		//printList(closedList);
	}
	public void createArray() 
	{
		for (int i=0; i<array.length; i++) 
		{
			for (int j=0; j<array[0].length; j++) 
			{
				if ( (i==0 && j==0) || (j== x && i== y) || (j == x-1 && i==y) || (i==0 && j==1)) {
					array[i][j] = 0;
					continue;
				}
				random = new Random();	
				if(random.nextInt(100)+1 <=48) {
					array[i][j] = 1;
				}
				else {
					array[i][j] = 0;
				}
			}
		}
		/*for (int i=0; i<array.length; i++) 
		{
			for (int j=0; j<array[0].length; j++) 
			{
				array[i][j] =0;
				if ((i==4 || i==5 || i==6) && (j==4 || j==5 ||j==6 || j==7)) {
					array[i][j] =1;
				}		
			}
		}*/
	}
	@Override
	public void paint(Graphics g) 
	{		
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		
		for (int i=0; i<openList.size(); i++) {
			g2D.setPaint(Color.GREEN);
			g2D.fillRect(openList.get(i).getX()*unitSize, openList.get(i).getY()*unitSize, unitSize, unitSize);
		}
		for (int i=0; i<closedList.size(); i++) {
			g2D.setPaint(Color.RED);
			g2D.fillRect(closedList.get(i).getX()*unitSize, closedList.get(i).getY()*unitSize , unitSize, unitSize);
		}	
		
		g2D.setPaint(Color.BLUE);
		g2D.fillRect(x*unitSize,y*unitSize,unitSize, unitSize);
		
		g2D.setPaint(Color.black);
		for (int i=0; i<array.length; i++) 
		{
			for (int j=0; j<array[0].length; j++) 
			{
				if(array[i][j]==1) {
					g2D.fillRect(j*unitSize, i*unitSize, unitSize, unitSize);
				}
			}
		}
		if(!check)
		{
			g2D.setPaint(Color.BLUE);
			path();
			for (int i=0; i< path.length; i++) 
			{
				g2D.fillRect(path[i][0]*unitSize, path[i][1]*unitSize,unitSize,unitSize);
			}
		}
		g2D.setPaint(Color.black);
		for (int i = 0; i<WIDTH_SCREEN; i=i+unitSize) {
			g2D.drawLine(i, 0, i, HEIGHT_SCREEN);
			for (int j = 0; j<HEIGHT_SCREEN; j=j+unitSize) {
				g2D.drawLine(0, j,WIDTH_SCREEN, j );
			}
		}
		
	}
	
	// In mang
	public void printArray(int array[][])
	{
		for (int i=0; i<array.length; i++) 
		{
			for (int j=0; j<array[1].length; j++) 
			{
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	//In list
	public void printList( ArrayList<tester> list) 
	{
		for (int i=0; i< list.size(); i++) 
		{
			System.out.println(i +"("+ list.get(i).getX()+";"+list.get(i).getY()+")"+ "  openList.get(0) :("+openList.get(0).getX()+"; "+openList.get(0).getY()+")");
		}
		System.out.println("Vong thu:" +loop);
		System.out.println("-----------------------------------------------------");
	}
	
	// Tao cac phan tu ban dau
	public void pathFinding() 
	{
		double dis = Math.sqrt(x*x+y*y);
		tester t1 = new tester( 0 , dis, 0, 0);
		openList.add(t1);
		
		for(int i=1; i>=0; i--) {
			for(int j=1; j>=0; j--) {
				if (array[j][i] != 1 && i+j !=0 ) {
					dis = Math.sqrt((x-i)*(x-i)+(y-j)*(y-j));
					t1 = new tester(1, dis, i, j);
					if (i+j==2)
						t1 = new tester(1.4, dis,i, j);
					openList.add(t1);
				}
			}
		}	
		closedList.add(openList.get(0));
		openList.remove(0);
		
	}
	
	// Tim duong di giua cac phan tu tiep theo
	public void searching()
	{
			loop++;
			tester t1;
			double dis;
			for(int i=-1; i<2; i++) 
			{
				int a = openList.get(0).getY()+i;	
				for (int j=-1; j<2; j++) 
				{				
					int b = openList.get(0).getX()+j;
					if ( a<0 || b<0 || a== boundY || b== boundX  || array[a][b]==1 || checkVisited(b, a) == false )
					{
						continue;
					}	
					
					dis = Math.sqrt((x-b)*(x-b)+(y-a)*(y-a));
					
					if(i==0 || j==0)
					{
						double fValue = 1+openList.get(0).getgValue() + dis;
						if (checkValue(b, a, fValue)) 
						{
							t1 = new tester(1+openList.get(0).getgValue(), dis, b, a);	
							openList.add(t1);	
						}
					}
					else 
					{
						double fValue = 1.4+openList.get(0).getgValue() + dis;
						if (checkValue(b, a, fValue)) 
						{
							t1 = new tester(1.4+openList.get(0).getgValue(), dis, b, a);	
							openList.add(t1);	
						}
					}	
		
				}
			}				   
				closedList.add(openList.get(0));
				printList(closedList);
				openList.remove(0);			
			
			double min = openList.get(0).getfValue();		
			for(int i=1; i<openList.size(); i++) 
			{
					if (openList.get(i).getfValue() < min ) 
					{
						tester tempt = openList.get(i);
						openList.set(i, openList.get(0));
						openList.set(0, tempt);
						min = openList.get(0).getfValue();
					}
			}
			
	}
	
	public boolean checkValue(int posX, int posY, double fValue)
	{
		for (int i =0; i<openList.size(); i++) 
		{
			if (openList.get(i).getX()==posX && openList.get(i).getY()==posY) 
			{
				if(openList.get(i).getfValue() < fValue) 
				{
					return false;
				}
				else {
					openList.remove(i);
					i--;
				}
			}	
		}
		return true;
	}
	
	public boolean checkVisited(int posX, int posY) {
		for (int i=0; i< closedList.size(); i++) {
			if (closedList.get(i).getX()==posX && closedList.get(i).getY()==posY ) {
				return false;
			}
		}
		return true;
	}
	
	public void stop ()
	{
		for(int i=0; i<openList.size(); i++) 
		{
				if(openList.get(i).getX() ==x && openList.get(i).getY()==y) {
					check=false;				
				}
		}
		if (!check) {
			System.out.println("Cost: "+closedList.get(closedList.size()-1).getgValue());
			System.out.println("Done");
			timer.stop();
		}
	}
	
	public void path() 
	{
		int size = closedList.size();
		path = new int [size][2];
		int index =2;
		path[0][0] = x;
		path[0][1] = y;
		path[1][0] = closedList.get(size-1).getX();
		path[1][1] = closedList.get(size-1).getY();
		int a = path[1][0]; 
		int b = path[1][1];
		for (int i=0; i<size; i++) 
		{

			if ((closedList.get(i).getX()-1) <=a && a<= (closedList.get(i).getX() +1) && (closedList.get(i).getY() -1 )<=b && b<= (closedList.get(i).getY() +1)) 
			{
				size = i;
				path[index][0] = closedList.get(i).getX();
				path[index][1] = closedList.get(i).getY();
				a= path[index][0];
				b= path[index][1];
				index++;
				i=-1;		
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(check) {
			searching();
			stop();
		}	
		repaint();
	}
}
