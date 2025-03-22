package utils;

/**
 * A utility class for handling mathematical expressions.
 * This class provides methods to check if a token is a number, if a character is an operator,
 * apply an operator to two operands, and determine the priority of an operator.
 */
public class ExpressionUtils {

	/**
	 * Checks if the given token is a valid number (integer or decimal).
	 *
	 * @param token The token to check.
	 * @return True if the token is a valid number, otherwise false.
	 */
	public static boolean isNumber(String token) {
		return token.matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * Checks if the given character is a valid operator (+, -, *, /, ^).
	 *
	 * @param element The character to check.
	 * @return True if the character is a valid operator, otherwise false.
	 */
	public static boolean isOperator(char element) {
		return element == '+' || element == '-' || element == '*' || element == '/' || element == '^';
	}

	/**
	 * Applies the given operator to two operands and returns the result.
	 *
	 * @param first    The first operand.
	 * @param operator The operator to apply.
	 * @param second   The second operand.
	 * @return The result of applying the operator to the operands.
	 * @throws ArithmeticException If division by zero is attempted.
	 * @throws IllegalArgumentException If the operator is unknown.
	 */
	public static double applyOperator(double first, char operator, double second) {
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
			case '^' -> Math.pow(first, second);
			default -> throw new IllegalArgumentException("Unknown operator: " + operator);
		};
	}

	/**
	 * Returns the priority of the given operator.
	 *
	 * @param ch The operator character.
	 * @return The priority of the operator (1 for + and -, 2 for * and /, 3 for ^, 0 for unknown).
	 */
	public static int priority(char ch) {
		return switch (ch) {
			case '+', '-' -> 1;
			case '*', '/' -> 2;
			case '^' -> 3;
			default -> 0;
		};
	}
}