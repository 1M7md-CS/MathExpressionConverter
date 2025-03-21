package converter;

import utils.Checker;
import utils.OperatorHandler;

import java.util.Stack;

public class InfixToPostfixConverter {

	public static String convert(String expression) {

		Checker.checkNullOrEmpty(expression);
		Checker.checkBalancedParentheses(expression);
		Checker.checkValidCharacters(expression);

		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		StringBuilder currentNumber = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);

			if (Character.isDigit(ch)) {
				handleDigit(ch, i, expression, currentNumber, postfix);
			} else if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				handleClosingParenthesis(stack, postfix);
			} else if (OperatorHandler.isOperator(ch)) {
				handleOperator(ch, stack, postfix);
			}
		}

		appendRemainingOperators(stack, postfix);

		return postfix.toString().trim();
	}

	private static void handleDigit(char ch, int currentIndex, String expression, StringBuilder currentNumber, StringBuilder postfix) {
		currentNumber.append(ch);
		if (currentIndex == expression.length() - 1 || !Character.isDigit(expression.charAt(currentIndex + 1))) {
			postfix.append(currentNumber).append(" ");
			currentNumber.setLength(0);
		}
	}

	private static void handleClosingParenthesis(Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty() && stack.peek() != '(') {
			postfix.append(stack.pop()).append(" ");
		}
		if (!stack.isEmpty() && stack.peek() == '(') {
			stack.pop();
		} else {
			throw new IllegalArgumentException("Mismatched parentheses in the expression.");
		}
	}

	private static void handleOperator(char operator, Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty() && stack.peek() != '(' && OperatorHandler.priority(stack.peek()) >= OperatorHandler.priority(operator)) {
			postfix.append(stack.pop()).append(" ");
		}
		stack.push(operator);
	}

	private static void appendRemainingOperators(Stack<Character> stack, StringBuilder postfix) {
		while (!stack.isEmpty()) {
			if (stack.peek() == '(') {
				throw new IllegalArgumentException("Mismatched parentheses in the expression.");
			}
			postfix.append(stack.pop()).append(" ");
		}
	}
}