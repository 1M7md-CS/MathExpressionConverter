package evaluator;

import java.util.Stack;

import utils.Checker;
import utils.ExpressionUtils;

public class PostfixEvaluation {

	public static double evaluate(String expression) {

		Checker.isNullOrEmpty(expression);

		if (!Checker.isValidPostfix(expression)) {
			throw new IllegalArgumentException("Invalid postfix expression.");
		}

		Stack<Double> stack = new Stack<>();
		String[] tokens = expression.split("\\s+");

		for (String token : tokens) {
			if (token.isEmpty()) continue;

			if (ExpressionUtils.isNumber(token)) {
				stack.push(Double.parseDouble(token));
			} else if (ExpressionUtils.isOperator(token.charAt(0))) {
				double secondOperand = stack.pop();
				double firstOperand = stack.pop();
				double result = ExpressionUtils.applyOperator(firstOperand, token.charAt(0), secondOperand);
				stack.push(result);
			}
		}

		return stack.pop();
	}
}
