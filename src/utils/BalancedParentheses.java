package utils;

import java.util.Stack;

public class BalancedParentheses {

	public static boolean isBalanced(String expression) {

		Stack<Character> stack = new Stack<>();

		for (char ch : expression.toCharArray()) {
			if (Character.isDigit(ch) || Character.isAlphabetic(ch) || ch == ' ') continue;
			if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
}
