package hw0;
import java.util.Scanner;

public class GradeSorter2 {
	private static int a, b, c, d, f;
	
	public static void main(String[] args) {
		
		System.out.print("Please enter the grades to be sorted: ");
		try (Scanner sc = new Scanner(System.in)) {
			
			int grades = sc.nextInt();
			while (sc.hasNextInt()) {
				if (90 <= grades && grades <= 100)
					a++;
				else if (80 <= grades && grades <= 89)
					b++;
				else if (70 <= grades && grades <= 79)
					c++;
				else if (60 <= grades && grades <= 69)
					d++;
				else if (0 <= grades && grades <= 59)
					f++;
				else
					throw new Exception();
			}
		} catch (Exception e) {
			System.err.println("An invalid grade was entered.");
		}
		System.out.println("Total number of grades = " + (a+b+c+d+f));
	}
}
