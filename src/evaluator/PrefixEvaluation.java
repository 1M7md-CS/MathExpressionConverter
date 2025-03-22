package evaluator;

import java.util.Stack;

import utils.Checker;
import utils.ExpressionUtils;

/**
 * A utility class for evaluating prefix expressions.
 * This class provides a method to evaluate a prefix expression and return the result as a double.
 */
public class PrefixEvaluation {

	/**
	 * Evaluates a prefix expression and returns the result.
	 *
	 * @param expression The prefix expression to evaluate.
	 * @return The result of the evaluation as a double.
	 * @throws IllegalArgumentException If the expression is null, empty, invalid, or contains unsupported tokens.
	 */
	public static double evaluate(String expression) {
		// Validate the expression
		Checker.isNullOrEmpty(expression);

		// Check if the expression is a valid prefix expression
		if (!Checker.isValidPrefix(expression)) {
			throw new IllegalArgumentException("Invalid prefix expression.");
		}

		// Stack to hold operands during evaluation
		Stack<Double> stack = new Stack<>();

		// Split the expression into tokens (numbers and operators)
		String[] tokens = expression.split("\\s+");

		// Process each token in the expression in reverse order
		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i];

			if (token.isEmpty()) continue; // Skip empty tokens

			// If the token is a number, push it onto the stack
			if (ExpressionUtils.isNumber(token)) {
				stack.push(Double.parseDouble(token));
			}
			// If the token is an operator, perform the operation
			else if (ExpressionUtils.isOperator(token.charAt(0))) {
				// Ensure there are enough operands on the stack
				if (stack.size() < 2) {
					throw new IllegalArgumentException("Invalid prefix expression: not enough operands for operator " + token);
				}
				double firstOperand = stack.pop(); // Pop the first operand
				double secondOperand = stack.pop(); // Pop the second operand
				double result = ExpressionUtils.applyOperator(firstOperand, token.charAt(0), secondOperand); // Apply the operator
				stack.push(result); // Push the result back onto the stack
			}
			// If the token is neither a number nor an operator, throw an exception
			else {
				throw new IllegalArgumentException("Invalid token in expression: " + token);
			}
		}

		// Ensure there is exactly one value left on the stack (the final result)
		if (stack.size() != 1) {
			throw new IllegalArgumentException("Invalid prefix expression: too many operands.");
		}

		// The final result is the only remaining value on the stack
		return stack.pop();
	}
}