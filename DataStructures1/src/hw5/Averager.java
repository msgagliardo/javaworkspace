package hw5;

public class Averager {
	private double airSum;
	private double landSum;
	private int numDeparted;
	private int numLanded;
	private int numCrashed;
	
	public Averager() {
		airSum = 0;
		landSum = 0;
		numDeparted = 0;
		numLanded = 0;
		numCrashed = 0;
	}
	public void addLandSum(double landWait) {
		if (numDeparted == Integer.MAX_VALUE)
			throw new IllegalStateException("Too many numbers");
		landSum += landWait;
		numDeparted++;
	}
	public void addAirSum(double airWait) {
		if (numLanded == Integer.MAX_VALUE)
			throw new IllegalStateException("Too many numbers");
		airSum += airWait;
		numLanded++;
	}
	public double averageLandWait() {
		if (numDeparted == 0)
			return Double.NaN;
		else
			return landSum / numDeparted;
	}
	public double averageAirWait() {
		if (numLanded == 0)
			return Double.NaN;
		else
			return airSum / numLanded;
	}
	public int getNumLanded() {
		return numLanded;
	}
	public int getNumDeparted() {
		return numDeparted;
	}
	public void increaseCrashCount() {
		numCrashed++;
	}
	public int getCrashCount() {
		return numCrashed;
	}
}
