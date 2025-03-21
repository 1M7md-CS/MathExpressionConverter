package evaluator;

import java.util.Stack;

import utils.Checker;
import utils.ExpressionUtils;

public class PrefixEvaluation {

	public static double evaluate(String expression) {
		Checker.isNullOrEmpty(expression);

		if (!Checker.isValidPrefix(expression)) {
			throw new IllegalArgumentException("Invalid prefix expression.");
		}

		Stack<Double> stack = new Stack<>();
		String[] tokens = expression.split("\\s+");

		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i];

			if (token.isEmpty()) continue;

			if (ExpressionUtils.isNumber(token)) {
				stack.push(Double.parseDouble(token));
			} else if (ExpressionUtils.isOperator(token.charAt(0))) {
				if (stack.size() < 2) {
					throw new IllegalArgumentException("Invalid prefix expression: not enough operands for operator " + token);
				}
				double firstOperand = stack.pop();
				double secondOperand = stack.pop();
				double result = ExpressionUtils.applyOperator(firstOperand, token.charAt(0), secondOperand);
				stack.push(result);
			} else {
				throw new IllegalArgumentException("Invalid token in expression: " + token);
			}
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException("Invalid prefix expression: too many operands.");
		}

		return stack.pop();
	}
}
