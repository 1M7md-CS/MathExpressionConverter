package main;

import converter.InfixToPostfixConverter;
import converter.InfixToPrefixConverter;
import evaluator.PostfixEvaluation;
import evaluator.PrefixEvaluation;
import utils.Checker;

import java.util.Scanner;
import java.util.function.Function;

/**
 * The main class for the Math Expression Converter and Evaluator application.
 * This program allows users to convert infix expressions to postfix or prefix notations,
 * and evaluate postfix or prefix expressions.
 */
public class EvaluatorMain {

	// Constants for menu options
	private static final int OPTION_CONVERT_INFIX_TO_POSTFIX = 1;
	private static final int OPTION_CONVERT_INFIX_TO_PREFIX = 2;
	private static final int OPTION_EVALUATE_POSTFIX = 3;
	private static final int OPTION_EVALUATE_PREFIX = 4;
	private static final int OPTION_HELP = 5;
	private static final int OPTION_EXIT = 6;

	// ANSI color codes for console output
	private static final String RESET = "\u001B[0m";
	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String BLUE = "\u001B[34m";
	private static final String PURPLE = "\u001B[35m";
	private static final String CYAN = "\u001B[36m";
	private static final String BOLD = "\u001B[1m";

	/**
	 * The main method that starts the application.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EvaluatorMain evaluator = new EvaluatorMain();

		while (true) {
			evaluator.displayMenu();
			int choice = evaluator.getIntInput(scanner, 1, 6);

			if (choice == OPTION_EXIT) {
				if (evaluator.confirmExit(scanner)) {
					System.out.println(GREEN + BOLD + "Thank you for using the Math Expression Converter and Evaluator. Goodbye!" + RESET);
					break;
				} else {
					continue;
				}
			}

			evaluator.handleUserChoice(choice, scanner);
			System.out.println(YELLOW + "\nPress Enter to continue..." + RESET);
			scanner.nextLine();
		}

		scanner.close();
	}

	/**
	 * Displays the main menu of the application.
	 */
	private void displayMenu() {
		String title = "Welcome to the Math Expression Converter and Evaluator!";
		String[] options = {"1. Convert Infix to Postfix", "2. Convert Infix to Prefix", "3. Evaluate Postfix Expression", "4. Evaluate Prefix Expression", "5. Help", "6. Exit"};

		int maxLength = Math.max(title.length(), getMaxLength(options));
		String border = "╭" + "─".repeat(maxLength + 4) + "╮";
		String bottomBorder = "╰" + "─".repeat(maxLength + 4) + "╯";

		System.out.println(PURPLE + BOLD + border + RESET);
		System.out.println("│ " + CYAN + BOLD + centerText(title, maxLength) + RESET + " │");
		System.out.println(PURPLE + BOLD + border + RESET);
		System.out.println("│ " + YELLOW + centerText("Please choose an option:", maxLength) + RESET + " │");

		for (String option : options) {
			System.out.println("│ " + BLUE + centerText(option, maxLength) + RESET + " │");
		}

		System.out.println(PURPLE + BOLD + bottomBorder + RESET);
		System.out.print(PURPLE + BOLD + "Enter your choice (1-6): " + RESET);
	}

	/**
	 * Calculates the maximum length of the options in the menu.
	 *
	 * @param options The array of menu options.
	 * @return The length of the longest option.
	 */
	private int getMaxLength(String[] options) {
		int maxLength = 0;
		for (String option : options) {
			maxLength = Math.max(maxLength, option.length());
		}
		return maxLength;
	}

	/**
	 * Centers the given text within a specified width.
	 *
	 * @param text  The text to center.
	 * @param width The total width for centering.
	 * @return The centered text.
	 */
	private String centerText(String text, int width) {
		if (text.length() >= width) {
			return text;
		}
		int padding = (width - text.length()) / 2;
		return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
	}

	/**
	 * Handles the user's menu choice.
	 *
	 * @param choice  The user's selected option.
	 * @param scanner The Scanner object for user input.
	 */
	private void handleUserChoice(int choice, Scanner scanner) {
		switch (choice) {
			case OPTION_CONVERT_INFIX_TO_POSTFIX:
				handleConversion(scanner, "infix", "postfix", InfixToPostfixConverter::convert);
				break;
			case OPTION_CONVERT_INFIX_TO_PREFIX:
				handleConversion(scanner, "infix", "prefix", InfixToPrefixConverter::convert);
				break;
			case OPTION_EVALUATE_POSTFIX:
				handleEvaluation(scanner, "postfix", PostfixEvaluation::evaluate);
				break;
			case OPTION_EVALUATE_PREFIX:
				handleEvaluation(scanner, "prefix", PrefixEvaluation::evaluate);
				break;
			case OPTION_HELP:
				displayHelp();
				break;
			default:
				System.out.println(RED + BOLD + "Invalid choice. Please try again." + RESET);
		}
	}

	/**
	 * Handles the conversion of infix expressions to postfix or prefix.
	 *
	 * @param scanner  The Scanner object for user input.
	 * @param from     The source notation (e.g., "infix").
	 * @param to       The target notation (e.g., "postfix").
	 * @param converter The function to perform the conversion.
	 */
	private void handleConversion(Scanner scanner, String from, String to, Function<String, String> converter) {
		System.out.print(CYAN + BOLD + "Enter the " + from + " expression: " + RESET);
		String expression = scanner.nextLine().trim();

		try {
			if (from.equals("infix")) {
				Checker.isValidCharacters(expression);
			} else if (to.equals("postfix") && !Checker.isValidPostfix(expression)) {
				throw new IllegalArgumentException("Invalid postfix expression.");
			} else if (to.equals("prefix") && !Checker.isValidPrefix(expression)) {
				throw new IllegalArgumentException("Invalid prefix expression.");
			}

			showProgressBar("Converting " + from + " to " + to + "...");
			String result = converter.apply(expression);
			System.out.println(GREEN + BOLD + to.substring(0, 1).toUpperCase() + to.substring(1) + " Expression: " + RESET + result);
		} catch (IllegalArgumentException e) {
			System.out.println(RED + BOLD + e.getMessage() + RESET);
		}
	}

	/**
	 * Handles the evaluation of postfix or prefix expressions.
	 *
	 * @param scanner  The Scanner object for user input.
	 * @param type     The type of expression (e.g., "postfix").
	 * @param evaluator The function to perform the evaluation.
	 */
	private void handleEvaluation(Scanner scanner, String type, Function<String, Double> evaluator) {
		System.out.print(CYAN + BOLD + "Enter the " + type + " expression: " + RESET);
		String expression = scanner.nextLine().trim();

		try {
			Checker.isValidCharacters(expression);

			if (type.equals("postfix") && !Checker.isValidPostfix(expression)) {
				throw new IllegalArgumentException("Invalid postfix expression.");
			} else if (type.equals("prefix") && !Checker.isValidPrefix(expression)) {
				throw new IllegalArgumentException("Invalid prefix expression.");
			}

			showProgressBar("Evaluating " + type + " expression...");
			double result = evaluator.apply(expression);
			System.out.println(GREEN + BOLD + "Result: " + RESET + result);
		} catch (IllegalArgumentException e) {
			System.out.println(RED + BOLD + e.getMessage() + RESET);
		}
	}

	/**
	 * Displays a progress bar with a message.
	 *
	 * @param message The message to display.
	 */
	private void showProgressBar(String message) {
		System.out.print(YELLOW + BOLD + message + RESET);
		for (int i = 0; i < 3; i++) {
			System.out.print(YELLOW + "." + RESET);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(RED + BOLD + "Error: Progress interrupted." + RESET);
			}
		}
		System.out.println();
	}

	/**
	 * Displays help information about infix, postfix, and prefix notations.
	 */
	private void displayHelp() {
		String title = "Help: Understanding Infix, Postfix, and Prefix Notations";
		String[] descriptions = {"Infix, Postfix, and Prefix are notations for writing mathematical expressions.", "- " + CYAN + "Infix" + RESET + ": Operators are written between operands (e.g., " + YELLOW + "2 + 3" + RESET + ").", "- " + CYAN + "Postfix" + RESET + ": Operators are written after operands (e.g., " + YELLOW + "2 3 +" + RESET + ").", "- " + CYAN + "Prefix" + RESET + ": Operators are written before operands (e.g., " + YELLOW + "+ 2 3" + RESET + ").", "Use this tool to convert between notations or evaluate expressions."};

		int maxLength = title.length();
		for (String desc : descriptions) {
			maxLength = Math.max(maxLength, desc.length());
		}

		String border = "╭" + "─".repeat(maxLength + 4) + "╮";
		String bottomBorder = "╰" + "─".repeat(maxLength + 4) + "╯";

		System.out.println(PURPLE + BOLD + border + RESET);
		System.out.println("│ " + CYAN + BOLD + centerText(title, maxLength) + RESET + " │");
		System.out.println(PURPLE + BOLD + border + RESET);

		for (String desc : descriptions) {
			System.out.println("│ " + desc + " ".repeat(maxLength - desc.length()) + " │");
		}

		System.out.println(PURPLE + BOLD + bottomBorder + RESET);
	}

	/**
	 * Prompts the user to confirm exit.
	 *
	 * @param scanner The Scanner object for user input.
	 * @return True if the user confirms exit, otherwise false.
	 */
	private boolean confirmExit(Scanner scanner) {
		System.out.print(RED + BOLD + "Are you sure you want to exit? (y/n): " + RESET);
		String input = scanner.nextLine().trim().toLowerCase();
		return input.equals("y") || input.equals("yes");
	}

	/**
	 * Gets an integer input from the user within a specified range.
	 *
	 * @param scanner The Scanner object for user input.
	 * @param min     The minimum allowed value.
	 * @param max     The maximum allowed value.
	 * @return The valid integer input from the user.
	 */
	private int getIntInput(Scanner scanner, int min, int max) {
		while (true) {
			try {
				int input = Integer.parseInt(scanner.nextLine());
				if (input >= min && input <= max) {
					return input;
				} else {
					System.out.println(RED + BOLD + "Please enter a number between " + min + " and " + max + "." + RESET);
				}
			} catch (NumberFormatException e) {
				System.out.println(RED + BOLD + "Invalid input. Please enter a valid number." + RESET);
			}
		}
	}
}