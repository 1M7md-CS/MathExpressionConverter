package utils;

public class ExpressionUtils {

	public static boolean isNumber(String token) {
		return token.matches("-?\\d+");
	}

	public static boolean isOperator(char element) {
		return element == '+' || element == '-' || element == '*' || element == '/' || element == '^';
	}

	public static int applyOperator(int first, char operator, int second) {
		return switch (operator) {
			case '+' -> first + second;
			case '-' -> first - second;
			case '*' -> first * second;
			case '/' -> {
				if (second == 0) {
					throw new ArithmeticException("Division by zero is not allowed.");
				}
				yield first / second;
			}
			case '^' -> (int) Math.pow(first, second);
			default -> throw new IllegalArgumentException("Unknown operator: " + operator);
		};
	}

	public static int priority(char ch) {
		return switch (ch) {
			case '+', '-' -> 1;
			case '*', '/' -> 2;
			case '^' -> 3;
			default -> 0;
		};
	}
}
