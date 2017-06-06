package hw4;
import java.util.Stack;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;

public class PostfixExpressionCalc {
	private static ArrayList<String> lines;
	
	public static void evaluate(String file) {
		Stack<Double> numbers = new Stack<Double>();
		double operand = 0;
		String operator = null;
		Path myPath = Paths.get(file);
		
		scanLines(myPath);
		
		try {
			Scanner input = new Scanner(myPath);
			
			while (input.hasNext()) {
				if (input.hasNextDouble()) {
					operand = input.nextDouble();
					numbers.push(operand);
				}else {
					operator = input.next();
					evaluateStackTop(numbers, operator.charAt(0));
				}
			}
			input.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		String newItem = null;
		for (int i = lines.size() - 1; i >= 0; i--) {
			newItem = lines.get(i) + numbers.pop();
			lines.set(i, newItem);
		}
		if (!numbers.empty())
			throw new IllegalArgumentException("Illegal expression");
		
		for (String line: lines)
			System.out.println(line);
	}
	public static void evaluateStackTop(Stack<Double> numStack, char operation) {
		double op2 = 0;
		double op1 = 0;
		
		if ((numStack.size() < 2) && (operation != '_' && operation != '#'
				&& operation != '^'))
			throw new IllegalArgumentException("Illegal expression");
		
		if (numStack.size() == 0)
			throw new IllegalArgumentException("Illegal expression");
		
		op2 = numStack.pop();
		if ((!numStack.empty()) && (operation != '_' && operation != '#'))
			op1 = numStack.pop();
		
		switch (operation) {
		
			case '+': numStack.push(op1 + op2);
				      break;
			case '-': numStack.push(op1 - op2);
					  break;
			case '*': numStack.push(op1 * op2);
					  break;
			case '/': numStack.push(op1 / op2);
					  break;
			case '_': numStack.push(op2 * -1);
					  break;
			case '#': numStack.push(Math.sqrt(op2));
					  break;
			case '^': numStack.push(Math.pow(op1, op2));
					  break;
		}
	}
	public static void scanLines(Path file) {
		lines = new ArrayList<String>();
		
		try {
			Scanner lineInput = new Scanner(file);
			while (lineInput.hasNextLine()) {
				lines.add("The value of " + lineInput.nextLine() + " is ");
			}
			lineInput.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		evaluate("in.dat");
	}
}
