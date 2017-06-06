/* Marc Gagliardo 					September 3, 2015
   CPS593.02									  hw0*/

package hw0;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class GradeSorter {
	private static int a, b, c, d, f;

	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(
				new FileReader("grades.txt"))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				int grade = Integer.parseInt(line);
				
				if (90 <= grade && grade <= 100)
					a++;
				else if (80 <= grade && grade <= 89)
					b++;
				else if (70 <= grade && grade <= 79)
					c++;
				else if (60 <= grade && grade <= 69)
					d++;
				else if (0 <= grade && grade <= 59)
					f++;
				else
					throw new Exception();
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Invalid grade found in file.");
		}
		System.out.println("Total number of grades = " + (a+b+c+d+f));
		System.out.println("Number of A's = " + a);
		System.out.println("Number of B's = " + b);
		System.out.println("Number of C's = " + c);
		System.out.println("Number of D's = " + d);
		System.out.println("Number of F's = " + f);
	}
}
