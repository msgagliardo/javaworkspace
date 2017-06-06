import java.util.ArrayList;

public class Solver {
	
	private int column = 0;
	private int boardEdge;
	private int numSolutions = 0;
	private ArrayList<ArrayList<Point>> solutions = new ArrayList<ArrayList<Point>>();
	
	public Solver(int edge) {
		boardEdge = edge;
		ArrayList<Point> firstSolution = new ArrayList<Point>();
		solutions.add(firstSolution);
	}
	public boolean isSafe(int col, int row) {
		
		for (int i = 0; i < col; i++) {
			Point p = solutions.get(numSolutions).get(i);
			if ((p.getX() == col) || (p.getY() == row) || 
					Math.abs((p.getY() - row) / (p.getX() - col)) == 1)
				return false;
		}
		return true;
	}

	public void findSolutions() {
		
		if (column == boardEdge && !solutions.get(0).isEmpty()) {
			solutions.add(new ArrayList<Point>());
			for (Point p: solutions.get(numSolutions)) {
				Point pNew = p.clone();
				solutions.get(numSolutions + 1).add(pNew);
			}
			numSolutions++;
			column--;
			return;
		}
		for (int i = 0; i < boardEdge; i++) {
			if (isSafe(column, i)) {
				Point newSquare = new Point(column, i);
				if (solutions.get(numSolutions).size() >= (column + 1)) {
					solutions.get(numSolutions).set(column, newSquare);
				}else {
					solutions.get(numSolutions).add(column, newSquare);
				}
				column++;
				findSolutions();
			}
		}
		column--;
		return;
	}
	public static void main(String[] args) {
		Solver s = new Solver(8);
		s.findSolutions();
		System.out.println(s.solutions.size());
		for (Point p: s.solutions.get(92)) {
			System.out.println("(" + p.getX() + ", " + p.getY() + ")");
		}
		System.out.println(s.numSolutions);
		System.out.println(s.solutions.get(0).size());
		
		/*ArrayList<ArrayList<Integer>> test = new ArrayList<ArrayList<Integer>>();
		test.add(new ArrayList<Integer>());
		test.get(0).add(1);
		test.get(0).add(2);
		System.out.println(test.get(0).toString());*/
		
	}
}
