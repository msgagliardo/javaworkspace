
public class Point implements Cloneable {

	private double x;
	private double y;
	
	public Point(double xValue, double yValue) {
		x = xValue;
		y = yValue;
	}
	public void setX(double newX) {
		x = newX;
	}
	public double getX() {
		return x;
	}
	public void setY(double newY) {
		y = newY;
	}
	public double getY() {
		return y;
	}
	public Point clone() {
		Point p = null;
		try {
			p = (Point)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return p;
	}
}
