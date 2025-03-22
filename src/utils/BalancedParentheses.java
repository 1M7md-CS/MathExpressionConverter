package utils;

import java.util.Stack;

/**
 * A utility class for checking if an expression has balanced parentheses.
 * This class provides a method to determine whether the parentheses in a given expression
 * are properly balanced.
 */
public class BalancedParentheses {

	/**
	 * Checks if the parentheses in the given expression are balanced.
	 *
	 * @param expression The expression to check for balanced parentheses.
	 * @return True if the parentheses are balanced, otherwise false.
	 */
	public static boolean isBalanced(String expression) {
		// Stack to keep track of opening parentheses
		Stack<Character> stack = new Stack<>();

		// Iterate through each character in the expression
		for (char ch : expression.toCharArray()) {
			// Skip digits, alphabetic characters, and spaces
			if (Character.isDigit(ch) || Character.isAlphabetic(ch) || ch == ' ') {
				continue;
			}

			// If the character is an opening parenthesis, push it onto the stack
			if (ch == '(') {
				stack.push(ch);
			}
			// If the character is a closing parenthesis, check for a matching opening parenthesis
			else if (ch == ')') {
				// If the stack is empty, there is no matching opening parenthesis
				if (stack.isEmpty()) {
					return false;
				}
				// Pop the matching opening parenthesis from the stack
				stack.pop();
			}
		}

		// If the stack is empty, all parentheses are balanced
		return stack.isEmpty();
	}
}