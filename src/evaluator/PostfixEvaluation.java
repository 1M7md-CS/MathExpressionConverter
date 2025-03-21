package evaluator;

import java.util.Stack;

import utils.Checker;
import utils.ExpressionUtils;

public class PostfixEvaluation {

	public static int evaluate(String expression) {

		Checker.isNullOrEmpty(expression);

		if (!Checker.isValidPostfix(expression)) {
			throw new IllegalArgumentException("Invalid postfix expression.");
		}

		Stack<Integer> stack = new Stack<>();
		String[] tokens = expression.split("\\s+");

		for (String token : tokens) {
			if (token.isEmpty()) continue;

			if (ExpressionUtils.isNumber(token)) {
				stack.push(Integer.parseInt(token));
			} else if (ExpressionUtils.isOperator(token.charAt(0))) {
				int secondOperand = stack.pop();
				int firstOperand = stack.pop();
				int result = ExpressionUtils.applyOperator(firstOperand, token.charAt(0), secondOperand);
				stack.push(result);
			}
		}

		return stack.pop();
	}
}
