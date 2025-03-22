package utils;

/**
 * A utility class for validating expressions.
 * This class provides methods to check if an expression is null or empty, has balanced parentheses,
 * contains valid characters, and is a valid prefix or postfix expression.
 */
public class Checker {

	/**
	 * Checks if the given expression is null or empty.
	 *
	 * @param expression The expression to check.
	 * @throws IllegalArgumentException If the expression is null or empty.
	 */
	public static void isNullOrEmpty(String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Expression cannot be null or empty.");
		}
	}

	/**
	 * Checks if the parentheses in the given expression are balanced.
	 *
	 * @param expression The expression to check.
	 * @throws IllegalArgumentException If the parentheses are not balanced.
	 */
	public static void isBalancedParentheses(String expression) {
		if (!BalancedParentheses.isBalanced(expression)) {
			throw new IllegalArgumentException("Error: Unbalanced parentheses in expression!");
		}
	}

	/**
	 * Checks if the given expression contains only valid characters (digits, operators, parentheses, and spaces).
	 *
	 * @param expression The expression to check.
	 * @throws IllegalArgumentException If the expression contains unsupported characters.
	 */
	public static void isValidCharacters(String expression) {
		for (char ch : expression.toCharArray()) {
			if (!Character.isDigit(ch) && !ExpressionUtils.isOperator(ch)
					&& ch != '(' && ch != ')' && !Character.isWhitespace(ch)) {
				throw new IllegalArgumentException("Error: Unsupported character '" + ch + "' in expression. Only numbers and operators are allowed.");
			}
		}
	}

	/**
	 * Checks if the given expression is a valid prefix expression.
	 *
	 * @param expression The expression to check.
	 * @return True if the expression is a valid prefix expression, otherwise false.
	 */
	public static boolean isValidPrefix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

		// Process tokens in reverse order
		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i];
			if (token.isEmpty()) continue;

			if (ExpressionUtils.isNumber(token)) {
				operandCount++;
			} else if (ExpressionUtils.isOperator(token.charAt(0))) {
				if (operandCount < 2) {
					return false;
				}
				operandCount--;
			}
		}

		// A valid prefix expression should have exactly one operand left
		return operandCount == 1;
	}

	/**
	 * Checks if the given expression is a valid postfix expression.
	 *
	 * @param expression The expression to check.
	 * @return True if the expression is a valid postfix expression, otherwise false.
	 */
	public static boolean isValidPostfix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

		// Process tokens in order
		for (String token : tokens) {
			if (token.isEmpty()) continue;

			if (ExpressionUtils.isNumber(token)) {
				operandCount++;
			} else if (ExpressionUtils.isOperator(token.charAt(0))) {
				if (operandCount < 2) {
					return false;
				}
				operandCount--;
			}
		}

		// A valid postfix expression should have exactly one operand left
		return operandCount == 1;
	}
}