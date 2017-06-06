package hw5;
import java.util.Queue;
import java.util.LinkedList;

public class Runway {
	private int timeToLand;
	private int timeToTakeoff;
	private int landTimeRem;
	private int takeoffTimeRem;
	
	public Runway(int land, int takeoff) {
		timeToLand = land;
		timeToTakeoff = takeoff;
		landTimeRem = 0;
		takeoffTimeRem = 0;
	}
	public boolean isBusy() {
		return (landTimeRem > 0 || takeoffTimeRem > 0);
	}
	public void reduceTimeRem() {
		if (landTimeRem > 0)
			landTimeRem--;
		if (takeoffTimeRem > 0)
			takeoffTimeRem--;
	}
	public void land() {
		if (landTimeRem > 0 || takeoffTimeRem > 0)
			throw new IllegalStateException("There is a plane currently using the runway!");
		else
			landTimeRem = timeToLand;
	}
	public void takeoff() {
		if (landTimeRem > 0 || takeoffTimeRem > 0)
			throw new IllegalStateException("There is a plane currently using the runway!");
		else 
			takeoffTimeRem = timeToTakeoff;
	}
	public static void airportSim(int timeToLand, int timeToTakeoff, double arrivalLandProb,
			double arrivalAirProb, int airTimeMax, int totalSimTime) {
		
		Queue<Integer> arrivalLandTime = new LinkedList<Integer>();
		Queue<Integer> arrivalAirTime = new LinkedList<Integer>();
		int next = 0;
		int status = 0;
		Averager waitTimes = new Averager();
		BooleanSource newPlane = new BooleanSource(arrivalAirProb, arrivalLandProb);
		Runway run = new Runway(timeToLand, timeToTakeoff);
		
		if ((arrivalAirProb < 0 || arrivalAirProb > 1) || (arrivalLandProb < 0 ||
				arrivalLandProb > 1) || (airTimeMax < 0) || (timeToLand < 0) ||
				(timeToTakeoff < 0) || (totalSimTime < 0))
			throw new IllegalArgumentException("Invalid inputs");
		
		for (int currentMinute = 0; currentMinute < totalSimTime; currentMinute++) {
			if (newPlane.groundQuery())
				arrivalLandTime.add(currentMinute);
			if (newPlane.airQuery())
				arrivalAirTime.add(currentMinute);
			
			if (!run.isBusy() && !arrivalLandTime.isEmpty() && arrivalAirTime.isEmpty()) {
				next = arrivalLandTime.remove();
				waitTimes.addLandSum(currentMinute - next);
				run.takeoff();
			}
			if (!run.isBusy() && !arrivalAirTime.isEmpty()) {
				next = arrivalAirTime.remove();
				status = currentMinute - next;
				if (status < airTimeMax) {
					waitTimes.addAirSum(status);
					run.land();
				}else 
					waitTimes.increaseCrashCount();
			}
			if (run.isBusy())
				run.reduceTimeRem();
		}
		System.out.println("Number of planes taken off: " + waitTimes.getNumDeparted());
		System.out.println("Number of planes landed: " + waitTimes.getNumLanded());
		System.out.println("Number of planes crashed: " + waitTimes.getCrashCount());
		System.out.printf("Average waiting time for taking off: %.2f " +
				"minutes\n", waitTimes.averageLandWait());
		System.out.printf("Average waiting time for landing: %.2f " + 
				"minutes", waitTimes.averageAirWait());
	}
	public static void main(String[] args) {
		airportSim(4, 2, 0.05, 0.05, 2, 6000);
	}
}
