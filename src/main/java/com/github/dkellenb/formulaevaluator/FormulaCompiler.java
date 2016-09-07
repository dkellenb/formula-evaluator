package com.github.dkellenb.formulaevaluator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.github.dkellenb.formulaevaluator.definition.Function;
import com.github.dkellenb.formulaevaluator.definition.Operator;
import com.github.dkellenb.formulaevaluator.exceptions.FormulaEvaluatorException;
import com.github.dkellenb.formulaevaluator.mapping.BigDecimalTermFactory;
import com.github.dkellenb.formulaevaluator.mapping.TermFactory;
import com.github.dkellenb.formulaevaluator.term.Term;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

/**
 * Compiles a Formula to an RPN and based on the RPN build up the formula evaluator tree.
 *
 * @param <T> type of the class based on which the calculations will be performed (e.g. BigDecimal)
 */
final class FormulaCompiler<T> {

  /** The original formula. */
  private final String formula;

  /** The term factory which knows all supported terms. */
  private final TermFactory<T> termFactory;

  /** Known functions. */
  private final Map<String, Function> functions;

  /** Known opoerators. */
  private final Map<String, Operator> operators;

  /** Known constants. */
  private final Set<String> constants;

  /** List of known variables. */
  private final Set<String> variables;

  /** The cached RPN (Reverse Polish Notation) of the expression. */
  private List<String> rpn = null;

  /** What character to use for decimal separators. */
  private static final char DECIMAL_SEPARATOR = '.';

  /** What character to use for minus sign (negative values). */
  private static final char MINUS_SIGN = '-';

  /**
   * Compiles a formula and returns the Calculation Tree.
   *
   * @param formula the formula
   * @param variables all variables in the formula
   * @return the tree
   */
  public static Term<BigDecimal> compile(String formula, String... variables) {
    return create(formula, variables).build();
  }

  /**
   * Compiles a formula and returns the Calculation Tree.
   *
   * @param formula the formula
   * @param variables all variables in the formula
   * @return the tree
   */
  public static Term<BigDecimal> compile(String formula, Set<String> variables) {
    return create(formula, variables).build();
  }

  /**
   * Creates a BigDecimal based formula compiler. Default visibility for testing purpose.
   *
   * @param formula the formula
   * @param variables known variables
   * @return the compiler
   */
  public static FormulaCompiler<BigDecimal> create(String formula, String... variables) {
    return new FormulaCompiler<>(formula,
      variables.length == 0 ? emptySet() : new HashSet<>(asList(variables)),
      BigDecimalTermFactory.getInstance());
  }

  /**
   * Creates a BigDecimal based formula compiler. Default visibility for testing purpose.
   *
   * @param formula the formula
   * @param variables known variables
   * @return the compiler
   */
  public static FormulaCompiler<BigDecimal> create(String formula, Set<String> variables) {
    return new FormulaCompiler<>(formula,
      new HashSet<>(variables),
      BigDecimalTermFactory.getInstance());
  }

  private FormulaCompiler(String formula, Set<String> variables, TermFactory<T> termFactory) {
    this.formula = formula;
    this.termFactory = termFactory;
    this.operators = termFactory.getSupportedOperators();
    this.functions = termFactory.getSupportedFunctions();
    this.variables = new HashSet<>(variables);
    this.constants = termFactory.getSupportedConstants();
  }

  /**
   * Evaluates the expression without rounding.
   *
   * @return the result of the expression.
   */
  public Term<T> build() {
    Stack<Term<T>> stack = new Stack<>();
    for (String token : getRPN()) {
      if (operators.containsKey(token)) {
        Term<T> v1Term = stack.pop();
        Term<T> v2Term = stack.pop();
        Term<T> operatorTerm = termFactory.getOperatorTerm(operators.get(token), v2Term, v1Term);
        stack.push(operatorTerm);
      } else if (constants.contains(token)) {
        Term<T> constantTerm = termFactory.getConstantTerm(token);
        stack.push(constantTerm);
      } else if (variables.contains(token)) {
        Term<T> variableTerm = termFactory.getVariableTerm(token);
        stack.push(variableTerm);
      } else if (functions.containsKey(token.toUpperCase())) {
        Function f = functions.get(token.toUpperCase());
        List<Term<T>> parameters = new ArrayList<>(f.getNumParams());
        for (int i = 0; i < f.getNumParams(); i++) {
          parameters.add(0, stack.pop());
        }
        Term<T> functionTerm = termFactory.getFunctionTerm(f, parameters);
        stack.push(functionTerm);
      } else {
        Term<T> fixedValueTerm = termFactory.createFixedValueTerm(token);
        stack.push(fixedValueTerm);
      }
    }
    return stack.pop();
  }

  /**
   * Get a string representation of the RPN (Reverse Polish Notation) for this
   * expression.
   *
   * @return a string with the RPN representation for this expression.
   */
  String toRPN() {
    StringBuilder sb = new StringBuilder();
    for (String st : getRPN()) {
      if (sb.length() > 0) {
        sb.append(" ");
      }
      sb.append(st);
    }
    return sb.toString();
  }

  /**
   * Get an iterator for this expression, allows iterating over an expression
   * token by token.
   *
   * @return A new iterator instance for this expression.
   */
  Iterator<String> getExpressionTokenizer() {
    return new Tokenizer(this.formula);
  }

  /**
   * Cached access to the RPN notation of this expression, ensures only one
   * calculation of the RPN per expression instance. If no cached instance
   * exists, a new one will be created and put to the cache.
   *
   * @return the cached RPN instance.
   */
  private List<String> getRPN() {
    if (rpn == null) {
      rpn = shuntingYard(this.formula);
    }
    return rpn;
  }

  /**
   * Implementation of the Shunting Yard algorithm to transform an infix expression to a RPN expression.
   *
   * @param expression the input expression in infx.
   * @return A RPN representation of the expression, with each token as a list member.
   */
  private List<String> shuntingYard(String expression) {
    List<String> outputQueue = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    Tokenizer tokenizer = new Tokenizer(expression);
    String lastFunction = null;
    String previousToken = null;
    while (tokenizer.hasNext()) {
      String token = tokenizer.next();
      if (isNumber(token)) {
        outputQueue.add(token);
      } else if (constants.contains(token)) {
        outputQueue.add(token);
      } else if (variables.contains(token)) {
        outputQueue.add(token);
      } else if (functions.containsKey(token.toUpperCase())) {
        stack.push(token);
        lastFunction = token;
      } else if (Character.isLetter(token.charAt(0))) {
        stack.push(token);
      } else if (",".equals(token)) {
        while (!stack.isEmpty() && !"(".equals(stack.peek())) {
          outputQueue.add(stack.pop());
        }
        if (stack.isEmpty()) {
          throw new FormulaEvaluatorException("Parse error for function '" + lastFunction + "'");
        }
      } else if (operators.containsKey(token)) {
        Operator o1 = operators.get(token);
        String token2 = stack.isEmpty() ? null : stack.peek();
        while (operators.containsKey(token2)
            && ((o1.isLeftAssoc() && o1.getPrecedence() <= operators.get(token2).getPrecedence())
             || (o1.getPrecedence() < operators.get(token2).getPrecedence()))) {
          outputQueue.add(stack.pop());
          token2 = stack.isEmpty() ? null : stack.peek();
        }
        stack.push(token);
      } else if ("(".equals(token)) {
        if (previousToken != null) {
          if (isNumber(previousToken)) {
            throw new FormulaEvaluatorException("Missing operator at character position " + tokenizer.getPos());
          }
        }
        stack.push(token);
      } else if (")".equals(token)) {
        while (!stack.isEmpty() && !"(".equals(stack.peek())) {
          outputQueue.add(stack.pop());
        }
        if (stack.isEmpty()) {
          throw new RuntimeException("Mismatched parentheses");
        }
        stack.pop();
        if (!stack.isEmpty() && functions.containsKey(stack.peek().toUpperCase())) {
          outputQueue.add(stack.pop());
        }
      }
      previousToken = token;
    }
    while (!stack.isEmpty()) {
      String element = stack.pop();
      if ("(".equals(element) || ")".equals(element)) {
        throw new RuntimeException("Mismatched parentheses");
      }
      if (!operators.containsKey(element)) {
        throw new RuntimeException("Unknown operator, function or unregistered variable: " + element);
      }
      outputQueue.add(element);
    }
    return outputQueue;
  }

  /**
   * Is the string a number?
   *
   * @param value the string
   * @return @code{true}, if the input string is a number.
   */
  private static boolean isNumber(String value) {
    if (value.charAt(0) == MINUS_SIGN && value.length() == 1) {
      return false;
    }
    for (char ch : value.toCharArray()) {
      if (!Character.isDigit(ch) && ch != MINUS_SIGN
            && ch != DECIMAL_SEPARATOR) {
        return false;
      }
    }
    return true;
  }

  /**
   * Expression tokenizer that allows to iterate over a {@link String} formula token by token. Blank characters will be
   * skipped.
   */
  private class Tokenizer implements Iterator<String> {

    /**
     * Actual position in formula string.
     */
    private int pos = 0;

    /**
     * The original input formula.
     */
    private String input;

    /**
     * The previous token or @code{null} if none.
     */
    private String previousToken;

    /**
     * Creates a new tokenizer for an formula.
     *
     * @param input
     * The formula string.
     */
    Tokenizer(String input) {
      this.input = input;
    }

    @Override
    public boolean hasNext() {
      return pos < input.length();
    }

    /**
     * Peek at the next character, without advancing the iterator.
     *
     * @return The next character or character 0, if at end of string.
     */
    private char peekNextChar() {
      if (pos < (input.length() - 1)) {
        return input.charAt(pos + 1);
      } else {
        return 0;
      }
    }

    @Override
    public String next() {
      StringBuilder token = new StringBuilder();
      if (pos >= input.length()) {
        previousToken = null;
        return null;
      }

      char ch = input.charAt(pos);
      while (Character.isWhitespace(ch) && pos < input.length()) {
        ch = input.charAt(++pos);
      }

      if (Character.isDigit(ch)) {
        // parsing of numbers
        while ((Character.isDigit(ch) || ch == DECIMAL_SEPARATOR) && (pos < input.length())) {
          token.append(input.charAt(pos++));
          ch = pos == input.length() ? 0 : input.charAt(pos);
        }

      } else if (ch == MINUS_SIGN
                   && Character.isDigit(peekNextChar())
                   && ("(".equals(previousToken) || ",".equals(previousToken) || previousToken == null
                         || operators.containsKey(previousToken))) {
        // negative value
        token.append(MINUS_SIGN);
        pos++;
        token.append(next());

      } else if (Character.isLetter(ch)) {
        // variable / function name parsing
        while ((Character.isLetter(ch) || Character.isDigit(ch) || (ch == '_') || (ch == '.'))
                 && (pos < input.length())) {
          token.append(input.charAt(pos++));
          ch = pos == input.length() ? 0 : input.charAt(pos);
        }

      } else if (ch == '(' || ch == ')' || ch == ',') {
        // separators
        token.append(ch);
        pos++;

      } else {
        //
        while (!Character.isLetter(ch) && !Character.isDigit(ch) && !Character.isWhitespace(ch) && ch != '('
                 && ch != ')' && ch != ',' && (pos < input.length())) {
          token.append(input.charAt(pos));
          pos++;
          ch = pos == input.length() ? 0 : input.charAt(pos);
          if (ch == MINUS_SIGN) {
            break;
          }
        }
        if (!operators.containsKey(token.toString())) {
          throw new FormulaEvaluatorException("Unknown operator '" + token + "' at position "
                                                + (pos - token.length() + 1));
        }
      }

      previousToken = token.toString();
      return previousToken;
    }

    @Override
    public void remove() {
      throw new FormulaEvaluatorException("remove() not supported");
    }

    /**
     * Get the actual character position in the string.
     *
     * @return The actual character position.
     */
    public int getPos() {
      return pos;
    }

  }

}
