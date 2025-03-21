package utils;

public class Checker {

	public static void isNullOrEmpty(String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			throw new IllegalArgumentException("Expression cannot be null or empty.");
		}
	}

	public static void isBalancedParentheses(String expression) {
		if (!BalancedParentheses.isBalanced(expression)) {
			throw new IllegalArgumentException("Error: Unbalanced parentheses in expression!");
		}
	}

	public static void isValidCharacters(String expression) {
		for (char ch : expression.toCharArray()) {
			if (!Character.isDigit(ch) && !ExpressionUtils.isOperator(ch)
					&& ch != '(' && ch != ')' && !Character.isWhitespace(ch)) {
				throw new IllegalArgumentException("Error: Unsupported character '" + ch + "' in expression. Only numbers and operators are allowed.");
			}
		}
	}

	public static boolean isValidPrefix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

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

		return operandCount == 1;
	}

	public static boolean isValidPostfix(String expression) {
		int operandCount = 0;
		String[] tokens = expression.split("\\s+");

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

		return operandCount == 1;
	}
}