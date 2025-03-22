package evaluator;

import java.util.Stack;

import utils.Checker;
import utils.ExpressionUtils;

/**
 * A utility class for evaluating postfix expressions.
 * This class provides a method to evaluate a postfix expression and return the result as a double.
 */
public class PostfixEvaluation {

	/**
	 * Evaluates a postfix expression and returns the result.
	 *
	 * @param expression The postfix expression to evaluate.
	 * @return The result of the evaluation as a double.
	 * @throws IllegalArgumentException If the expression is null, empty, or invalid.
	 */
	public static double evaluate(String expression) {
		// Validate the expression
		Checker.isNullOrEmpty(expression);

		// Check if the expression is a valid postfix expression
		if (!Checker.isValidPostfix(expression)) {
			throw new IllegalArgumentException("Invalid postfix expression.");
		}

		// Stack to hold operands during evaluation
		Stack<Double> stack = new Stack<>();

		// Split the expression into tokens (numbers and operators)
		String[] tokens = expression.split("\\s+");

		// Process each token in the expression
		for (String token : tokens) {
			if (token.isEmpty()) continue; // Skip empty tokens

			// If the token is a number, push it onto the stack
			if (ExpressionUtils.isNumber(token)) {
				stack.push(Double.parseDouble(token));
			}
			// If the token is an operator, perform the operation
			else if (ExpressionUtils.isOperator(token.charAt(0))) {
				double secondOperand = stack.pop(); // Pop the second operand
				double firstOperand = stack.pop();  // Pop the first operand
				double result = ExpressionUtils.applyOperator(firstOperand, token.charAt(0), secondOperand); // Apply the operator
				stack.push(result); // Push the result back onto the stack
			}
		}

		// The final result is the only remaining value on the stack
		return stack.pop();
	}
}