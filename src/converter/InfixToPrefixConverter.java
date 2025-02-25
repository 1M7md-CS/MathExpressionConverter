package converter;

import utils.Checker;

public class InfixToPrefixConverter {

	public static String convert(String expression) {

		Checker.checkBalancedParentheses(expression);
		Checker.checkValidCharacters(expression);

		expression = new StringBuilder(expression).reverse().toString();

		expression = expression.replace('(', '#').replace(')', '(').replace('#', ')');

		String postfix = InfixToPostfixConverter.convert(expression);

		return new StringBuilder(postfix).reverse().toString();
	}
}