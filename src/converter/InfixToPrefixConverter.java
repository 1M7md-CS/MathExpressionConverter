package converter;

import utils.Checker;

/**
 * A utility class for converting infix expressions to prefix notation.
 * This class provides methods to reverse the expression, convert it to postfix,
 * and then reverse the result to obtain the prefix notation.
 */
public class InfixToPrefixConverter {

	/**
	 * Converts an infix expression to prefix notation.
	 *
	 * @param expression The infix expression to convert.
	 * @return The prefix expression as a String.
	 * @throws IllegalArgumentException If the expression is null, empty, contains invalid characters,
	 *                                  or has mismatched parentheses.
	 */
	public static String convert(String expression) {
		// Validate the expression
		Checker.isNullOrEmpty(expression);
		Checker.isBalancedParentheses(expression);
		Checker.isValidCharacters(expression);

		// Reverse the expression for processing
		String reversedExpression = reverseExpression(expression);

		// Convert the reversed expression to postfix
		String postfix = InfixToPostfixConverter.convert(reversedExpression);

		// Reverse the postfix result to get the prefix expression
		return new StringBuilder(postfix).reverse().toString();
	}

	/**
	 * Reverses the given expression and swaps parentheses to handle the conversion correctly.
	 *
	 * @param expression The original infix expression.
	 * @return The reversed expression with swapped parentheses.
	 */
	private static String reverseExpression(String expression) {
		return new StringBuilder(expression)
				.reverse()
				.toString()
				.replace('(', '#')  // Temporarily replace '(' with '#' to avoid conflicts
				.replace(')', '(')  // Replace ')' with '('
				.replace('#', ')'); // Replace '#' with ')'
	}
}