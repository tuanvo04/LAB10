package stack_queue;

import java.util.Stack;

import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> stackReverse = new Stack<E>();
		for (E elemnent : array) {
			stackReverse.push(elemnent);
		}
		for (int i = 0; i < array.length; i++) {
			if (stackReverse.isEmpty()) {
				break;
			}
			array[i] = stackReverse.pop();
		}
	}

	// This method checks the correctness of the given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		Stack<Character> stack = new Stack<>();
		for (char c : input.toCharArray()) {
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else if (c == ')' || c == ']' || c == '}') {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if ((top == '(' && c == ')') || (top == '[' && c == ']') || (top == '{' && c == '}')) {
					return true;
				}
			}
		}
		return stack.isEmpty();
	}

	// This method evaluates the value of an expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String expression) {
		expression = insertBlanks(expression);
		Stack<Integer> operandStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();
		String[] tokens = expression.split(" ");
		for (String token : tokens) {
			if (token.length() == 0) {
				continue;
			} else if (Character.isDigit(token.charAt(0))) {
				operandStack.push(Integer.parseInt(token));
			} else if (token.charAt(0) == '(') {
				operatorStack.push('(');
			} else if (token.charAt(0) == ')') {
				while (operatorStack.peek() != '(') {
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.pop();
			} else if (token.charAt(0) == '+' || token.charAt(0) == '-' || token.charAt(0) == '*'
					|| token.charAt(0) == '/') {
				while (!operatorStack.isEmpty() && (precedence(token.charAt(0)) <= precedence(operatorStack.peek()))) {
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.push(token.charAt(0));
			}
		}
		while (!operatorStack.isEmpty()) {
			processAnOperator(operandStack, operatorStack);
		}
		return operandStack.pop();
	}

	public static String insertBlanks(String input) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/'
					|| input.charAt(i) == '(' || input.charAt(i) == ')') {
				result = result + " " + input.charAt(i) + " ";
			} else {
				result = result + input.charAt(i);
			}
		}
		return result;
	}

	// This method returns the precedence of an operator
	public static int precedence(char operator) {
		if (operator == '+' || operator == '-') {
			return 1;
		} else if (operator == '*' || operator == '/') {
			return 2;
		} else {
			return 0;
		}
	}

	// This method pops and processes an operator and its operands from the stacks
	public static void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
		char operator = operatorStack.pop();
		int operand2 = operandStack.pop();
		int operand1 = operandStack.pop();
		int result = 0;
		switch (operator) {
		case '+':
			result = operand1 + operand2;
			break;
		case '-':
			result = operand1 - operand2;
			break;
		case '*':
			result = operand1 * operand2;
			break;
		case '/':
			result = operand1 / operand2;
			break;
		}
		operandStack.push(result);
	}

	public static void main(String[] args) {
		Integer[] myArray = { 1, 2, 3, 4, 5 };
		System.out.println("Original array: " + Arrays.toString(myArray));
		reserve(myArray);
		System.out.println("Reversed array: " + Arrays.toString(myArray));
		String ch1 = "()(())[]{(())}";
		String ch2 = ")()";
		System.out.println("This " + ch1 + " is " + isCorrect(ch1));
		System.out.println("This " + ch1 + " is " + isCorrect(ch2));
		String expression1 = "51 + (54 *(3+2)) = 321";
		String expression2 = "((2+3) * 4 - 1) / (3+2)";
		System.out.println("The value of " + expression1 + " is: " + evaluateExpression(expression1));
		System.out.println("The value of " + expression2 + " is: " + evaluateExpression(expression2));
	}
}