package converter;

import utils.Checker;

public class InfixToPrefixConverter {

	public static String convert(String expression) {

		Checker.isNullOrEmpty(expression);
		Checker.isBalancedParentheses(expression);
		Checker.isValidCharacters(expression);

		String reversedExpression = reverseExpression(expression);

		String postfix = InfixToPostfixConverter.convert(reversedExpression);

		return new StringBuilder(postfix).reverse().toString();
	}

	private static String reverseExpression(String expression) {
		return new StringBuilder(expression)
				.reverse()
				.toString()
				.replace('(', '#')
				.replace(')', '(')
				.replace('#', ')');
	}
}