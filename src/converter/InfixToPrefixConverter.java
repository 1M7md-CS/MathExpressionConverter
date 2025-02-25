package converter;

import utils.BalancedParentheses;

public class InfixToPrefixConverter {

	public static String convert(String expression) {

		if (!BalancedParentheses.isBalanced(expression)) {
			throw new IllegalArgumentException("Error: Unbalanced parentheses in expression!");
		}

		expression = new StringBuilder(expression).reverse().toString();

		expression = expression.replace('(', ' ').replace(')', '(').replace(' ', ')');

		String postfix = InfixToPostfixConverter.convert(expression);

		return new StringBuilder(postfix).reverse().toString();
	}
}