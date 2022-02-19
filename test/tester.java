package test;

public class tester {
	
	double gValue;
	double hValue;
	int X,Y;

	public tester(double g, double h, int X, int Y) {
		this.gValue = g;
		this.hValue = h;
		this.X = X;
		this.Y = Y;
	}
	
	public double getgValue() {
		return gValue;
	}

	public double gethValue() {
		return hValue;
	}
	public double getfValue() {
		double fValue = gValue+hValue;
		return fValue;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}	
}
