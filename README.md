# Math Expression Converter

[![Math Expression](https://img.shields.io/badge/Math-Expression-blue.svg)](https://github.com/1M7md-CS/math-expression-converter)
[![Prefix](https://img.shields.io/badge/Notation-Prefix-blue)](https://simple.wikipedia.org/wiki/Prefix_notation)
[![Postfix](https://img.shields.io/badge/Notation-Postfix-blue)](https://simple.wikipedia.org/wiki/Postfix_notation)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-24-red.svg)](https://www.oracle.com/java/)
[![GitHub issues](https://img.shields.io/github/issues/1M7md-CS/math-expression-converter)](https://github.com/1M7md-CS/math-expression-converter/issues)

A simple tool to convert mathematical expressions between **Infix**, **Postfix**, and **Prefix** notations — and evaluate them.

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [License](#license)
- [Author](#author)

## Features

- Convert Infix to Postfix notation.
- Convert Infix to Prefix notation.
- Evaluate Postfix expressions.
- Evaluate Prefix expressions.
- Interactive **command-line interface** for easy usage.

## Installation

```bash
# Clone the repository
git clone https://github.com/1M7md-CS/math-expression-converter.git

# Navigate to the project directory
cd math-expression-converter

# Compile the Java files
javac -d bin src\converter\*.java src\evaluator\*.java src\utils\*.java src\main\*.java

# Run the application
java -cp bin main.EvaluatorMain
```

## Usage

```
Infix to Postfix: (3 + 4) * 2  
Output: 3 4 + 2 *

Infix to Prefix: (3 + 4) * 2  
Output: * + 3 4 2

Postfix Evaluation: 3 4 + 2 *  
Output: 14

Prefix Evaluation: * + 3 4 2  
Output: 14
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

Mohammad Hammad - [GitHub](https://github.com/1M7md-CS)
