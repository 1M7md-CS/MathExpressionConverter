package converter;

import utils.Checker;
import utils.ExpressionUtils;

import java.util.Stack;

/**
 * A utility class for converting infix expressions to postfix notation.
 * This class provides methods to handle digits, operators, and parentheses
 * during the conversion process.
 */
public class InfixToPostfixConverter {

	/**
	 * Converts an infix expression to postfix notation.
	 *
	 * @param expression The infix expression to convert.
	 * @return The postfix expression as a String.
	 * @throws IllegalArgumentException If the expression is null, empty, contains invalid characters,
	 *                                  or has mismatched parentheses.
	 */
	public static String convert(String expression) {
		// Validate the expression
		Checker.isNullOrEmpty(expression);
		Checker.isBalancedParentheses(expression);
		Checker.isValidCharacters(expression);

		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();

		// Process each character in the expression
		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isDigit(ch)) {
				handleDigit(ch, i, expression, currentNumber, postfix);
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				handleClosingParenthesis(stack, postfix);
			} else if (ExpressionUtils.isOperator(ch)) {
				handleOperator(ch, stack, postfix);
			}
		}

		// Append any remaining operators in the stack to the postfix expression
		appendRemainingOperators(stack, postfix);

		return postfix.toString().trim();
	}

	/**
	 * Handles digits in the expression by appending them to the current number
	 * and adding the complete number to the postfix expression.
	 *
	 * @param ch            The current character (digit).
	 * @param currentIndex  The current index in the expression.
	 * @param expression    The full infix expression.
	 * @param currentNumber A StringBuilder to accumulate multi-digit numbers.
	 * @param postfix       The StringBuilder for the postfix expression.
	 */
	private static void handleDigit(char ch, int currentIndex, String expression, StringBuilder currentNumber, StringBuilder postfix) {
		currentNumber.append(ch);
		if (currentIndex == expression.length() - 1 || !Character.isDigit(expression.charAt(currentIndex + 1))) {
			postfix.append(currentNumber).append(" ");
			currentNumber.setLength(0); // Reset for the next number
		}
	}

	/**
	 * Handles closing parentheses by popping operators from the stack
	 * to the postfix expression until an opening parenthesis is found.
	 *
	 * @param stack   The stack containing operators and parentheses.
	 * @param postfix The StringBuilder for the postfix expression.
	 * @throws IllegalArgumentException If there are mismatched parentheses.
	 */
	private static void handleClosingParenthesis(Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty() && stack.peek() != '(') {
			postfix.append(stack.pop()).append(" ");
		}
		if (!stack.isEmpty() && stack.peek() == '(') {
			stack.pop(); // Remove the opening parenthesis
		} else {
			throw new IllegalArgumentException("Mismatched parentheses in the expression.");
		}
	}

	/**
	 * Handles operators by comparing their precedence with operators in the stack
	 * and pushing them to the stack or popping higher precedence operators to the postfix expression.
	 *
	 * @param operator The current operator.
	 * @param stack    The stack containing operators and parentheses.
	 * @param postfix  The StringBuilder for the postfix expression.
	 */
	private static void handleOperator(char operator, Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty() && stack.peek() != '(' && ExpressionUtils.priority(stack.peek()) >= ExpressionUtils.priority(operator)) {
			postfix.append(stack.pop()).append(" ");
		}
		stack.push(operator);
	}

	/**
	 * Appends any remaining operators in the stack to the postfix expression.
	 *
	 * @param stack   The stack containing remaining operators.
	 * @param postfix The StringBuilder for the postfix expression.
	 * @throws IllegalArgumentException If there are mismatched parentheses.
	 */
	private static void appendRemainingOperators(Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				throw new IllegalArgumentException("Mismatched parentheses in the expression.");
			}
			postfix.append(stack.pop()).append(" ");
		}
	}
}