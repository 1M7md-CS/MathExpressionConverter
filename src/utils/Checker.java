package utils;

public class Checker {

	public static void checkNullOrEmpty(String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Expression cannot be null or empty.");
		}
	}

	public static void checkBalancedParentheses(String expression) {
		if (!BalancedParentheses.isBalanced(expression)) {
			throw new IllegalArgumentException("Error: Unbalanced parentheses in expression!");
		}
	}

	public static void checkValidCharacters(String expression) {
		for (char ch : expression.toCharArray()) {
			if (!Character.isDigit(ch) && !OperatorHandler.isOperator(ch)
					&& ch != '(' && ch != ')' && !Character.isWhitespace(ch)) {
				throw new IllegalArgumentException("Error: Unsupported character '" + ch + "' in expression. Only numbers and operators are allowed.");
			}
		}
	}
}