package hw0;
import java.util.ArrayList;
import java.util.List;

public class Statistician {
	private List<Double> statList;
	
	public Statistician() {
		statList = new ArrayList<Double>();
	}
	public void next(double number) {
		int index = length();
		statList.add(index, number);
	}
	public double sum() {
		double listSum = 0;
		
		for (int i = 0; i < statList.size(); i++) 
			listSum += statList.get(i);
		
		if (listSum > Double.MAX_VALUE)
			return Double.POSITIVE_INFINITY;
		else if (listSum < 0)
			return Double.NEGATIVE_INFINITY;
		else 
			return listSum;
	}
	public void clear() {
		statList.clear();
	}
	public int length() {
		return statList.size();
	}
	public double mean() {
		
		if (length() == 0)
			return Double.NaN;
		else 
			return sum() / length();
	}
	public void addAll(Statistician addend) {
		
		if (addend != null) {
			for (int i = 0; i < addend.length(); i++) {
				next(addend.statList.get(i));
			}
		} else 
			throw new NullPointerException("An argument with a null value was entered.");
	}
	public double maximum() {
		double max = 0;
		
		if (length() == 0)
			return Double.NaN;
		
		for (int i = 0; i < length(); i++) {
			if (statList.get(i) > max)
				max = statList.get(i);
		}
		return max;
	}
	public double minimum() {
		double min = Double.MAX_VALUE;
		
		if (length() == 0)
			return Double.NaN;
		
		for (int i = 0; i < length(); i++) {
			if (statList.get(i) < min)
				min = statList.get(i);
		}
		return min;
	}
	public static Statistician union(Statistician s1, Statistician s2) {
		Statistician s3 = new Statistician();
		
		if ((s1 == null) || (s2 == null))
			throw new NullPointerException("One of the arguments " +
					"entered has a null value.");
		else {
			for (int i = 0; i < s2.length(); i++) 
				s3.next(s2.statList.get(i));
			for (int i = 0; i < s1.length(); i++)
				s3.next(s1.statList.get(i));
		}
		return s3;
	}
	public boolean equals(Object obj) {
		
		if (obj instanceof Statistician) {
			Statistician candidate = (Statistician)obj;
			return (length() == candidate.length()) && (sum() == candidate.sum())
					&& (mean() == candidate.mean()) && (maximum() == candidate.maximum())
					&& (minimum() == candidate.minimum());
		}else
			return false;
	}
	public static void main(String[] args) {
		
	}
}
