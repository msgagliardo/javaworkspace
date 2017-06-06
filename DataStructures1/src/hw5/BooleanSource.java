package hw5;

public class BooleanSource {
	private double airProbability;
	private double groundProbability;
	
	public BooleanSource(double airP, double groundP) {
		airProbability = airP;
		groundProbability = groundP;
	}
	public boolean airQuery() {
		return (Math.random() < airProbability);
	}
	public boolean groundQuery() {
		return (Math.random() < groundProbability);
	}
	
}
