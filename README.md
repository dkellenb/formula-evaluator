# Formula Evaluator

## Goal

Goal of this Library is to make calculation codes with BigDecimals much more readable and a lot of Java Code. By defining the formula with a string expression, the code should be better understandable and maintainable.

## Usage

Simple usage:

```java
BigDecimal result = FormulaEvaluator.create("IF(a >= 8, a * betta ^ 3, a / gamma)")
                                    .with("a", a).and("betta", betta).and("gamma", gamma)
                                    .eval();
```

### Configuration option

#### Precisions
Define with which precision the calculations should be performed.

Methods:
`setPrecision(int)`: Sets calculation and result precision
`setResultPrecision(int)`: Sets result precision
`setCalculationPrecision(int)`: Sets calculation precision
(see [MathContext](https://docs.oracle.com/javase/8/docs/api/java/math/MathContext.html))

#### Rounding modes
Define how values should be rounded.

Method:
`setRoundingMode(RoundingMode)`: Sets the rounding Mode (see [RoundingMode](https://docs.oracle.com/javase/8/docs/api/java/math/RoundingMode.html))

#### Default NULL handling
Define the generic behavior of `null` values.

Method:
`setDefaultNullHandling(DefaultNullHandling)`

Available options:
 * `EXCEPTION`: Throw an exception if one value is null (default).
 * `NULL`: Return null if at least one value of an operation or function is null
 * `ZERO`: Null is equal to 0

Example:
```java
BigDecimal result = FormulaEvaluator.create("a / b")
                                    .setDefaultNullHandling(NULL)
                                    .with("a", null)
                                    .with("b", ONE)
                                    .eval();
```

### Specific NULL handling
Define for some basic operations their handling of `null` values.

Methods:
`setPlusMinusNullHandling(BasicOperationsNullHandling)`
`setMultiplicationNullHandling(BasicOperationsNullHandling)`
`setDivisionNullHandling(BasicOperationsNullHandling)`

Available options:
 * `INHERIT`: Use default handling (default).
 * `IDENTITY`: Operation should return the same value (+-: 0, */: 1)

### Specific Division through zero handling
Define how the calculation should behave on division by zero.

Method:
 * `setDivisionByZeroHandling(DivisionByZeroHandling)`

Available options:
 * `INHERIT`: Inherit checks from default.
 * `ONE`:  Zero is treated as 1. (a / 0 = a)
 * `NULL`: When denominator is zero it returns null.

## Supported operators and functions
### Operators
 * `+` (Addition)
 * `-` (Subtraction)
 * `*` (Multiplication)
 * `/` (Division)
 * `%` (Modulo)
 * `^` (Exponentiation)
 * `&&` (logical and)
 * `||` (logical or)
 * `>` (greater)
 * `>=` (greater equal)
 * `<` (smaller)
 * `<=` (smaller equal)
 * `==` or `=` (greater)
 * `!=` or `<>` (not equal)

### Functions
 * `NOT(term)`
 * `IF(condTerm, trueTerm, falseTerm)`
 * `RANDOM()`
 * `SIN(term)`, `COS(term)`, `TAN(term)`, `SINH(term)`, `COSH(term)`, `TANH(term)`
 * `DEG(term)`, `RAD(term)`
 * `MIN(term1, term2)`, `MAX(term1, term2)`
 * `LOG(term)`, `LOG10(term)`
 * `ROUND(valueTerm, precisionTerm)`
 * `FLOOR(term)`
 * `CEILING(term)`
 * `ABS(term)`
 * `SQRT(term)`
 
## Additional operators and functions
It is possible to extend the FormulaEvaluator with custom Operators and Functions by registering them in `BigDecimalTermFactory`

## How does it works
1. In a first step the formula will be parsed and converted into PRN notation
1. As next based on the PRN a calculation tree is built up which will later be used for the calculations
1. Now this tree (Term) can be evaluated by passing all variables

## Caching
Already evaluated and built up terms will be cached to improve future calculations.
