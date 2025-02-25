package converter;

import utils.Checker;
import utils.OperatorHandler;

import java.util.Stack;

public class InfixToPostfixConverter {

	public static String convert(String expression) {

		Checker.checkBalancedParentheses(expression);
		Checker.checkValidCharacters(expression);

		Stack<Character> stack = new Stack<>();
		StringBuilder builder = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isDigit(ch)) {
				currentNumber.append(ch);

				if (i == expression.length() - 1 || !Character.isDigit(expression.charAt(i + 1))) {
					builder.append(currentNumber).append(" ");
					currentNumber.setLength(0);
				}
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					builder.append(stack.pop()).append(" ");
				}

				if (!stack.isEmpty()) stack.pop();
			} else if (OperatorHandler.isOperator(ch)) {
				while (!stack.isEmpty() && stack.peek() != '(' && OperatorHandler.priority(stack.peek()) >= OperatorHandler.priority(ch)) {
					builder.append(stack.pop()).append(" ");
				}
				stack.push(ch);
			}
		}

		while (!stack.isEmpty()) {
			builder.append(stack.pop()).append(" ");
		}

		return builder.toString().trim();
	}

}